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
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 * 提供输入内容的非仿射转换的效果。最典型的透视变换用于为其他二维内容提供“人造”三维效果。
 * @author realpai <paiap@outlook.com>
 */
public class PerspectiveTransformApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - PerspectiveTransform";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        PerspectiveTransform pt1 = new PerspectiveTransform();
        PerspectiveTransform pt2 = new PerspectiveTransform();
        PerspectiveTransform pt3 = new PerspectiveTransform();
        PerspectiveTransform pt4 = new PerspectiveTransform();

        Button btn = new Button("按钮");
        btn.setEffect(pt1);

        Text text = new Text("这是一行文本");
        // 上左、上右
        pt2.setUlx(0);
        pt2.setUly(0);
        pt2.setUrx(100);
        pt2.setUry(0);

        // 下左、下右
        pt2.setLlx(0);
        pt2.setLly(100);
        pt2.setLrx(100);
        pt2.setLry(100);
        text.setFont(new Font(20));
        text.setEffect(pt2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        pt3.setUlx(0);
        pt3.setUly(0);
        pt3.setUrx(100);
        pt3.setUry(30);
        pt3.setLlx(0);
        pt3.setLly(100);
        pt3.setLrx(100);
        pt3.setLry(70);
        r.setEffect(pt3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(pt4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
