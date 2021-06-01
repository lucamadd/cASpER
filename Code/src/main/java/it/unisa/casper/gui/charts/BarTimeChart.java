package it.unisa.casper.gui.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BarTimeChart {

    private static final boolean VIEW_TIME = true;
    private static final boolean EXECUTION_TIME = false;


    private CategoryDataset createDataset(String[][] data, boolean mode) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (mode == VIEW_TIME){
            for(int i=0;i<data.length;i++){
                dataset.setValue(Integer.parseInt(data[i][1]), "View time", data[i][0]);
            }
        } else if (mode == EXECUTION_TIME) {
            for(int i=0;i<data.length;i++){
                dataset.setValue(Integer.parseInt(data[i][2]), "Execution time", data[i][0]);
            }
        }
        return dataset;
    }

    //restituisce un JPanel contenente il grafico
    public JPanel getPanel(boolean mode, String[][] data){
        //creo il dataset
        final CategoryDataset dataset = createDataset(data, mode);
        //creo il grafico
        final JFreeChart chart = createChart(dataset, mode);
        chart.setBackgroundPaint(new Color(255,255,255,180));

        final ChartPanel chartPanel = new ChartPanel(chart);
        if (mode == VIEW_TIME){
            chartPanel.setBorder(new TitledBorder("View time"));
        } else if (mode == EXECUTION_TIME){
            chartPanel.setBorder(new TitledBorder("Execution time"));
        }
        return chartPanel;
    }

    private JFreeChart createChart(CategoryDataset dataset, boolean mode) {
        JFreeChart chart = ChartFactory.createBarChart("", "",
                "Time (ms)", dataset, PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot plot = chart.getCategoryPlot();

        //plot.setRangeCrosshairPaint(Color.RED);
        //plot.setDomainGridlinePaint(Color.RED);

        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setBackgroundPaint(new Color(255,255,255,180));

        plot.setOutlineVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setGradientPaintTransformer(null);

        if (mode == VIEW_TIME){
            renderer.setSeriesPaint(0, Color.BLUE);
        } else if (mode == EXECUTION_TIME){
            renderer.setSeriesPaint(0, Color.RED);
        }


        renderer.setBarPainter(new StandardBarPainter());



        renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        Font font = new Font("Dialog", Font.PLAIN, 10);
        domainAxis.setTickLabelFont(font);

        return chart;

    }
}
