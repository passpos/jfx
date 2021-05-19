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

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.common.Data;
import jfx.core.entity.FxPerson;

/**
 * 显示 对象类型 与 StringConverter
 *
 * @author realpai <paiap@outlook.com>
 */
public class TextFieldListCellDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - TextFieldListCell - FxPerson";
    private ListView<FxPerson> lv;

    @Override
    public void index() {
        base();
        editAction();
    }

    public void base() {
        lv = new ListView<>(Data.getFxPersonList());
        lv.setPrefHeight(200.0);
        getChildren().add(lv);
    }

    /**
     * setCellFactory | Callback - StringConverter
     *
     * StringConverter<>()的泛型不一定非要是String，如果需要可以是任意类型；
     *
     * - fromString()，在用户编辑完成后，提交的字符串数据会传递到这里，并生成新
     * 版本的 FxPerson 对象。不过这个对象是不会同步到数据源的，除非特意设置；
     *
     * - toString()，无论是初始过程还是用户修改后的提交过程，所有的 FxPerson 对
     * 象会被转换为字符串，更新到界面中；
     */
    public void editAction() {
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback;
        callback = TextFieldListCell.forListView(getStringConverter());

        lv.setEditable(true);
        lv.setCellFactory(callback);
    }
    
    public static StringConverter<FxPerson> getStringConverter() {
        StringConverter<FxPerson> sc = new StringConverter<>() {

            @Override
            public String toString(FxPerson t) {
                return t.getName() + " - OK!";
            }

            @Override
            public FxPerson fromString(String string) {
                FxPerson pe = new FxPerson("newPerson - " + string, "0");
                return pe;
            }

        };
        return sc;
    }

}
