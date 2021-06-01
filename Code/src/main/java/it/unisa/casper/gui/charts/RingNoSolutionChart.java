package it.unisa.casper.gui.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RingNoSolutionChart {

    //restituisce un JPanel contenente il grafico
    public JPanel getPanel(boolean isBlob, String[][] data){
        //creo il dataset
        final PieDataset dataset = createDataset(data, isBlob);
        //creo il grafico
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        if (isBlob){
            chartPanel.setBorder(new TitledBorder("Solutions for Blob"));
        } else {
            chartPanel.setBorder(new TitledBorder("Solutions for Promiscuous Package"));
        }
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;
    }

    private PieDataset createDataset(String[][] data, boolean isBlob) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int notApplicable = 0;
        int noSolutionFound = 0;
        int solutionFound = 0;
        if (isBlob){
            for (int i=0;i<data.length;i++){
                if (data[i][4].equals("-1")){
                    notApplicable++;
                } else if (data[i][4].equals("0")){
                    solutionFound++;
                } else {
                    noSolutionFound++;
                }
            }
        } else {
            for (int i=0;i<data.length;i++){
                if (data[i][3].equals("-1")){
                    notApplicable++;
                } else if (data[i][3].equals("0")){
                    solutionFound++;
                } else {
                    noSolutionFound++;
                }
            }
        }

        dataset.setValue("N/A", notApplicable);
        dataset.setValue("No solution found", noSolutionFound);
        dataset.setValue("Solution found", solutionFound);

        return dataset;
    }

    private JFreeChart createChart(final PieDataset dataset) {
        JFreeChart chart = ChartFactory.createRingChart(
                "",  // chart title
                dataset,             // data
                true,               // include legend
                true,
                false
        );

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}--{1}--{2}"));
        //plot.setSectionDepth(0.35);
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        plot.setOutlinePaint(new Color(0,0,0,0));
        plot.setBackgroundPaint(new Color(0,0,0,0));
        chart.setBackgroundPaint(new Color(255,255,255,180));
        return chart;
    }

}
