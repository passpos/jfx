/*
 * Copyright (C) 2019 realpai <paiap@outlook.com>
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TitledPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Pane - TitledPane 下拉菜单";

    @Override
    public void index() {
        baseDemo();

        expandDemo();

        accDemo();
    }

    public void baseDemo() {
        TitledPane tp = new TitledPane("TitledPane", new Button("btn"));
        // 设置为false后，不可折叠；
        tp.setCollapsible(false);
        this.getChildren().addAll(tp);
    }

    public void expandDemo() {
        TitledPane tp2 = new TitledPane();
        TitledPane tp3 = new TitledPane();
        tp3.setText("TitledPane");
        tp3.setContent(new Button("btn"));
        tp3.setExpanded(false);             // 设置默认状态为折叠；
        tp3.setAnimated(true);              // 设置是否展示动画效果；
        tp3.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                ol(t1);
            }
        });

        tp2.setText("TitledPane");
        tp2.setContent(tp3);

        AnchorPane.setTopAnchor(tp2, 100.0);
        this.getChildren().add(tp2);
    }

    /**
     * 设置其下的子项只有一个展开；
     */
    public void accDemo() {
        Accordion acc = new Accordion();
        TitledPane tp4 = new TitledPane("TitledPane", new Button("btn"));
        TitledPane tp5 = new TitledPane("TitledPane", new Button("btn"));
        TitledPane tp6 = new TitledPane("TitledPane", new Button("btn"));
        acc.getPanes().addAll(tp4, tp5, tp6);
        acc.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
            @Override
            public void changed(ObservableValue<? extends TitledPane> ov, TitledPane t, TitledPane t1) {
                ol(t1);
                ol("\n");
            }
        });
        AnchorPane.setTopAnchor(acc, 200.0);
        this.getChildren().add(acc);
    }

}
