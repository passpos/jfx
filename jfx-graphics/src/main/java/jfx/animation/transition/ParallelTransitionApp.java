/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.animation.transition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ParallelTransitionApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - ParallelTransition 并行动画";
    private ParallelTransition pt;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Rectangle r = new Rectangle(50, 50, Color.BLANCHEDALMOND);
        setTopAnchor(r, 50.0);
        setLeftAnchor(r, 50.0);
        getChildren().addAll(r);

        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(3));
        tt.setFromX(0);
        tt.setToX(600);

        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(3));
        rt.setFromAngle(0);
        rt.setToAngle(360);

        pt = new ParallelTransition();
        pt.setNode(r);
        pt.getChildren().addAll(tt, rt);

        pt.setInterpolator(Interpolator.LINEAR);

        pt.setAutoReverse(true);
        pt.setCycleCount(Animation.INDEFINITE);

        controlDemo();
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
                pt.play();
                ol("播放");
            }
        });
        tb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pt.pause();
                ol("暂停");
            }
        });
        tb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pt.stop();
                ol("停止");
            }
        });
        tb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pt.playFromStart();
                ol("重播");
                pt.getCurrentTime().toSeconds();
            }
        });

        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tb1, tb2, tb3, tb4);
        getChildren().add(h);
    }

}
