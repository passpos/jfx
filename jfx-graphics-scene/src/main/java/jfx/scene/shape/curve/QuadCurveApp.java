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
package jfx.scene.shape.curve;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class QuadCurveApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - QuadCurve 二次曲线";

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

        QuadCurve e1 = new QuadCurve(0, 0, 10, 50, 70, 60);
        e1.setStrokeWidth(3);

        QuadCurve e2 = new QuadCurve();
        e2.setStrokeWidth(3);
        e2.setFill(Color.AZURE);
        e2.setStartX(0);
        e2.setStartY(0);
        e2.setControlX(0);
        e2.setControlY(100);
        e2.setEndX(70);
        e2.setEndY(60);

        QuadCurve e3 = new QuadCurve(0, 0, 35, 35, 70, 0);

        QuadCurve e4 = new QuadCurve();

        hBox.getChildren().addAll(e1, e2, e3, e4);
        getChildren().add(hBox);

        ol(e4.getLayoutBounds());
        ol(e4.getBoundsInLocal());
        ol(e4.getBoundsInParent());
    }

}
