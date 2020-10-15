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
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * Bloom - 基于可配置的阈值，使输入图像的较亮部分看起来发光的高级效果。
 * Glow - 基于可配置的阈值，使输入图像看起来发光的高级效果。
 * @author realpai <paiap@outlook.com>
 */
public class BloomAndGlowApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - BloomAndGlow";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        Bloom b1 = new Bloom();
        Bloom b2 = new Bloom();
        Glow g1 = new Glow();
        Glow g2 = new Glow();

        Button btn = new Button("按钮");
        btn.setEffect(b1);

        Text text = new Text("这是一行文本");
        // 0.0-1.0，默认值为0.3
        b2.setThreshold(0.7);
        text.setFont(new Font(20));
        text.setEffect(b2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        // 0.0-1.0，默认为0.3
        g1.setLevel(0.5);
        r.setEffect(g1);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        g2.setLevel(0.1);
        circle.setEffect(g2);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }
}
