/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfx.core.app.ContentBox;

/**
 * B78
 *
 * @author passpos <paiap@outlook.com>
 */
public class StageDemo extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Stage - 拖拽Stage";
    public Stage stage;
    public Scene scene;
    public AnchorPane root;
    public double sceneX;
    public double sceneY;

    @Override
    public void index() {
        fillData();
        transparentRadius();
        setAction();
    }

    public void fillData() {
        stage = new Stage();
        stage.setWidth(300);
        stage.setHeight(200);
        stage.setAlwaysOnTop(true);

        root = new AnchorPane();
        scene = new Scene(root);
        stage.setScene(scene);

        Button btn1 = new Button("触发Stage");
        getChildren().add(btn1);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.show();
            }
        });

        Button btn2 = new Button("隐藏Stage");
        getChildren().add(btn2);
        setLeftAnchor(btn2, 150.0);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.hide();
            }
        });
    }

    /**
     * 设置圆角窗体
     *
     * - 所谓的圆角窗体其实是：圆角根节点；
     * - 场景图默认是白色的，但可以填充为透明色；
     * - 窗体必须设置样式风格为：StageStyle.TRANSPARENT；
     *
     * 通过这三点就可以实现圆角窗体；
     */
    public void transparentRadius() {
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#993333"), new CornerRadii(15), Insets.EMPTY));
        root.setBackground(bg);

        // 透明场景；
        scene.setFill(Paint.valueOf("#ffffff00"));

        // 透明窗体
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    public void setAction() {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                // 获取窗体左上角在屏幕中的坐标；
                double stageX = stage.getX();
                double stageY = stage.getY();
                ol("窗体左上角坐标X - " + stageX);
                ol("窗体左上角坐标Y - " + stageY);

                // 获取指针在显示屏幕中的坐标；
                double pointX = t.getScreenX();
                double pointY = t.getScreenY();

                // 获取指针相对于窗体左上角的坐标；
                sceneX = t.getSceneX();
                sceneY = t.getSceneY();

                // 下面两项相等：
                ol(pointX - stageX);
                ol(sceneX);
                ol((pointY - stageY) == sceneY);
            }
        });

        /* 这里，t.getScreenX()、t.getScreenY()会随着（按下的）指针移动而变化；
         * 上面的按下事件则不是；
         */
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                double x = t.getScreenX() - sceneX;
                double y = t.getScreenY() - sceneY;
                stage.setX(x);
                stage.setY(y);
            }
        });

        // 阻止窗体位置超出屏幕的上边框与左边框
        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                double x = stage.getX();
                double y = stage.getY();
                if (x < 0) {
                    stage.setX(0);
                } else {
                    stage.setX(x);
                }
                if (y < 0) {
                    stage.setY(0);
                } else {
                    stage.setY(y);
                }
            }
        });
    }
}
