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
package jfx.scene.shape.path;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.VLineTo;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PathApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Path 路径";

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

        Path p1 = new Path();
        MoveTo mt1 = new MoveTo(0, 0);
        LineTo lt1 = new LineTo(50, 0);
        p1.getElements().addAll(mt1, lt1);
        QuadCurveTo qct1 = new QuadCurveTo(100, 0, 100, 100);
        p1.getElements().addAll(qct1);

        Path p2 = new Path();
        MoveTo mt2 = new MoveTo();
        QuadCurveTo qct2 = new QuadCurveTo(100, 0, 100, 100);
        // HLineTo、VLineTo中的分别是X轴、Y轴坐标值，而非线段长度；
        VLineTo vlt1 = new VLineTo(30);
        HLineTo hlt1 = new HLineTo(0);
        ClosePath cp1 = new ClosePath();
        p2.getElements().addAll(mt2, qct2, vlt1, hlt1, cp1);
        p2.setFill(Color.BLUEVIOLET);
        p2.setStroke(Color.BISQUE);
        p2.getStrokeDashArray().addAll(5.0, 5.0);
        p2.setFillRule(FillRule.EVEN_ODD);

        Path p3 = new Path();
        Path p4 = new Path();

        hBox.getChildren().addAll(p1, p2, p3, p4);
        getChildren().add(hBox);

    }

}
