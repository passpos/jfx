/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.animation.transition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PathTransitionApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - PathTransition";
    private PathTransition pt;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Rectangle r = new Rectangle(50, 50, Color.BLANCHEDALMOND);
        // 设置中心点坐标为左上角（默认为中心位置）
        r.setX(-25);
        r.setY(-25);
        setTopAnchor(r, 50.0);
        setLeftAnchor(r, 50.0);

        MoveTo mt = new MoveTo();
        QuadCurveTo qct = new QuadCurveTo(100, 0, 300, 300);
        VLineTo vlt = new VLineTo(100);
        HLineTo hlt = new HLineTo(0);
        ClosePath cp = new ClosePath();

        Path p = new Path();
        p.setStroke(Color.BISQUE);
        p.setFillRule(FillRule.EVEN_ODD);
        p.getElements().addAll(mt, qct, vlt, hlt, cp);

        // 将路径置于此布局，以使相对路径转换为绝对路径；
        StackPane sp = new StackPane(p);
        setTopAnchor(sp, 50.0);
        setLeftAnchor(sp, 50.0);
        getChildren().addAll(r, sp);

        pt = new PathTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setNode(r);

        pt.setPath(p);
        pt.setInterpolator(Interpolator.LINEAR);
        // 设置运动方向总是垂直于路径的切线
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

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
