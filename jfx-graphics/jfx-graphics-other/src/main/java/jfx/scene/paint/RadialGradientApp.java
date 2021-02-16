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
package jfx.scene.paint;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 * 圆形径向渐变
 * @author realpai <paiap@outlook.com>
 */
public class RadialGradientApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Paint - RadialGradient";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle r = new Rectangle(150, 150);
                r.setFill(Paint.valueOf("#EDEDED"));
                gp.add(r, j, i);
            }
        }

        ArrayList<Paint> list = new ArrayList<>();

        Stop[] stops = new Stop[]{
            new Stop(0, Color.RED),
            new Stop(0.5, Color.AQUA),
            new Stop(1, Color.valueOf("#aa66cc"))
        };

        // 渐变中心在图形右下角；
        RadialGradient rg1 = getRadialGradientByArray(0, 0, 150, 150, 75, false, CycleMethod.NO_CYCLE, stops);
        list.add(rg1);

        // 渐变中心在图形中心；
        RadialGradient rg2 = getRadialGradientByArray(0, 0, 75, 75, 75, false, CycleMethod.NO_CYCLE, stops);
        list.add(rg2);

        // 渐变中心右移一个径向半径；
        RadialGradient rg3 = getRadialGradientByArray(0, 1, 75, 75, 75, false, CycleMethod.NO_CYCLE, stops);
        list.add(rg3);

        // 渐变中心左移一个径向半径；
        RadialGradient rg4 = getRadialGradientByArray(0, -1, 75, 75, 75, false, CycleMethod.NO_CYCLE, stops);
        list.add(rg4);

        // 顺时针为正，一般为0-360度，支持负数；
        RadialGradient rg5 = getRadialGradientByArray(45, 1, 75, 75, 75, false, CycleMethod.NO_CYCLE, stops);
        list.add(rg5);

        RadialGradient rg6 = getRadialGradientByArray(45, 1, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, stops);
        list.add(rg6);

        RadialGradient rg7 = getRadialGradientByArray(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.REPEAT, stops);
        list.add(rg7);

        RadialGradient rg8 = getRadialGradientByArray(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.REFLECT, stops);
        list.add(rg8);

        RadialGradient rg9 = getRadialGradientByArray(0, 0, 0.5, 0.5, 0.4, true, CycleMethod.REPEAT, stops);
        list.add(rg9);

        for (int i = 0; i < list.size(); i++) {
            Rectangle r = (Rectangle) gp.getChildren().get(i);
            r.setFill(list.get(i));
        }

        // 圆形的坐标计算方式十分独特（与其他多边形有很大区别）；
        RadialGradient rgc1 = getRadialGradientByArray(0, 0, 0, 0, 0.5, true, CycleMethod.REPEAT, stops);
        Circle circle1 = new Circle(50);
        circle1.setFill(rgc1);
        setTopAnchor(circle1, 500.0);

        RadialGradient rgc2 = getRadialGradientByArray(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.REPEAT, stops);
        Circle circle2 = new Circle(50);
        circle2.setFill(rgc2);
        setTopAnchor(circle2, 500.0);
        setLeftAnchor(circle2, 100.0);

        getChildren().addAll(gp, circle1, circle2);
    }

    /**
     * @param focusAngle    - 从渐变中心到第一种颜色映射到的焦点的角度（度）；
     * @param focusDistance - 从渐变中心到第一种颜色映射到的焦点的距离；
     *
     * @param centerX       - 梯度圆中心点的X坐标；
     * @param centerY       - 梯度圆中心点的Y坐标；
     *
     * @param radius        - 定义颜色渐变范围的圆的半径；
     * @param proportional  - 坐标和大小是否与此渐变填充的形状成比例；
     * @param cycleMethod   - 应用于梯度的循环法；
     * @param stops         - 渐变的颜色规范（允许向可变参数传入数组类型）；
     *
     * @return
     */
    private RadialGradient getRadialGradientByArray(double focusAngle, double focusDistance, double centerX, double centerY, double radius, boolean proportional, CycleMethod cycleMethod, Stop... stops) {
        RadialGradient rg = new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional, cycleMethod, stops);
        return rg;
    }

    private RadialGradient getRadialGradientByList(double focusAngle, double focusDistance, double centerX, double centerY, double radius, boolean proportional, CycleMethod cycleMethod, List<Stop> stops) {
        RadialGradient rg = new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional, cycleMethod, stops);
        return rg;
    }

}
