/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PieChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - PieChart";
    private ObservableList<PieChart.Data> oal;
    private PieChart pc;
    private Button btn;
    private PieChart.Data d1;

    @Override
    public void index() {
        baseDemo();
        methodDemo();
        animateDemo();
    }

    public void baseDemo() {
        d1 = new PieChart.Data("data1", 40);
        PieChart.Data d2 = new PieChart.Data("data2", 10);
        PieChart.Data d3 = new PieChart.Data("data3", 30);
        PieChart.Data d4 = new PieChart.Data("data4", 60);
        PieChart.Data d5 = new PieChart.Data("data5", 40);

        oal = FXCollections.observableArrayList();
        oal.add(d1);
        oal.add(d2);
        oal.add(d3);
        oal.add(d4);
        oal.add(d5);

        pc = new PieChart(oal);
        btn = new Button("点击查看效果");
        setLeftAnchor(btn, 500.0);

        getChildren().addAll(btn, pc);
    }

    public void methodDemo() {
        // 是否显示标签
        pc.setLabelsVisible(true);

        // 设置标签线长
        pc.setLabelLineLength(50);

        // 旋转一个角度
        pc.setStartAngle(50);

        // 设置反方向
        pc.setClockwise(false);

        // 设置图例可见性
        pc.setLegendVisible(true);

        // 设置图例位置
        pc.setLegendSide(Side.RIGHT);

        // 设置标题和标题的位置
        pc.setTitle("演示");
        pc.setTitleSide(Side.TOP);

        // 数据改变时，是否播放动画
        pc.setAnimated(true);

    }

    private void animateDemo() {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                oal.get(0).setPieValue(70);
                // d1.setPieValue(70);
            }
        });

        pc.getData().forEach(new Consumer<PieChart.Data>() {
            @Override
            public void accept(PieChart.Data dt) {
                Node n = dt.getNode();
                Tooltip tp = new Tooltip(String.valueOf(dt.getPieValue()));
                Tooltip.install(n, tp);

                dt.pieValueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                        dt.setName(String.valueOf(t1.doubleValue()));

                        tp.setText(String.valueOf(dt.getPieValue()));
                        Tooltip.install(n, tp);
                    }
                });
            }
        });
    }

}
