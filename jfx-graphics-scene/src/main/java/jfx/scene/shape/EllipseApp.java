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
import javafx.scene.shape.Ellipse;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class EllipseApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Ellipse 椭圆形";

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

        Ellipse e1 = new Ellipse();
        e1.setRadiusX(70);
        e1.setRadiusY(50);

        Ellipse e2 = new Ellipse();
        e2.setRadiusX(50);
        e2.setRadiusY(70);
        e2.setFill(Color.DARKSEAGREEN);
        e2.setSmooth(true);

        Ellipse e3 = new Ellipse();
        e3.setFill(Color.BURLYWOOD);

        Ellipse e4 = new Ellipse();
        e4.setFill(Color.BLANCHEDALMOND);

        hBox.getChildren().addAll(e1, e2, e3, e4);
        getChildren().add(hBox);

        ol(e4.getLayoutBounds());
        ol(e4.getBoundsInLocal());
        ol(e4.getBoundsInParent());
    }

}
