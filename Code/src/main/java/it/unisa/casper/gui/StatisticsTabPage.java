package it.unisa.casper.gui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import it.unisa.casper.gui.charts.PieSmellChart;
import it.unisa.casper.statistics.StatsExtracter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class StatisticsTabPage extends DialogWrapper {
    private JPanel contentPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
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
        //TODO OPEN FILE
        contentPanel = new JPanel(); //pannello principale
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setLayout(new GridLayout(0, 1));//layout pannello principale
        JPanel tab = new JPanel();
        tab.setLayout(new BorderLayout(0, 0));
        tab.setBorder(new TitledBorder("Stats table"));

        createTable();

        JScrollPane scroll = new JScrollPane(table);
        tab.add(scroll, BorderLayout.CENTER);

        contentPanel.add(tab);
        table.repaint();
        //contentPanel.add(table);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 4));//layout pannello principale
        PieSmellChart pieChart = new PieSmellChart(data);
        bottomPanel.add(pieChart.getPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        bottomPanel.add(new JPanel());
        contentPanel.add(bottomPanel);
        contentPanel.setPreferredSize(new Dimension(1250, 700));

        return contentPanel;

    }

    private void createTable() {

        String header[] = {"Date/Time","View time (ms)","Execution time (ms)","No solutions for Promiscuous Package",
                "No solutions for Blob", "Refactoring done", "#smells for Blob", "#smells for Misplaced Class",
                "#smells for Promiscuous Package", "#smells for Feature Envy"};
        StatsExtracter extracter = new StatsExtracter();
        data = null;
        try {
            data = extracter.readStatistics();
        } catch (IOException e) {
            e.printStackTrace();
            Messages.showMessageDialog("Error fetching statistics.", "Error", Messages.getErrorIcon());
        }


        DefaultTableModel model = new DefaultTableModel(data,header);
        table = new JTable(model);
    }


    @NotNull
    @Override
    protected Action[] createActions() {
        Action okAction = new DialogWrapperExitAction("APPLY", 1) {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                try {

                } catch (Exception e) {
                    Messages.showMessageDialog("Error during storing thresholds", "Error", Messages.getErrorIcon());
                } finally {
                    Messages.showMessageDialog("Configured thresholds", "Success", Messages.getInformationIcon());
                    super.doAction(actionEvent);
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
