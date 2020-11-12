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

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PolylineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - Polyline";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 20.0);

        Polyline pl1 = new Polyline(0, 0, 50, 100, 100, 0);
        pl1.setStrokeWidth(5);
        pl1.setStroke(Paint.valueOf("#CD0055"));
        pl1.setFill(Color.BEIGE);

        Polyline pl2 = new Polyline(0, 0, 50, 100, 50, 50, 0, 0);
        pl2.setFill(Color.BURLYWOOD);

        Polyline pl3 = new Polyline();
        pl3.getPoints().addAll(0.0, 100.0, 50.0, 0.0, 100.0, 100.0);

        Polyline pl4 = new Polyline(100, 0, 150, 100);
        pl4.setStrokeWidth(10);
        Double[] p1 = new Double[]{0.0, 0.0, 50.0, 100.0, 70.0, 30.0, 0.0, 0.0};
        pl4.getPoints().addAll(p1);
        pl4.setFill(Color.CHOCOLATE);
        // 相接过渡方式
        pl4.setStrokeLineCap(StrokeLineCap.ROUND);
        // 转角过渡方式
        pl4.setStrokeLineJoin(StrokeLineJoin.MITER);
        // 转角衔接限制
        pl4.setStrokeMiterLimit(5);

        Polyline pl5 = new Polyline(0.0, 0.0, 50.0, 100.0, 70.0, 30.0, 0.0, 0.0);
        pl5.setStrokeWidth(10);
        pl5.setStrokeLineJoin(StrokeLineJoin.ROUND);

        Polyline pl6 = new Polyline(0, 0, 20, 100, 40, 20, 0, 0);
        pl6.setStrokeWidth(10);
        pl6.setStrokeType(StrokeType.OUTSIDE);
        pl6.setFill(Color.BEIGE);
        // 未设置填充时（setFill()），需要在线段上点击，而非闭合形状的内部；
        // 一旦设置了填充，点击形状内壁也可以触发事件；
        pl6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                pl6.setFill(Color.BROWN);
            }
        });

        hBox.getChildren().addAll(pl1, pl2, pl3, pl4, pl5, pl6);
        getChildren().add(hBox);
    }

}
