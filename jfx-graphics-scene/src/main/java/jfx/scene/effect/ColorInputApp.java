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
package jfx.scene.effect;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * 渲染矩形区域的效果，该矩形区域被给定的绘制填充（“淹没”）。
 * @author realpai <paiap@outlook.com>
 */
public class ColorInputApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - ColorInput";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        ColorInput ci1 = new ColorInput();
        ColorInput ci2 = new ColorInput();
        ColorInput ci3 = new ColorInput();
        ColorInput ci4 = new ColorInput();

        Button btn = new Button("按钮");
        btn.setEffect(ci1);

        Text text = new Text("这是一行文本");
        ci2.setWidth(50);
        ci2.setHeight(50);
        ci2.setPaint(Paint.valueOf("9AFF9A"));
        text.setFont(new Font(20));
        text.setEffect(ci2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        ci3.setX(10);
        ci3.setY(10);
        ci3.setPaint(Paint.valueOf("9AFF9A00"));
        r.setEffect(ci3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(ci4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
