/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.concurrent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import jfx.utils.app.ContentBox;

/**
 * Service是一个抽象类，用于安排一个可重用的任务
 * 该类任务可以在暂停后设置继续；
 *
 * @author realpai <paiap@outlook.com>
 */
public class ServiceApp extends ContentBox {

    public final static Boolean SHOWING = false;
    public final static String TITLE = "Concurrent - Service";
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

    /**
     * 不要反复点击 开始、取消按钮；
     * 不要在任务运行时，点击重置；
     * 遵循 开始 - 取消 - 重置 的使用流程；
     */
    public void baseDemo() {
        MyService ms = new MyService();
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ms.start();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ms.cancel();
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ms.restart();
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ms.reset();
                pb.setProgress(0);
            }
        });

        ms.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                pb.setProgress(t1.doubleValue());
            }
        });
        ms.titleProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l1.setText(t1);
            }
        });
        ms.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                l3.setText(t1.toString());
                ol("线程任务状态：" + t1.toString());
            }
        });
        ms.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l4.setText(t1);
            }
        });
    }
}

class MyService extends Service<Number> {

    @Override
    protected Task<Number> createTask() {
        TaskDemo td = new TaskDemo();
        return td;
    }

    /**
     * 接收 createTask() 的返回，并执行该任务
     * @param task
     */
    @Override
    protected void executeTask(Task<Number> task) {
        super.executeTask(task);
        task.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol(t1.doubleValue());
            }
        });
        ol("executeTask");
        ol(Thread.currentThread().getName());
    }

    @Override
    protected void failed() {
        super.failed();
        ol("failed");
        ol(Thread.currentThread().getName());
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        ol("cancelled");
        ol(Thread.currentThread().getName());

    }

    @Override
    protected void succeeded() {
        super.succeeded();
        ol("succeeded");
        ol(Thread.currentThread().getName());
    }

    @Override
    protected void running() {
        super.running();
        ol("running");
        ol(Thread.currentThread().getName());
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        ol("scheduled");
        ol(Thread.currentThread().getName());
    }

    @Override
    protected void ready() {
        super.ready();
        ol("ready");
        ol(Thread.currentThread().getName());
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
