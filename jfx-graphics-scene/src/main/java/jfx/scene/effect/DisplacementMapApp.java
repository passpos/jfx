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
package jfx.scene.effect;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DisplacementMapApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - DisplacementMap";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        DisplacementMap dm1 = new DisplacementMap();
        DisplacementMap dm2 = new DisplacementMap();
        DisplacementMap dm3 = new DisplacementMap();
        DisplacementMap dm4 = new DisplacementMap();

        Button btn = new Button("按钮");
        btn.setEffect(dm1);

        Text text = new Text("这是一行文本");
        // 遮罩组件的20%部分
        dm2.setOffsetX(0.2);
        dm2.setOffsetY(0.2);
        text.setFont(new Font(20));
        text.setEffect(dm2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        dm3.setMapData(setFloatMap(0.5f));
        r.setEffect(dm3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        dm4.setOffsetX(0.5);
        dm4.setOffsetY(0.5);
        // 定义是否从地图边缘外部获取值“环绕”
        dm4.setWrap(true);
        circle.setEffect(dm4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }

    /**
     * FloatMap 一种包含浮点数据的缓冲区，用作诸如DisplacementMap等效果的参数。
     * @param v
     * @return
     */
    public FloatMap setFloatMap(float v) {
        int w = 100;
        int h = 100;
        FloatMap fm = new FloatMap(w, h);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                float tmp;
                if (j < h / 2) {
                    tmp = v;
                } else {
                    tmp = -1 * v;
                }
                fm.setSamples(i, j, tmp, 0);
            }
        }
        return fm;
    }
}
