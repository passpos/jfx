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
package jfx.scene.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PaginationApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - Pagination 分页";
    public Pagination pg;

    @Override
    public void index() {
        baseDemo();

        listenerDemo();

        addDataDemo();
    }

    public void baseDemo() {
        pg = new Pagination(10, 0);
        pg.setPrefWidth(400.0);
        pg.setPrefHeight(20);
        pg.setStyle("-fx-background-color:#55cc66");

        // 设置分页的页数，同上面的 10 一样；
        pg.setPageCount(15);

        // 设置分页的最大（如果不足最大值，则全部展示）可见页数，默认为10；
        pg.setMaxPageIndicatorCount(11);

        // 不确定的总页数；
        pg.setPageCount(Pagination.INDETERMINATE);

        // 设置当前页；
        pg.setCurrentPageIndex(2);

        pg.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        Group gp = new Group();
        gp.getChildren().add(pg);
        setTopAnchor(gp, 100.0);
        this.getChildren().add(gp);
    }

    public void listenerDemo() {
        pg.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol(t1);
            }
        });
    }

    public void addDataDemo() {
        pg.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                return new Button(String.valueOf(param + 1));
            }
        });
    }
}
