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
import javafx.scene.effect.Reflection;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
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
public class ReflectionApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - Reflection";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        Reflection r1 = new Reflection();
        Reflection r2 = new Reflection();
        Reflection r3 = new Reflection();
        Reflection r4 = new Reflection();

        Button btn = new Button("按钮");
        btn.setEffect(r1);

        Text text = new Text("这是一行文本");
        // 节点与倒影之间的距离
        r2.setTopOffset(20);
        // 倒影（上部）不透明度
        r2.setTopOpacity(0.5);
        text.setFont(new Font(20));
        text.setEffect(r2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        // 倒影（下部）不透明度
        r3.setBottomOpacity(0.1);
        r.setEffect(r3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        // 倒影只有原节点的一半
        r4.setFraction(0.5);
        circle.setEffect(r4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
