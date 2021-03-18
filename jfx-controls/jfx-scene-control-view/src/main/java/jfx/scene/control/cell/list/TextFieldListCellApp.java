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
package jfx.scene.control.cell.list;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TextFieldListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "List - TextFieldListCell - String";
    private ListView<String> lv;
    private ObservableList<String> oal;

    @Override
    public void index() {
        base();
        editAction();
    }

    public void base() {
        lv = new ListView<>();
        lv.setPrefHeight(200.0);

        // 用于list选项
        oal = lv.getItems();
        oal.add("A");
        oal.add("B");
        oal.add("C");

        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - TextFieldListCell
     * 修改会对数据源生效，但要持久化到数据库，需要另外设置；
     */
    public void editAction() {
        lv.setEditable(true);
        // Callback<ListView<String>, ListCell<String>> callback = TextFieldListCell.forListView();

        Callback<ListView<String>, ListCell<String>> callback = TextFieldListCell.forListView(
                new StringConverter<String>() {

            /**
             * toString(String t)方法会在程序初始化时就进行调用；
             * @param t
             * @return
             */
            @Override
            public String toString(String t) {
                return t + " - world";
            }

            /**
             * fromString(String string)方法仅在发生数据修改（提交数据）时调用；
             * 而提交数据是TextField特有的行为；
             */
            @Override
            public String fromString(String string) {
                return string + " - hello";
            }
        });

        lv.setCellFactory(callback);
    }

}
