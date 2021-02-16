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
import javafx.scene.shape.Circle;
import jfx.core.app.ContentBox;

/**
 * 圆形的布局方式是以圆心为基准，而不是圆形的外切矩形的左上角；
 * @author realpai <paiap@outlook.com>
 */
public class CircleApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Circle 圆形";

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

        Circle c1 = new Circle();
        c1.setCenterX(0);
        c1.setCenterY(0);
        c1.setRadius(50);
        // 设置抗锯齿（默认开启）
        c1.setSmooth(false);

        Circle c2 = new Circle(70);
        c2.setFill(Color.DARKSEAGREEN);
        c2.setSmooth(true);

        Circle c3 = new Circle(30);
        c3.setFill(Color.BURLYWOOD);

        Circle c4 = new Circle(50);
        c4.setFill(Color.BLANCHEDALMOND);

        hBox.getChildren().addAll(c1, c2, c3, c4);
        getChildren().add(hBox);

        ol(c4.getLayoutBounds());
        ol(c4.getBoundsInLocal());
        ol(c4.getBoundsInParent());
    }

}
