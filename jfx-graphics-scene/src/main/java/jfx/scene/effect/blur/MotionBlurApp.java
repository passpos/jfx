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

package jfx.scene.effect.blur;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 * 动态模糊
 * @author realpai <paiap@outlook.com>
 */
public class MotionBlurApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - MotionBlur";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        MotionBlur mb1 = new MotionBlur();
        MotionBlur mb2 = new MotionBlur();
        MotionBlur mb3 = new MotionBlur();
        MotionBlur mb4 = new MotionBlur();

        Button btn = new Button("按钮");
        btn.setEffect(mb1);

        Text text = new Text("这是一行文本");
        mb2.setRadius(40);
        text.setFont(new Font(20));
        text.setEffect(mb2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        /* 运动效果的角度，以度为单位；
         * 默认为0，水平方向模糊，模糊中心轴则为竖直；
         */
        mb3.setAngle(45);
        r.setEffect(mb3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        mb4.setAngle(90);
        circle.setEffect(mb4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

}
