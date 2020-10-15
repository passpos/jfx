/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.concurrent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Task是一个抽象类，用于安排一个一次性的任务
 * @author realpai <paiap@outlook.com>
 */
public class TaskApp extends ContentBox {

    public final static Boolean SHOWING = false;
    public final static String TITLE = "Concurrent - Task";
    private ProgressBar pb;
    private Button b1;
    private Button b2;
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
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        setLeftAnchor(hBox, 70.0);
        setTopAnchor(hBox, 70.0);

        b1 = new Button("开始");
        b2 = new Button("取消");

        pb = new ProgressBar(0);
        pb.setPrefWidth(200.0);

        l1 = new Label("Title-任务");
        l1.setPrefWidth(50.0);
        l1.setStyle("-fx-text-fill:#cccccc");

        l2 = new Label("Value");
        l2.setPrefWidth(50.0);
        l2.setStyle("-fx-text-fill:#cccccc");

        l3 = new Label("State-状态");
        l3.setPrefWidth(70.0);
        l3.setStyle("-fx-text-fill:#cccccc");

        l4 = new Label("Message-消息");
        l4.setPrefWidth(70.0);
        l4.setStyle("-fx-text-fill:#cccccc");

        hBox.getChildren().addAll(b1, b2, pb, l1, l2, l3, l4);
        getChildren().add(hBox);
    }

    public void baseDemo() {
        TaskDemo td = new TaskDemo();
        Thread thread = new Thread(td);
        // 设置后，任务线程称为守护线程，关闭界面，也会关闭任务线程；
        thread.setDaemon(true);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                thread.start();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                td.cancel();
            }
        });

        td.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                pb.setProgress(t1.doubleValue());
            }
        });
        td.titleProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l1.setText(t1);
            }
        });
        td.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                l3.setText(t1.toString());
                ol("线程任务状态：" + t1.toString());
            }
        });
        td.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                l4.setText(t1);
            }
        });
    }

}
