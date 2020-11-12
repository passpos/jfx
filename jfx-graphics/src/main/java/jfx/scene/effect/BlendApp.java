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
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 * 图层混合
 * @author realpai <paiap@outlook.com>
 */
public class BlendApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - Blend";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Blend pt1 = new Blend();
        Blend pt2 = new Blend();
        Blend pt3 = new Blend();
        Blend pt4 = new Blend();

        // 图层
        Button btn1 = new Button("按钮");
        btn1.setEffect(pt1);

        Rectangle r1 = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        r1.setEffect(pt2);

        HBox hBox1 = new HBox(50);
        hBox1.setAlignment(Pos.CENTER);
        setTopAnchor(hBox1, 50.0);
        setLeftAnchor(hBox1, 50.0);
        hBox1.getChildren().addAll(btn1, r1);

        // 图层
        Button btn2 = new Button("按钮");
        pt3.setMode(BlendMode.HARD_LIGHT);
        btn2.setEffect(pt3);

        Rectangle r2 = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        pt4.setMode(BlendMode.ADD);
        r2.setEffect(pt4);

        HBox hBox2 = new HBox(50);
        hBox2.setAlignment(Pos.CENTER);
        setTopAnchor(hBox2, 50.0);
        setLeftAnchor(hBox2, 50.0);
        hBox2.getChildren().addAll(btn2, r2);

        // 混合
        StackPane sp = new StackPane();
        sp.getChildren().addAll(hBox1, hBox2);

        getChildren().add(hBox2);
    }

}
