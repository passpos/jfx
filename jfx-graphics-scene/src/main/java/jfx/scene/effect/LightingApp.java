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
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
public class LightingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - Lighting";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        Lighting pt1 = new Lighting();
        Lighting pt2 = new Lighting();
        Lighting pt3 = new Lighting();
        Lighting pt4 = new Lighting();

        Button btn = new Button("按钮");
        btn.setEffect(pt1);

        Text text = new Text("这是一行文本");
        Light.Distant ld = new Light.Distant();
        // 设置光源颜色
        ld.setColor(Color.WHITESMOKE);
        // 设置光照角度
        ld.setAzimuth(-45);
        pt2.setLight(ld);
        pt2.setBumpInput(pt3);
        text.setFont(new Font(36));
        text.setEffect(pt2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        // 立体效果包含的范围0-10.0，默认1.5
        pt3.setSurfaceScale(7);
        // 漫反射0.0-2.0，默认1.0
        pt3.setDiffuseConstant(1.5);
        // 漫反射0.0-2.0，默认0.3
        pt3.setSpecularConstant(1.5);
        r.setEffect(pt3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        // 高光0.0-40.0，默认20.0
        pt4.setSpecularExponent(40);
        circle.setEffect(pt4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
