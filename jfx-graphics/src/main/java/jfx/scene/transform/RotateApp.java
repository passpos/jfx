/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.transform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import jfx.core.app.ContentBox;

/**
 * 旋转转换
 * Rotate()
 * Rotate​(double angle)
 * Rotate​(double angle, double pivotX, double pivotY)
 * - 使用 轴 创建二维旋转变换；
 * Rotate​(double angle, double pivotX, double pivotY, double pivotZ)
 * - 参数1： 旋转角度（允许为负）；
 * - 参数2： 旋转中心点 X 轴坐标；
 * - 参数3： 旋转中心点 Y 轴坐标；
 * - 参数4： 旋转中心点 Z 轴坐标；
 * Rotate​(double angle, double pivotX, double pivotY, double pivotZ, Point3D axis)
 * - 使用 枢轴 创建三维旋转变换；
 * Rotate​(double angle, Point3D axis)
 * - 创建三维旋转变换。
 * @author realpai <paiap@outlook.com>
 */
public class RotateApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Transform - Rotate 旋转";
    private Rotate r;
    private Button btn;

    @Override
    public void index() {
        baseDemo();
        rotateRoot();
        resume();
    }

    public void baseDemo() {
        r = new Rotate(45, 0, 0);

        btn = new Button("按钮");
        setLeftAnchor(btn, 100.0);
        setTopAnchor(btn, 100.0);
        getChildren().add(btn);

        btn.getTransforms().add(r);
    }

    public void rotateRoot() {
        getTransforms().add(r);
    }

    public void resume() {
        Rotate rotate = new Rotate(-45, 0, 0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getTransforms().add(rotate);
            }
        });
    }
}
