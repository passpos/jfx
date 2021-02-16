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
import javafx.scene.effect.GaussianBlur;
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
public class GaussianBlurApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - GaussianBlur";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color:#FFFFFF");
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        GaussianBlur gb1 = new GaussianBlur();
        GaussianBlur gb2 = new GaussianBlur();
        GaussianBlur gb3 = new GaussianBlur();
        GaussianBlur gb4 = new GaussianBlur();

        Button btn = new Button("按钮");
        btn.setEffect(gb1);

        Text text = new Text("这是一行文本");
        // 0-63，默认为10.0
        gb2.setRadius(40);
        text.setFont(new Font(20));
        text.setEffect(gb2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        r.setEffect(gb3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(gb4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
