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
package jfx.scene.layout.box;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 * 水平排列子组件的布局方式
 * 1. 当未设置子组件时，HBox的尺寸为0，而不是填充父组件；
 * 2. 若设置了子组件，HBox的尺寸就会被撑开；
 * 3. 当HBox的水平尺寸较小且不可调整（设置了maxSize），而子组件的总尺寸较大时 ：
 * - 若每个子组件的尺寸可调整（只设置了prefSize），垂直方向会压缩子组件，水平方
 * 向也被压缩；
 * - 若每个子组件的尺寸不可调整（设置了minSize），则子组件不会被挤压，HBox的水
 * 平尺寸会被强制撑开，但不会溢出窗体；
 * 4. 当HBox的父组件尺寸固定（如设置了maxSize），而HBox的尺寸开放时：
 * @author realpai <paiap@outlook.com>
 */
public class HBoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Layout - HBox";

    @Override
    public void index() {
        baseDemo();
        test();
    }

    public void baseDemo() {
        HBox hBox = new HBox();
        Button b1 = new Button("blabla");
        Button b2 = new Button("blabla");
        Button b3 = new Button("blabla");
        hBox.getChildren().addAll(b1, b2, b3);

        // 设置HBox的内边距，不影响子组件
        hBox.setPadding(new Insets(3, 55, 4, 70));

        // 设置子组件间的空隙，不影响HBox
        hBox.setSpacing(20);
        
        hBox.setStyle("-fx-background-color: #793");
        this.getChildren().add(hBox);
    }

    public void test() {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #829");

        // 限定最大尺寸
        hBox.setMaxSize(100, 20);

        Button b1 = new Button("blabla");
        Button b2 = new Button("blabla");
        Button b3 = new Button("blabla");
        // 限定子组件的最小尺寸
        b1.setMinSize(100, 20);
        b2.setMinSize(100, 20);
        b3.setMinSize(100, 20);

        hBox.getChildren().addAll(b1, b2, b3);
        System.out.println(hBox.widthProperty().getValue());
        System.out.println(hBox.prefWidthProperty().getValue());
        System.out.println(hBox.maxWidthProperty().getValue());
        this.getChildren().add(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
    }

}
