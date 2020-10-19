/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BubbleChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - BubbleChart/气泡图";
    private XYChart.Series<Number, Number> xy1;
    private XYChart.Series<Number, Number> xy2;

    @Override
    public void index() {
        data();
        baseDemo();
    }

    public void data() {
        XYChart.Data<Number, Number> d1 = new XYChart.Data<>(10, 30);
        XYChart.Data<Number, Number> d2 = new XYChart.Data<>(20, 20);
        XYChart.Data<Number, Number> d3 = new XYChart.Data<>(30, 50);
        XYChart.Data<Number, Number> d4 = new XYChart.Data<>(40, 60);

        XYChart.Data<Number, Number> d5 = new XYChart.Data<>(10, 20);
        XYChart.Data<Number, Number> d6 = new XYChart.Data<>(20, 70);
        XYChart.Data<Number, Number> d7 = new XYChart.Data<>(30, 30);
        XYChart.Data<Number, Number> d8 = new XYChart.Data<>(40, 80);

        xy1 = new XYChart.Series<>();
        xy1.setName("xy1");
        xy1.getData().addAll(d1, d2, d3, d4);

        xy2 = new XYChart.Series<>();
        xy2.setName("xy2");
        xy2.getData().addAll(d5, d6, d7, d8);
    }

    public void baseDemo() {
        // X轴
        NumberAxis x = new NumberAxis();
        x.setLabel("国家");

        // Y轴（最大值、最小值、单位值）
        NumberAxis y = new NumberAxis(0, 100, 10);
        y.setLabel("GDP");

        BubbleChart<Number, Number> bc = new BubbleChart<>(x, y);
        bc.getData().addAll(xy1, xy2);
        bc.setTitle("BubbleChart");
        getChildren().add(bc);

    }
}
