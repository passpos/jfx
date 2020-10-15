/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.animation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TimelineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - Timeline";
    private Timeline tl;

    @Override
    public void index() {
        baseDemo();
        settingsDemo();
        controlDemo();
        eventDemo();
        listenerDemo();
    }

    public void baseDemo() {
        Button b = new Button("Button");
        setTopAnchor(b, 150.0);
        getChildren().addAll(b);

        /*
         * 起始状态
         *
         * kf1 参数1，若为2，则延迟2s，然后播放动画；
         *
         * 各个过程的状态设置应完整，下面如不设置kv1b，就会无法定位Y平移量，重播
         * 时，translateY的属性值就是上次暂停或停止时的值，再次播放translateY就
         * 不再是0了；
         */
        KeyValue kv1a = new KeyValue(b.translateXProperty(), 0);
        KeyValue kv1b = new KeyValue(b.translateYProperty(), 0);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0), "kf1", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("kf1");
            }
        }, kv1a, kv1b);

        // 中间状态
        KeyValue kv2a = new KeyValue(b.translateXProperty(), 300);
        KeyValue kv2b = new KeyValue(b.translateYProperty(), 0);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), "kf2", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("kf2");
            }
        }, kv2a, kv2b);

        // 结束状态
        KeyValue kv3a = new KeyValue(b.translateXProperty(), 600);
        KeyValue kv3b = new KeyValue(b.translateYProperty(), 300);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(3), "kf2", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("kf2");
            }
        }, kv3a, kv3b);

        tl = new Timeline();
        tl.getKeyFrames().addAll(kf1, kf2, kf3);
    }

    public void settingsDemo() {
        tl.setDelay(Duration.seconds(1));
        // tl.setCycleCount(3);
        // 无限循环
        tl.setCycleCount(Timeline.INDEFINITE);
        // 自动反向播放（反向播放也算一次）
        tl.setAutoReverse(true);
        // 播放速率
        tl.setRate(2);
    }

    public void controlDemo() {
        ToggleButton tb1 = new ToggleButton("播放");
        ToggleButton tb2 = new ToggleButton("暂停");
        ToggleButton tb3 = new ToggleButton("停止");
        ToggleButton tb4 = new ToggleButton("重播");

        ToggleGroup tg = new ToggleGroup();
        tb1.setToggleGroup(tg);
        tb2.setToggleGroup(tg);
        tb3.setToggleGroup(tg);
        tb4.setToggleGroup(tg);

        tb1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tl.play();
                ol("播放");
            }
        });
        tb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tl.pause();
                ol("暂停");
            }
        });
        tb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tl.stop();
                ol("停止");
            }
        });
        tb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tl.playFromStart();
                ol("重播");
                tl.getCurrentTime().toSeconds();
            }
        });

        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tb1, tb2, tb3, tb4);
        getChildren().add(h);

        /*
         * 提示点可以用来标记动画的重要位置。
         * 一旦定义了提示点，就可以将其用作jumpTo()和playFrom()的参数，以便快速移动到相关位置。
         */
        tl.getCuePoints().put("", Duration.seconds(8));
    }

    public void eventDemo() {
        // 循环播放没有结束事件
        tl.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("over");
            }
        });
    }

    public void listenerDemo() {
        tl.statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> ov, Animation.Status t, Animation.Status t1) {
                ol("状态：" + t1.toString());
            }
        });
    }
}
