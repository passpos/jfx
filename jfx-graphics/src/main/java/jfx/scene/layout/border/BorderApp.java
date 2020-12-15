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
package jfx.scene.layout.border;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import jfx.core.app.ContentBox;

/**
 * final Border extends Object
 *
 * 区域/Region的边界。 Border是一个不变的对象，它封装呈现Region的边框所需的整个
 * 数据集。由于此类是不可变的，因此您可以在许多不同的Region上自由地重用相同的
 * Border。
 *
 * 请参阅《JavaFX CSS参考指南》，以获取有关样式区域边框的CSS规则的完整说明。
 *
 * - strokes images
 * 每个边框都由笔触（strokes）和/或图像（images）组成。两个列表都不会为null，但
 * 是一个或两个却可能为empty。渲染时，如果未指定图像或没有成功加载图像，则将按顺
 * 序渲染所有strokes。如果指定了任何图像并成功加载，则将不会绘制任何strokes，尽
 * 管它们仍然会影响Border的 insets 和 outsets。
 *
 * - outsets
 * Border的起点（outsets）定义了Region绘制区域的任何扩展，这是考虑所有Border绘制
 * 和位置所必需的。这些outsets由在此Border上指定的 BorderStrokes 和 BorderImages
 * 定义。outsets是严格非负的。
 *
 * - insets
 * insets用于定义所有边框的最内边缘。 它也始终严格是非负的。 Region使用背景和边框
 * 的插图以及Region的填充来确定Region插图，这些插图定义了Region的任何子级的内容
 * 区域。 Border的起点与Background的起点以及Region的宽度和高度一起定义了Region的
 * 几何边界（而几何边界又对layoutBounds，boundsInLocal和boundsInParent有所贡献）。
 *
 * 如果要使用图像为Region蒙皮，通常将Border与9补丁缩放技术结合使用。 在这种情况
 * 下，您还可以指定一个描边边框，该边框仅在图像由于某种原因无法加载时使用。
 * @author realpai <paiap@outlook.com>
 */
public class BorderApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Border - Border";
    private HBox hBox;

    @Override
    public void index() {
    }

    public void baseDemo() {
        hBox = new HBox();
        hBox.setPrefWidth(200.0);
        hBox.setPrefHeight(250.0);
        hBox.setStyle("-fx-background-color:#556699");

        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#ff0000"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(0),
                new BorderWidths(3.0)
        );

        hBox.setBorder(new Border(bs));
    }
}
