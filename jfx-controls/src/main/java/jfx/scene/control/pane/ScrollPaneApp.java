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
package jfx.scene.control.pane;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ScrollPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Pane - ScrollPane 滚动面板";

    @Override
    public void index() {
        baseDemo();
        fitSizeDemo1();
        fitSizeDemo2();
        fitSizeDemo3();
        fitSizeDemo4();
        defaultSizeDemo();
    }

    public void baseDemo() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 10; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);

        // ScrollPane的尺寸即为用户的所有可见尺寸，可滚动的内容收藏于其中；
        sp.setPrefWidth(300);

        sp.setFitToWidth(false);
        sp.setFitToHeight(true);

        getChildren().add(sp);
    }

    /**
     * FitSize 设置为true后，内容的总尺寸将跟随ScrollPane的尺寸。
     * 例如：
     * 可以被压缩尺寸的Button等内容，其尺寸就会被压缩为ScrollPane的尺寸。此
     * 时，ScrollPane的滑块就会消失，仅剩滑轨；
     *
     * 所以，通常不设置 FitSize 为 true ；
     *
     * 若不设置FitSize，或者设置为false，则内容的尺寸不会跟随ScrollPane；
     * 此时，若：
     * - 内容尺寸小于ScrollPane尺寸，则不显示ScrollBar；
     * - 内容尺寸大于ScrollPane尺寸，则显示ScrollBar，不压缩内容尺寸；
     */
    public void fitSizeDemo1() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 4; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);
        sp.setPrefWidth(400);

        getChildren().add(sp);
        setTopAnchor(sp, 50.0);
    }

    /**
     * 设置为true后，ScrollPane内的HBox的尺寸跟随ScrollPane；
     * 此时，若：
     * - 内容尺寸小于ScrollPane尺寸，则不显示ScrollBar；
     * - 内容尺寸大于ScrollPane尺寸，则不显示ScrollBar，且压缩内容尺寸；
     */
    public void fitSizeDemo2() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 4; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);
        sp.setPrefWidth(400);

        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        getChildren().add(sp);
        setTopAnchor(sp, 100.0);
    }

    /**
     * 设置为true后，ScrollPane内的HBox的尺寸跟随ScrollPane；
     */
    public void fitSizeDemo3() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 7; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);
        sp.setPrefWidth(300);

        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        getChildren().add(sp);
        setTopAnchor(sp, 150.0);
    }

    /**
     * 设置为false，ScrollPane内的HBox的尺寸跟随ScrollPane；
     */
    public void fitSizeDemo4() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 10; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);
        sp.setPrefWidth(300);

        getChildren().add(sp);
        setTopAnchor(sp, 200.0);
    }

    /**
     * 若未设置首选尺寸，则ScrollPane的尺寸跟随内容尺寸。此时：
     * - ScrollPane虽然处于显示状态，但不显示滚动条；
     * - 即使内容尺寸大于ScrollPane的父容器的尺寸，滚动条也不显示；
     */
    public void defaultSizeDemo() {
        HBox hb = new HBox();
        hb.setSpacing(3);
        hb.setStyle("-fx-background-color: #956");
        for (int i = 1; i < 20; i++) {
            hb.getChildren().add(new Button("按钮" + i));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(hb);
        // sp.setPrefWidth(300);

        getChildren().add(sp);
        setTopAnchor(sp, 250.0);
    }
}
