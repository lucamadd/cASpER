package it.unisa.casper.gui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import it.unisa.casper.statistics.StatsExtracter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfirmExtractionPage extends DialogWrapper {

    private JPanel centerPanel;


    public ConfirmExtractionPage() {
        super(true);
        setResizable(false);
        init();
        setTitle("CONFIRM");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        //main panel init
        centerPanel = new JPanel();
        centerPanel.setBorder(JBUI.Borders.empty(5));
        centerPanel.setLayout(new GridLayout(1, 1, 0, 0));
        centerPanel.setPreferredSize(new Dimension(400, 100));

        JLabel label = new JLabel();
        label.setText("Do you want to export statistics into a .csv file?");
        centerPanel.add(label);

        return centerPanel;
    }



    @NotNull
    @Override
    protected Action[] createActions() {
        Action okAction = new DialogWrapperAction("YES") {

            @Override
            protected void doAction(ActionEvent actionEvent) {
                StatsExtracter statsExtracter = new StatsExtracter();
                try {
                    statsExtracter.createCSV();
                    JOptionPane.showMessageDialog(centerPanel, "The .csv file was created successfully.");
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(centerPanel, "Sorry, an error has occurred.");
                    dispose();
                }

            }
        };

        return new Action[]{okAction, new DialogWrapperExitAction("NO", 0)};
    }
}
