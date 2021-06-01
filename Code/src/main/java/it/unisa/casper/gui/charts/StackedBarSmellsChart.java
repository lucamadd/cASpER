package it.unisa.casper.gui.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StackedBarSmellsChart {

    private int blob = 0;
    private int misplacedClass = 0;
    private int promiscuousPackage = 0;
    private int featureEnvy = 0;

    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createStackedBarChart(
                "", "", "No. of smells", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(new Color(255,255,255,180));

        CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        Font font = new Font("Dialog", Font.PLAIN, 10);
        domainAxis.setTickLabelFont(font);
        return chart;
    }

    private CategoryDataset createDataset(String[][] data) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        for (int i=0;i<data.length;i++){

            blob = 0;
            misplacedClass = 0;
            promiscuousPackage = 0;
            featureEnvy = 0;
            blob += Integer.parseInt(data[i][6]);
            misplacedClass += Integer.parseInt(data[i][7]);
            promiscuousPackage += Integer.parseInt(data[i][8]);
            featureEnvy += Integer.parseInt(data[i][9]);
            result.addValue(blob, "Blob", data[i][0]);
            result.addValue(misplacedClass, "Misplaced class", data[i][0]);
            result.addValue(promiscuousPackage, "Promiscuous package", data[i][0]);
            result.addValue(featureEnvy, "Feature Envy", data[i][0]);
        }

        return result;
    }

    public JPanel getPanel(String[][] data){
        //creo il dataset
        final CategoryDataset dataset = createDataset(data);
        //creo il grafico
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(new TitledBorder("Number of smells"));
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;
    }

    public void update(String[][] data){
        getPanel(data);
    }
}
