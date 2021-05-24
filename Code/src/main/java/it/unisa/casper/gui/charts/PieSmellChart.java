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
import java.awt.*;

public class PieSmellChart {

    private String[][] data;

    public PieSmellChart(String[][] data){
        this.data = data;
    }

    //restituisce un JPanel contenente il grafico
    public JPanel getPanel(){
        //creo il dataset
        final PieDataset dataset = createDataset(data);
        //creo il grafico
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;
    }

    private PieDataset createDataset(String[][] data) {
        final DefaultPieDataset dataset = new DefaultPieDataset();
        int blob = 0;
        int misplacedClass = 0;
        int promiscuousPackage = 0;
        int featureEnvy = 0;
        for (int i=0;i<data.length;i++){
            blob += Integer.parseInt(data[i][6]);
            misplacedClass += Integer.parseInt(data[i][7]);
            promiscuousPackage += Integer.parseInt(data[i][8]);
            featureEnvy += Integer.parseInt(data[i][9]);
        }
        dataset.setValue("Blob", blob);
        dataset.setValue("Misplaced class", misplacedClass);
        dataset.setValue("Promiscuous package", promiscuousPackage);
        dataset.setValue("Feature envy", featureEnvy);
        return dataset;
    }

    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart3D(
                "Number of smells",  // chart title
                dataset,             // dataset
                true,                // include legend
                true,
                false
        );
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("One", 0.30);

        plot.setSectionPaint("One", Color.BLACK);
        plot.setSectionPaint("Two", Color.WHITE);

        //plot.setBackgroundPaint(Color.YELLOW);

        //plot.setOutlinePaint(Color.RED);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}--{1}--{2}"));

        plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);

        chart.setBorderVisible(false);

        //chart.setBorderPaint(Color.RED);

        //chart.setBackgroundPaint(Color.YELLOW);



        return chart;
    }

}
