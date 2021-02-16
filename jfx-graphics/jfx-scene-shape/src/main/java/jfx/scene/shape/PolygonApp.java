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
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PolygonApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Polygon 多边形";

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

        // 不需要指定结尾点与起始点坐标相同；
        Polygon p1 = new Polygon(0, 0, 40, 30, 20, 100);

        Polygon p2 = new Polygon();
        p2.setFill(Color.DARKSEAGREEN);
        p2.getPoints().addAll(0.0, 50.0, 40.0, 0.0, 60.0, 50.0);

        Polygon p3 = new Polygon();
        p3.setFill(Color.DARKSEAGREEN);

        hBox.getChildren().addAll(p1, p2, p3);
        getChildren().add(hBox);

    }

}
