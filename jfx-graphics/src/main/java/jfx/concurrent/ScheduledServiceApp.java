/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.concurrent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 * ScheduledService是一个抽象类，用于安排一个可重用、按周期或按计划执行的任务
 * 该类任务可以在指定时间后启动/执行、也可以设置周期性的执行；
 *
 * @author realpai <paiap@outlook.com>
 */
public class ScheduledServiceApp extends ContentBox {

    public final static Boolean SHOWING = false;
    public final static String TITLE = "Concurrent - ScheduledService";
    private ProgressBar pb;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;

    @Override
    public void index() {
        setUI();
        baseDemo();
    }

    public void setUI() {
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(5.0);

        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(5.0);

        b1 = new Button("开始");
        b2 = new Button("取消");
        b3 = new Button("重启");
        b4 = new Button("重置");

        pb = new ProgressBar(0);
        pb.setPrefWidth(300.0);

        l1 = new Label("Title-任务");
        l1.setPrefWidth(100.0);
        l1.setStyle("-fx-text-fill:#cccccc");

        l2 = new Label("Value");
        l2.setPrefWidth(100.0);
        l2.setStyle("-fx-text-fill:#cccccc");

        l3 = new Label("State-状态");
        l3.setPrefWidth(100.0);
        l3.setStyle("-fx-text-fill:#cccccc");

        l4 = new Label("Message-消息");
        l4.setPrefWidth(100.0);
        l4.setStyle("-fx-text-fill:#cccccc");

        hBox1.getChildren().addAll(b1, b2, b3, b4);
        hBox2.getChildren().addAll(l1, l2, l3, l4);
        getChildren().addAll(hBox1, pb, hBox2);

        setTopAnchor(pb, 50.0);
        setTopAnchor(hBox2, 100.0);
    }

    public void baseDemo() {
        MyScheduledService mss = new MyScheduledService();
        mss.setDelay(Duration.seconds(3));
        mss.setPeriod(Duration.seconds(3));
        mss.setRestartOnFailure(true);
        mss.setMaximumFailureCount(3);
//        mss.setBackoffStrategy(new Callback<ScheduledService<?>, Duration>() {
//            @Override
//            public Duration call(ScheduledService<?> param) {
//                return null;
//            }
//        });

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mss.start();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mss.cancel();
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mss.restart();
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mss.reset();
                pb.setProgress(0);
            }
        });

        mss.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                pb.setProgress(t1.doubleValue());
            }
        });
        mss.titleProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l1.setText(t1);
            }
        });
        mss.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                l3.setText(t1.toString());
                ol("线程任务状态：" + t1.toString());
            }
        });
        mss.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l4.setText(t1);
            }
        });
    }
}

class MyScheduledService extends ScheduledService<Number> {

    public int count = 0;

    @Override
    protected Task<Number> createTask() {
        count += 1;
        ol(count);
        TaskDemo td = new TaskDemo();
        return td;
    }

    @Override
    protected void failed() {
        super.failed();
    }

    @Override
    protected void executeTask(Task<Number> task) {
        super.executeTask(task);
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
