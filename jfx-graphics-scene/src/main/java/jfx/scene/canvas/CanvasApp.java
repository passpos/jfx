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

package jfx.scene.canvas;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CanvasApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Canvas - Canvas 画布";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Canvas c = new Canvas(500, 500);
        GraphicsContext gc = c.getGraphicsContext2D();

        // 先设置画笔/油漆桶
        gc.setFill(Color.ANTIQUEWHITE);
        gc.setLineWidth(5);
        gc.setStroke(Color.BLUE);

        // 填充一个矩形
        gc.fillRect(100, 100, 100, 100);
        // 画一个矩形
        gc.strokeRect(200, 200, 100, 100);
        // 画线
        gc.strokeLine(300, 100, 300, 200);

        vBox.getChildren().addAll(c);
        getChildren().addAll(vBox);

    }

}
