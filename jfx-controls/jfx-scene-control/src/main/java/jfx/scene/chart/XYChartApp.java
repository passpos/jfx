/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class XYChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - XYChart";
    private XYChart.Series<Number, Number> xy1;
    private XYChart.Series<Number, Number> xy2;
    private LineChart<Number, Number> lc;
    private NumberAxis x;
    private NumberAxis y;

    @Override
    public void index() {
        data();
        initChart();
        baseDemo();
    }

    @SuppressWarnings("unchecked")
    private void data() {
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

    @SuppressWarnings("unchecked")
    private void initChart() {
        // X轴
        x = new NumberAxis();
        x.setLabel("国家");

        // Y轴（最大值、最小值、单位值）
        y = new NumberAxis(0, 100, 10);
        y.setLabel("GDP");

        lc = new LineChart<>(x, y);
        lc.getData().addAll(xy1, xy2);
        lc.setTitle("LineChart");
        getChildren().add(lc);
    }

    public void baseDemo() {
        // 是否显示网格横线
        lc.setHorizontalGridLinesVisible(false);

        // 是否显示网格纵线
        lc.setVerticalGridLinesVisible(false);

        // 是否显示水平0点线
        lc.setHorizontalGridLinesVisible(false);
        // 是否显示垂直0点线
        lc.setVerticalZeroLineVisible(false);

        lc.setAlternativeColumnFillVisible(true);
        lc.setAlternativeRowFillVisible(true);
    }

    public void numberAxisDemo() {
        y.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number t) {
                return t.intValue() + " - blabla";
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });
    }
}
