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
package jfx.scene.effect.blur;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BoxBlurApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - BoxBlur";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        BoxBlur bb1 = new BoxBlur();
        BoxBlur bb2 = new BoxBlur();
        BoxBlur bb3 = new BoxBlur();
        BoxBlur bb4 = new BoxBlur();

        Button btn = new Button("按钮");
        btn.setEffect(bb1);

        Text text = new Text("这是一行文本");
        // 宽高设置范围0-255，默认5.0
        bb2.setWidth(50);
        // bb2.setHeight(50);
        text.setFont(new Font(20));
        text.setEffect(bb2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        // 0-3，默认为1
        bb3.setIterations(2);
        r.setEffect(bb3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(bb4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }
}
