/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class StackedBarChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - StackedBarChart/堆积柱状图";
    private XYChart.Series<String, Number> xy1;
    private XYChart.Series<String, Number> xy2;

    @Override
    public void index() {
        data();
        baseDemo();
    }

    @SuppressWarnings("unchecked")
    public void data() {
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("2011", 30);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("2012", 20);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("2013", 50);
        XYChart.Data<String, Number> d4 = new XYChart.Data<>("2014", 60);

        XYChart.Data<String, Number> d5 = new XYChart.Data<>("2011", 20);
        XYChart.Data<String, Number> d6 = new XYChart.Data<>("2012", 70);
        XYChart.Data<String, Number> d7 = new XYChart.Data<>("2013", 30);
        XYChart.Data<String, Number> d8 = new XYChart.Data<>("2014", 80);

        xy1 = new XYChart.Series<>();
        xy1.setName("xy1");
        xy1.getData().addAll(d1, d2, d3, d4);

        xy2 = new XYChart.Series<>();
        xy2.setName("xy2");
        xy2.getData().addAll(d5, d6, d7, d8);
    }

    @SuppressWarnings("unchecked")
    public void baseDemo() {
        // X轴
        CategoryAxis x = new CategoryAxis();
        x.setLabel("年份");

        // Y轴（最大值、最小值、单位值）
        NumberAxis y = new NumberAxis(0, 200, 10);
        y.setLabel("GDP");

        StackedBarChart<String, Number> sbc = new StackedBarChart<>(x, y);
        sbc.getData().addAll(xy1, xy2);
        sbc.setTitle("StackedBarChart");
        getChildren().add(sbc);

    }

}
