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
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * 产生棕褐色调效果的滤镜，类似于古董照片。
 *
 * @author realpai <paiap@outlook.com>
 */
public class SepiaToneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - SepiaTone";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        SepiaTone st1 = new SepiaTone();
        SepiaTone st2 = new SepiaTone();
        SepiaTone st3 = new SepiaTone();
        SepiaTone st4 = new SepiaTone();

        Button btn = new Button("按钮");
        btn.setEffect(st1);

        Text text = new Text("这是一行文本");
        // 0.0f-1.0f，默认值为1.0f
        st2.setLevel(0.7);
        text.setFont(new Font(20));
        text.setEffect(st2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        st3.setLevel(0.5);
        r.setEffect(st3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        st4.setLevel(0.3);
        circle.setEffect(st4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
