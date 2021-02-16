/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.animation.demo;

import fx.image.ImageMaker;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Snow extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Animation - Demo 落雪";
    private SubScene subScene;
    private AnchorPane secondaryRoot;
    private Random random;
    private ArrayList<ImageView> list;
    private ArrayList<ParallelTransition> pt_list;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Button b1 = new Button("开始");
        Button b2 = new Button("暂停");
        Button b3 = new Button("停止");
        HBox hBox = new HBox(b1, b2, b3);
        hBox.setSpacing(10);
        getChildren().add(hBox);

        random = new Random();

        secondScene();
        // 播放
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (pt_list == null || pt_list.isEmpty()) {
                    return;
                }
                if (secondaryRoot.getChildren().isEmpty()) {
                    secondaryRoot.getChildren().addAll(list);
                }
                pt_list.forEach(new Consumer<ParallelTransition>() {
                    @Override
                    public void accept(ParallelTransition t) {
                        t.play();
                    }
                });
            }
        });
        // 暂停
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (pt_list == null || pt_list.isEmpty()) {
                    return;
                }
                pt_list.forEach(new Consumer<ParallelTransition>() {
                    @Override
                    public void accept(ParallelTransition t) {
                        t.pause();
                    }
                });
            }
        });
        // 停止
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (pt_list == null || pt_list.isEmpty()) {
                    return;
                }
                pt_list.forEach(new Consumer<ParallelTransition>() {
                    @Override
                    public void accept(ParallelTransition t) {
                        t.stop();
                    }
                });
            }
        });
    }

    public void secondScene() {
        // 背景
        Image image = ImageMaker.getImageFromResource("imgs/demo/1.jpg");
        ImageView iv = new ImageView(image);

        StackPane sp = new StackPane();
        iv.setPreserveRatio(true);
        iv.fitWidthProperty().bind(prefWidthProperty());
        setTopAnchor(sp, 30.0);

        // 前景动画
        secondaryRoot = new AnchorPane();
        secondaryRoot.setStyle("-fx-background-color:#44333300");

        subScene = new SubScene(secondaryRoot, this.getWidth(), this.getHeight(), true, SceneAntialiasing.BALANCED);
        subScene.setCamera(new PerspectiveCamera());

        createSnow();
        setAnimation();
        secondaryRoot.getChildren().addAll(list);

        sp.getChildren().addAll(iv, subScene, secondaryRoot);
        getChildren().add(sp);
    }

    /**
     * 设置Z轴坐标，通过场景的摄像机，使得雪花呈现不同的远近和大小效果。
     * 但是由于还要设置背景图片，且背景图片必须铺满窗口，就必须使用
     * SubScene，SubScene是节点类型，可以被添加到其他（父）节点中，例如：
     * StackPane中。这样，父节点设置的背景图片大小，就不会受到3D场景远近
     * 效果的影响。
     *
     * 如果直接通过使用缩放效果，使雪花呈现不同大小，这么做会丢失一些
     * 真实性：
     * 1. 雪花不会重合。真实的3D场景中，是会发生重合的；
     * 2. 不同大小的雪花，观察到的下落速度应该呈现一定的自然差异性；
     */
    protected void createSnow() {
        // 创建雪花碎片
        list = new ArrayList<>();

        // 雪花出现时的定位
        int locationX;
        int locationY;
        int locationZ;

        for (int i = 0; i < 100; i++) {
            ImageView iv = new ImageView(ImageMaker.getImageFromResource("imgs/demo/snow1.png"));
            iv.setPreserveRatio(true);
            double fw = 0;
            while (fw < 10) {
                fw = random.nextDouble() * 30;
            }
            iv.setFitWidth(fw);

            if (random.nextBoolean()) {
                locationX = random.nextInt(600) + random.nextInt(400);
            } else {
                locationX = random.nextInt(600) - random.nextInt(300);
            }
            locationY = random.nextInt(200);

            locationZ = random.nextInt(600);
            double d = random.nextDouble();

            iv.setTranslateX(locationX);
            iv.setTranslateY(locationY);
            iv.setTranslateZ(locationZ);
            iv.setOpacity(0);

            list.add(iv);
        }
    }

    protected void setAnimation() {
        // 设置雪花动画
        pt_list = new ArrayList<>();
        list.forEach(new Consumer<ImageView>() {
            @Override
            public void accept(ImageView t) {
                double time = random.nextDouble() * 10 + 5;

                // 飘移到XY
                TranslateTransition tt = new TranslateTransition(Duration.seconds(time));
                tt.setFromX(t.getTranslateX());
                tt.setFromY(t.getTranslateY());
                tt.setByX(200);
                tt.setByY(500);

                // 自旋
                RotateTransition rt = new RotateTransition(Duration.seconds(time));
                rt.setFromAngle(0);
                rt.setToAngle(180);

                // 渐显、渐隐
                FadeTransition ft1 = new FadeTransition(Duration.seconds(time / 2));
                ft1.setFromValue(0);
                ft1.setToValue(1);

                FadeTransition ft2 = new FadeTransition(Duration.seconds(time / 2));
                ft2.setFromValue(1);
                ft2.setToValue(0);

                SequentialTransition st = new SequentialTransition();
                st.getChildren().addAll(ft1, ft2);
                st.setNode(t);

                ParallelTransition pt = new ParallelTransition();
                pt.setNode(t);
                pt.getChildren().addAll(tt, rt, st);
                pt.setCycleCount(Animation.INDEFINITE);

                pt_list.add(pt);

            }
        });
    }
}
