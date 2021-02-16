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

import java.net.URL;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jfx.core.app.ContentBox;

/**
 * Shape是抽象的。
 * @author realpai <paiap@outlook.com>
 */
public class ShapeApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Shape";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Circle c = new Circle(100);
        c.setCenterX(100);
        c.setCenterY(100);

        Rectangle r = new Rectangle(100, 100);
        r.setFill(Color.BEIGE);

        // 合成两个图形的交接部分，返回新的图形；
        Shape intersect = Shape.intersect(c, r);
        Shape subtract = Shape.subtract(c, r);
        Shape union = Shape.union(c, r);

        URL fileURL = this.getClass().getClassLoader().getResource("imgs/1.jpg");
        ImageView iv1 = new ImageView(fileURL.toExternalForm());
        iv1.setFitWidth(200);
        iv1.setPreserveRatio(true);
        iv1.setClip(intersect);

        ImageView iv2 = new ImageView(fileURL.toExternalForm());
        iv2.setFitWidth(200);
        iv2.setPreserveRatio(true);
        iv2.setClip(subtract);

        ImageView iv3 = new ImageView(fileURL.toExternalForm());
        iv3.setFitWidth(200);
        iv3.setPreserveRatio(true);
        iv3.setClip(union);

        hBox.getChildren().addAll(iv1, iv2, iv3);
        getChildren().add(hBox);

    }
}
