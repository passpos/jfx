/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class LineChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - LineChart/折线图";
    private XYChart.Series<Number, Number> xy1;
    private XYChart.Series<Number, Number> xy2;

    @Override
    public void index() {
        dataDemo1();
        baseDemo();
        tooltipDemo();
    }

    public void dataDemo1() {
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

        LineChart<Number, Number> lc = new LineChart<>(x, y);
        lc.getData().addAll(xy1, xy2);
        getChildren().add(lc);

        lc.setCreateSymbols(true);
    }

    /**
     * series实例化后，并没有实例化节点，所以data.getNode()返回空，Node的实例化
     * 由chart执行（不需要用户另外指定）；
     * 执行chart.getData().add(series);后，data.getNode()才能获取到Node。
     * 亦即：此方法应放在baseDemo()之后。
     */
    public void tooltipDemo() {
        xy1.getData().forEach(new Consumer<XYChart.Data<Number, Number>>() {
            @Override
            public void accept(XYChart.Data<Number, Number> dt) {
                Tooltip tp = new Tooltip(dt.getXValue() + " - " + dt.getYValue());
                Tooltip.install(dt.getNode(), tp);

                dt.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        ol(dt.getXValue());
                    }
                });
            }
        });
    }
}
