/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.animation;

import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoTimelineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - Demo Timeline";
    private ArrayList<Timeline> timelines;

    @Override
    public void index() {
        timelines = new ArrayList<>();
        baseDemo();
    }

    public void baseDemo() {
        Rectangle r1 = new Rectangle(50, 50, Color.BLANCHEDALMOND);
        Rectangle r2 = new Rectangle(50, 50, Color.FIREBRICK);
        Rectangle r3 = new Rectangle(50, 50, Color.CADETBLUE);
        Rectangle r4 = new Rectangle(50, 50, Color.CADETBLUE);
        setTopAnchor(r1, 50.0);
        setTopAnchor(r2, 150.0);
        setTopAnchor(r3, 250.0);
        setTopAnchor(r4, 250.0);
        setLeftAnchor(r4, 100.0);
        getChildren().addAll(r1, r2, r3, r4);

        // r1的动画
        KeyFrame kf1 = getDemoFrame(r1, 0, 0, 0, 0, 1, 1, 1);
        KeyFrame kf2 = getDemoFrame(r1, 1, 300, 0, 360, 0.1, 0.1, 0.2);

        Timeline tl1 = new Timeline();
        tl1.getKeyFrames().addAll(kf1, kf2);

        // r2的动画
        KeyFrame sf1 = getStepFrame(r2, 0, 0, 0);
        KeyFrame sf2 = getStepFrame(r2, 1, 0, 360);
        KeyFrame sf3 = getStepFrame(r2, 2, 300, 360);

        Timeline tl2 = new Timeline();
        tl2.getKeyFrames().addAll(sf1, sf2, sf3);

        // r3的动画
        Scale scale = new Scale();
        r3.getTransforms().add(scale);
        KeyFrame tf1 = getTransformFrame(scale, 0, 1, 1);
        KeyFrame tf2 = getTransformFrame(scale, 1, 0.3, 0.3);

        Timeline tl3 = new Timeline();
        tl3.getKeyFrames().addAll(tf1, tf2);

        // r4的动画
        Rotate rot = new Rotate(90, 0, 0);
        r4.getTransforms().add(rot);
        KeyFrame tf3 = getTransformFrame(rot, 0, 90);
        KeyFrame tf4 = getTransformFrame(rot, 1, 180);

        Timeline tl4 = new Timeline();
        tl4.getKeyFrames().addAll(tf3, tf4);

        timelines.add(tl1);
        timelines.add(tl2);
        timelines.add(tl3);
        timelines.add(tl4);
        settingsDemo();
        controlDemo();
    }

    public void settingsDemo() {
        timelines.forEach(new Consumer<Timeline>() {
            @Override
            public void accept(Timeline t) {
                // 无限循环
                t.setCycleCount(Timeline.INDEFINITE);

                // 自动反向播放（反向播放也算一次）
                t.setAutoReverse(true);

                // 播放速率
                t.setRate(1);
            }
        });
    }

    private void controlDemo() {
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
                timelines.forEach(new Consumer<Timeline>() {
                    @Override
                    public void accept(Timeline t) {
                        t.play();
                    }
                });
                ol("播放");
            }
        });
        tb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                timelines.forEach(new Consumer<Timeline>() {
                    @Override
                    public void accept(Timeline t) {
                        t.pause();
                    }
                });
                ol("暂停");
            }
        });
        tb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                timelines.forEach(new Consumer<Timeline>() {
                    @Override
                    public void accept(Timeline t) {
                        t.stop();
                    }
                });
                ol("停止");
            }
        });
        tb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                timelines.forEach(new Consumer<Timeline>() {
                    @Override
                    public void accept(Timeline t) {
                        t.playFromStart();
                    }
                });
                ol("重播");
            }
        });

        HBox h = new HBox();
        h.setSpacing(10);
        h.getChildren().addAll(tb1, tb2, tb3, tb4);
        getChildren().add(h);
    }

    private KeyFrame getDemoFrame(Rectangle r, double duration, double translateX, double translateY, double rotate, double scaleX, double scaleY, double opacity) {
        KeyValue kva = new KeyValue(r.translateXProperty(), translateX);
        KeyValue kvb = new KeyValue(r.translateYProperty(), translateY);
        KeyValue kvc = new KeyValue(r.rotateProperty(), rotate);
        KeyValue kvd = new KeyValue(r.scaleXProperty(), scaleX);
        KeyValue kve = new KeyValue(r.scaleYProperty(), scaleY);
        KeyValue kvf = new KeyValue(r.opacityProperty(), opacity);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), kva, kvb, kvc, kvd, kve, kvf);
        return kf;
    }

    private KeyFrame getTranslateFrame(Rectangle r, double duration, double translateX, double translateY) {
        KeyValue kva = new KeyValue(r.translateXProperty(), translateX);
        KeyValue kvb = new KeyValue(r.translateYProperty(), translateY);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), kva, kvb);
        return kf;
    }

    private KeyFrame getStepFrame(Rectangle r, double duration, double translateX, double rotate) {
        KeyValue kva = new KeyValue(r.translateXProperty(), translateX);
        KeyValue kvb = new KeyValue(r.rotateProperty(), rotate);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), kva, kvb);
        return kf;
    }

    private KeyFrame getTransformFrame(Scale scale, double duration, double x, double y) {
        KeyValue kva = new KeyValue(scale.xProperty(), x);
        KeyValue kvb = new KeyValue(scale.yProperty(), y);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), kva, kvb);
        return kf;
    }

    private KeyFrame getTransformFrame(Rotate rot, double duration, double angle) {
        KeyValue kva = new KeyValue(rot.angleProperty(), angle);
        KeyFrame kf = new KeyFrame(Duration.seconds(duration), kva);
        return kf;
    }
}
