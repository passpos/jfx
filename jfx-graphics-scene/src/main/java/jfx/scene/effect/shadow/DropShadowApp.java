/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.effect.shadow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * 外阴影：阴影将出现在节点外部
 * @author realpai <paiap@outlook.com>
 */
public class DropShadowApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - DropShadow 外阴影";

    @Override
    public void index() {
        baseDemo();
    }

    /**
     * 当父组件设置了背景颜色时，子组件的效果就会消失，并转移到父组件
     */
    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        DropShadow ds1 = new DropShadow();
        DropShadow ds2 = new DropShadow();
        DropShadow ds3 = new DropShadow();
        DropShadow ds4 = new DropShadow();

        Button btn = new Button("按钮");
        btn.setEffect(ds1);

        Text text = new Text("这是一行文本");
        ds2.setColor(Color.CORAL);
        ds2.setWidth(100);
        text.setEffect(ds2);

        // 图形透明度会影响背影暗度；
        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF44"));
        ds3.setOffsetX(20);
        ds3.setOffsetY(20);
        // 阴影半径（数值越大，阴影轮廓越圆润）
        ds3.setRadius(30);
        r.setEffect(ds3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        // 阴影在水平和垂直方向上的偏移量
        ds4.setOffsetX(20);
        ds4.setOffsetY(20);
        // 模糊类型
        ds4.setBlurType(BlurType.GAUSSIAN);
        // 阴影颜色（允许包含透明度）
        ds4.setColor(Color.valueOf("#2B2B2B55"));
        // 阴影边缘是从1“逐渐”消逝为0的；
        ds4.setSpread(0.5);
        circle.setEffect(ds4);


        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);

    }
}
