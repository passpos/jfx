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
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;
import jfx.utils.app.ContentBox;

/**
 * SVG path类表示通过从字符串解析SVG路径数据而构造的简单形状。
 * @author realpai <paiap@outlook.com>
 */
public class SVGPathApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - SVGPath";

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

        SVGPath p1 = new SVGPath();
        p1.setStroke(Color.CORNFLOWERBLUE);
        p1.setContent("m0,0 l100,100");

        SVGPath p2 = new SVGPath();
        p2.setFill(Color.BLUEVIOLET);
        p2.setStroke(Color.BISQUE);
        p2.getStrokeDashArray().addAll(5.0, 5.0);
        p2.setFillRule(FillRule.EVEN_ODD);
        p2.setContent("m0,0 l100,100 v100 h-100 z");

        SVGPath p3 = new SVGPath();
        p3.setContent("m0,0 q100,50,200,0 t200,0");

        SVGPath p4 = new SVGPath();

        hBox.getChildren().addAll(p1, p2, p3, p4);
        getChildren().add(hBox);

    }

}
