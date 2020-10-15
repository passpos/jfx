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
package jfx.scene.shape.line;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class LineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Line";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 20.0);

        Line l1 = new Line(0, 0, 100, 100);
        // 设置线段颜色
        l1.setStroke(Paint.valueOf("#CD0000"));
        // 设置线段宽度（粗细）
        l1.setStrokeWidth(5);

        Line l2 = new Line(0, 0, 50, 100);
        l2.setStrokeWidth(10);
        // 设置多条线段首位相接时的衔接方式，默认为SQUARE；
        l2.setStrokeLineCap(StrokeLineCap.BUTT);

        Line l3 = new Line(50, 100, 100, 0);
        l3.setStrokeWidth(10);
        l3.setStrokeLineCap(StrokeLineCap.BUTT);

        Line l4 = new Line(100, 0, 150, 100);
        l4.setStrokeWidth(10);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);

        Line l5 = new Line(150, 100, 200, 0);
        l5.setStrokeWidth(10);
        l5.setStrokeLineCap(StrokeLineCap.ROUND);

        Line l6 = new Line(200, 0, 250, 100);
        l6.setStrokeWidth(10);
        l6.setStrokeLineCap(StrokeLineCap.SQUARE);
        AnchorPane ap = new AnchorPane(l2, l3, l4, l5, l6);

        // 虚线与偏移
        Line l7 = new Line(0, 0, 50, 100);
        l7.setStrokeWidth(3);
        l7.getStrokeDashArray().addAll(15.0, 5.0);

        Line l8 = new Line(0, 0, 50, 100);
        l8.setStrokeWidth(3);
        l8.getStrokeDashArray().addAll(15.0, 5.0);
        l8.setStrokeDashOffset(10);

        hBox.getChildren().addAll(l1, ap, l7, l8);
        getChildren().add(hBox);
    }

}
