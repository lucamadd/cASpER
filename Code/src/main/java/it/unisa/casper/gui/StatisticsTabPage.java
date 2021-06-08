package it.unisa.casper.gui;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import it.unisa.casper.gui.charts.*;
import it.unisa.casper.statistics.StatsExtracter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class StatisticsTabPage extends DialogWrapper {
    private JPanel contentPanel;
    private JTable table;

    private String[][] data;

    public StatisticsTabPage() {
        super(true);
        setResizable(false);
        init();
        setTitle("STATISTICS");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        contentPanel = new JPanel(); //pannello principale
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setLayout(new GridLayout(3, 1));//layout pannello principale

        JPanel tab = new JPanel();
        tab.setLayout(new BorderLayout());
        tab.setBorder(new TitledBorder("Stats table"));

        createTable();

        JScrollPane scroll = new JScrollPane(table);
        tab.add(scroll, BorderLayout.CENTER);

        JPanel configPanel = new JPanel();
        configPanel.setLayout(new GridLayout(1, 1));
        configPanel.setBorder(new TitledBorder("Config"));




        JComboBox comboBox = new ComboBox();
        comboBox.addItem("All (default)");
        comboBox.addItem("Last usage");
        comboBox.addItem("Last 5 usages");
        comboBox.addItem("Last 10 usages");
        comboBox.addItem("Last 20 usages");
        comboBox.addItem("Last 50 usages");
        comboBox.setSelectedIndex(0);


        JButton applyButton = new JButton("APPLY");

        configPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        configPanel.add(new JLabel("Filter"), constraints);

        constraints.gridx = 1;
        configPanel.add(comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        configPanel.add(applyButton, constraints);
        JSplitPane topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                tab, configPanel);
        topPanel.setOneTouchExpandable(true);
        topPanel.setDividerLocation(0.2);
        topPanel.setEnabled(false);

        Dimension minimumSize = new Dimension(1000, 50);
        Dimension minimumSizeConfig = new Dimension(50, 50);
        tab.setMinimumSize(minimumSize);
        configPanel.setMinimumSize(minimumSizeConfig);
        contentPanel.add(topPanel);
        table.repaint();
        //contentPanel.add(table);
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 3));//layout pannello centrale
        bottomPanel.setLayout(new GridLayout(1, 3));//layout pannello inferiore
        try {
            StackedBarSmellsChart stackedBarSmellsChart = new StackedBarSmellsChart();
            centerPanel.add(stackedBarSmellsChart.getPanel(data));
            BarTimeChart barViewTimeChart = new BarTimeChart();
            centerPanel.add(barViewTimeChart.getPanel(true,data));
            BarTimeChart barExecutionTimeChart = new BarTimeChart();
            centerPanel.add(barExecutionTimeChart.getPanel(false,data));
            PieRefactorChart pieRefactorChart = new PieRefactorChart();
            bottomPanel.add(pieRefactorChart.getPanel(data));
            RingNoSolutionChart ringNoSolutionChartBlob = new RingNoSolutionChart();
            bottomPanel.add(ringNoSolutionChartBlob.getPanel(true,data));
            RingNoSolutionChart ringNoSolutionChartPromiscuousPackage = new RingNoSolutionChart();
            bottomPanel.add(ringNoSolutionChartPromiscuousPackage.getPanel(false,data));
        } catch (Exception e){
            //per catturare eccezioni nel caso in cui i dati siano malformati
            e.printStackTrace();
            Messages.showMessageDialog("Error fetching statistics.", "Error", Messages.getErrorIcon());
        }
        contentPanel.add(centerPanel);
        contentPanel.add(bottomPanel);
        contentPanel.setPreferredSize(new Dimension(1250, 900));


        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatsExtracter extracter = new StatsExtracter();
                try {
                    data = extracter.readStatistics(comboBox.getSelectedIndex());

                    if (data != null){
                        StackedBarSmellsChart stackedBarSmellsChart = new StackedBarSmellsChart();
                        centerPanel.removeAll();
                        centerPanel.add(stackedBarSmellsChart.getPanel(data));
                        BarTimeChart barViewTimeChart = new BarTimeChart();
                        centerPanel.add(barViewTimeChart.getPanel(true,data));
                        BarTimeChart barExecutionTimeChart = new BarTimeChart();
                        centerPanel.add(barExecutionTimeChart.getPanel(false,data));
                        PieRefactorChart pieRefactorChart = new PieRefactorChart();
                        bottomPanel.removeAll();
                        bottomPanel.add(pieRefactorChart.getPanel(data));
                        RingNoSolutionChart ringNoSolutionChartBlob = new RingNoSolutionChart();
                        bottomPanel.add(ringNoSolutionChartBlob.getPanel(true,data));
                        RingNoSolutionChart ringNoSolutionChartPromiscuousPackage = new RingNoSolutionChart();
                        bottomPanel.add(ringNoSolutionChartPromiscuousPackage.getPanel(false,data));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Messages.showMessageDialog("Error fetching statistics.", "Error", Messages.getErrorIcon());
                }
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        return contentPanel;

    }

    private void createTable() {

        String header[] = {"Date/Time","View time (ms)","Execution time (ms)","No solutions for Promiscuous Package",
                "No solutions for Blob", "Refactoring done", "#smells for Blob", "#smells for Misplaced Class",
                "#smells for Promiscuous Package", "#smells for Feature Envy"};
        StatsExtracter extracter = new StatsExtracter();
        data = null;
        try {
            data = extracter.readStatistics(0);
        } catch (IOException e) {
            e.printStackTrace();
            Messages.showMessageDialog("Error fetching statistics.", "Error", Messages.getErrorIcon());
        }

        DefaultTableModel model = new DefaultTableModel(data,header){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table = new JTable(model);
    }


    @NotNull
    @Override
    protected Action[] createActions() {
        Action okAction = new DialogWrapperAction("EXPORT .CSV") {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                try {
                    //noinspection UnstableApiUsage
                    com.intellij.ide.actions.ShowFilePathAction.openDirectory(new File(
                            System.getProperty("user.home") + File.separator +
                                    ".casper" + File.separator + "statistics"
                    ));
                } catch (Exception e) {
                    Messages.showMessageDialog("Error retrieving .csv file", "Error", Messages.getErrorIcon());
                }
            }
        };
        Action exitAction = new DialogWrapperExitAction("EXIT", 0) {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    super.doAction(actionEvent);
                }
            }
        };
        return new Action[]{okAction, exitAction};
    }





}
