/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.animation.transition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
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
public class ScaleTransitionApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - ScaleTransition";
    private ScaleTransition st;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Rectangle r = new Rectangle(50, 50, Color.BLANCHEDALMOND);
        setTopAnchor(r, 50.0);
        setLeftAnchor(r, 50.0);
        getChildren().addAll(r);

        st = new ScaleTransition();
        st.setDuration(Duration.seconds(3));
        st.setNode(r);

        st.setFromX(1);
        st.setToX(3);
        st.setInterpolator(Interpolator.LINEAR);

        st.setAutoReverse(true);
        st.setCycleCount(Animation.INDEFINITE);

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
                st.play();
                ol("播放");
            }
        });
        tb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                st.pause();
                ol("暂停");
            }
        });
        tb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                st.stop();
                ol("停止");
            }
        });
        tb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                st.playFromStart();
                ol("重播");
                st.getCurrentTime().toSeconds();
            }
        });

        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tb1, tb2, tb3, tb4);
        getChildren().add(h);
    }

}