/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - Demo 动态显示数据";
    private NumberAxis x;
    private NumberAxis y;
    private XYChart.Series<Number, Number> xy1;
    private XYChart.Series<Number, Number> xy2;
    private ChartDataTask cdt;

    @Override
    public void index() {
        data();
        actionEvent();
        baseDemo();
        dataEvent();
    }

    public void data() {
        // 数据序列1
        xy1 = new XYChart.Series<>();
        xy1.setName("xy1");

        // 数据序列2
        xy2 = new XYChart.Series<>();
        xy2.setName("xy2");

        // 数据生产任务
        cdt = new ChartDataTask();
        cdt.setDelay(Duration.seconds(0.5));
        cdt.setPeriod(Duration.seconds(1));
    }

    public void actionEvent() {
        Button stop = new Button("停止");
        Button play = new Button("监控");

        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        setTopAnchor(hBox, 20.0);
        setLeftAnchor(hBox, 20.0);
        hBox.getChildren().addAll(play, stop);
        getChildren().add(hBox);

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (!cdt.isRunning()) {
                    cdt.start();
                }
            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (cdt.isRunning()) {
                    cdt.cancel();
                }
                cdt.reset();
            }
        });

    }

    public void baseDemo() {
        // X轴
        x = new NumberAxis(0, 20, 1);
        x.setLabel("Time");

        // Y轴（最大值、最小值、单位值）
        y = new NumberAxis(0, 100, 5);
        y.setLabel("Value");

        LineChart<Number, Number> lc = new LineChart<>(x, y);
        lc.getData().addAll(xy1, xy2);
        lc.setTitle("动态数据表 - 演示");
        lc.setAnimated(true);

        setTopAnchor(lc, 50.0);
        getChildren().add(lc);

    }

    public void dataEvent() {
        cdt.valueProperty().addListener(new ChangeListener<ArrayList<Integer>>() {
            @Override
            public void changed(ObservableValue<? extends ArrayList<Integer>> ov, ArrayList<Integer> t, ArrayList<Integer> t1) {
                if (t1 != null) {
                    int v1 = t1.get(0);
                    int v2 = t1.get(1);

                    int size = xy1.getData().size();

                    if (size > 200) {
                        size = 0;
                        xy1.getData().clear();
                        xy2.getData().clear();

                        x.setLowerBound(0);
                        x.setUpperBound(20);
                    }

                    XYChart.Data<Number, Number> d1 = new XYChart.Data<>(size, v1);
                    XYChart.Data<Number, Number> d2 = new XYChart.Data<>(size, v2);

                    xy1.getData().add(d1);
                    xy2.getData().add(d2);

                    if (size > 18) {
                        x.setLowerBound(x.getLowerBound() + 1);
                        x.setUpperBound(x.getUpperBound() + 1);
                    }
                }
            }
        });
    }
}

class ChartDataTask extends ScheduledService<ArrayList<Integer>> {

    @Override
    protected Task<ArrayList<Integer>> createTask() {
        Task<ArrayList<Integer>> task = new Task<ArrayList<Integer>>() {
            @Override
            protected ArrayList<Integer> call() throws Exception {
                Random r = new Random();
                int ni1 = r.nextInt(100);
                int ni2 = r.nextInt(100);
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(ni1);
                list.add(ni2);
                return list;
            }

        };
        return task;
    }

}
