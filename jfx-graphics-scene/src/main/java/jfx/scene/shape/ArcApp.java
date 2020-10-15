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
package jfx.scene.shape;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ArcApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Arc 弧形";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 20.0);

        Arc a1 = new Arc();
        a1.setRadiusX(70);
        a1.setRadiusY(50);
        // 弧形在原图中所占的角度
        a1.setLength(45);

        Arc a2 = new Arc();
        a2.setFill(Color.DARKSEAGREEN);
        a2.setRadiusX(50);
        a2.setRadiusY(70);
        a2.setLength(90);
        a2.setType(ArcType.ROUND);

        Arc a3 = new Arc();
        a3.setFill(Color.DARKSEAGREEN);
        a3.setRadiusX(50);
        a3.setRadiusY(70);
        a3.setLength(90);
        // 从原图切出弧的角度（弧在原图中的位置）
        a3.setStartAngle(90);
        a3.setType(ArcType.ROUND);

        Arc a4 = new Arc();
        a4.setFill(Color.BURLYWOOD);
        a4.setRadiusX(50);
        a4.setRadiusY(70);
        // 扇形所占的角度
        a4.setLength(180);
        // 弧形未被弦闭合
        a4.setType(ArcType.OPEN);

        Arc a5 = new Arc();
        a5.setFill(Color.BLANCHEDALMOND);
        a5.setRadiusX(50);
        a5.setRadiusY(70);
        a5.setLength(90);
        // 弧形与弦形成闭合图形
        a5.setType(ArcType.CHORD);

        hBox.getChildren().addAll(a1, a2, a3, a4, a5);
        getChildren().add(hBox);

        ol(a5.getLayoutBounds());
        ol(a5.getBoundsInLocal());
        ol(a5.getBoundsInParent());
    }

}
