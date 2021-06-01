package it.unisa.casper.gui.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PieRefactorChart {


    //restituisce un JPanel contenente il grafico
    public JPanel getPanel(String[][] data){
        //creo il dataset
        final PieDataset dataset = createDataset(data);
        //creo il grafico
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(new TitledBorder("Refactoring"));
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;
    }

    private PieDataset createDataset(String[][] data) {
        final DefaultPieDataset dataset = new DefaultPieDataset();
        int refactoringPerformed = 0;
        int refactoringNotPerformed = 0;
        for (int i=0;i<data.length;i++){
            if (data[i][5].equals("0")){
                refactoringNotPerformed++;
            } else {
                refactoringPerformed++;
            }
        }
        dataset.setValue("Refactoring performed", refactoringPerformed);
        dataset.setValue("Refactoring not performed", refactoringNotPerformed);
        return dataset;
    }

    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart3D(
                "",  // chart title
                dataset,             // dataset
                true,                // include legend
                true,
                false
        );
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("One", 0.30);

        plot.setSectionPaint("One", Color.BLACK);
        plot.setSectionPaint("Two", Color.WHITE);

        //plot.setBackgroundPaint(Color.YELLOW);

        plot.setOutlinePaint(new Color(0,0,0,0));

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}--{1}--{2}"));

        plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
        plot.setBackgroundPaint(new Color(0,0,0,0));
        chart.setBackgroundPaint(new Color(255,255,255,180));

        chart.setBorderVisible(false);



        return chart;
    }

}
