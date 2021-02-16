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
package jfx.scene.control.menubox;

import jfx.core.app.ContentBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChoiceBoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ChoiceBox 下拉菜单";
    private ChoiceBox<String> cb;

    @Override
    public String toString() {
        return "ChoiceBoxApp{" + "cb=" + cb + '}';
    }

    @Override
    public void index() {
        baseDemo();
        this.getChildren().add(cb);

        // 监听按钮切换
        listenerDemo();
    }

    public void baseDemo() {
        // 下拉菜单；
        cb = new ChoiceBox<>();

        // 可以放字符串、分隔符new Sperate()，不能放Node类型的对象；
        cb.getItems().addAll("itm1", "itm2", "itm3");

        // 设置默认选中
        cb.setValue("itm2");
        cb.getSelectionModel().select("itm3");
        cb.getSelectionModel().selectPrevious();
        cb.getSelectionModel().selectFirst();
    }

    /**
     * 监听按钮切换（其中，t、t1为按钮的标题文本）
     */
    public void listenerDemo() {
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ol(t + t1);
            }
        });
    }

}
