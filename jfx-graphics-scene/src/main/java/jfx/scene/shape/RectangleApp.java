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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class RectangleApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Rectangle 矩形";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 20.0);

        // x, y, width, height（要让x、y生效，还需要依据父组件的类型）
        Rectangle r1 = new Rectangle(0, 0, 50, 50);

        // width, height
        Rectangle r2 = new Rectangle(100, 100);
        r2.setFill(Color.AQUA);
        r2.setArcWidth(30);
        r2.setArcHeight(10);

        Rectangle r3 = new Rectangle(50, 50);
        r3.setFill(Color.ANTIQUEWHITE);
        // 边框宽度
        r3.setStrokeWidth(5);
        r3.setStroke(Color.BLUE);
        // 边框画在外部
        r3.setStrokeType(StrokeType.OUTSIDE);

        hBox.getChildren().addAll(r1, r2, r3);
        getChildren().add(hBox);
    }

}
