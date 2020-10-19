/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.effect.shadow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * 内阴影：阴影将出现在节点内部
 * @author realpai <paiap@outlook.com>
 */
public class InnerShadowApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - InnerShadow 内阴影";

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

        InnerShadow is1 = new InnerShadow();
        InnerShadow is2 = new InnerShadow();
        InnerShadow is3 = new InnerShadow();
        InnerShadow is4 = new InnerShadow();

        Button btn = new Button("按钮");
        btn.setEffect(is1);

        Text text = new Text("这是一行文本");
        is2.setColor(Color.CORAL);
        is2.setOffsetX(20);
        is2.setOffsetY(20);
        text.setFont(new Font(20));
        text.setEffect(is2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        // 阴影的瓶颈（0-1）
        is3.setChoke(0.5);
        is3.setOffsetX(20);
        is3.setOffsetY(20);
        is3.setRadius(10);
        r.setEffect(is3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        is4.setOffsetX(20);
        is4.setOffsetY(20);
        is4.setBlurType(BlurType.GAUSSIAN);
        is4.setColor(Color.valueOf("#2B2B2B55"));
        // 叠加效果：
        // is4.setInput(is3);
        circle.setEffect(is4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);

    }

}
