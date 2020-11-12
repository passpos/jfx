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
import javafx.scene.effect.ColorAdjust;
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
public class ColorAdjustApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - ColorAdjust";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        ColorAdjust ca1 = new ColorAdjust();
        ColorAdjust ca2 = new ColorAdjust();
        ColorAdjust ca3 = new ColorAdjust();
        ColorAdjust ca4 = new ColorAdjust();

        Button btn = new Button("按钮");
        btn.setEffect(ca1);

        Text text = new Text("这是一行文本");
        // 色调：-1.0 - 1.0，默认为0.0
        ca2.setHue(1);
        // 亮度：-1.0 - 1.0，默认为0.0
        ca2.setBrightness(1);
        // 饱和度：-1.0 - 1.0，默认为0.0
        ca2.setSaturation(1);
        // 对比度：-1.0 - 1.0，默认为0.0
        ca2.setContrast(1);
        text.setFont(new Font(20));
        text.setEffect(ca2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        ca3.setHue(-1);
        ca3.setBrightness(-1);
        ca3.setSaturation(-1);
        ca3.setContrast(-1);
        r.setEffect(ca3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(ca4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
