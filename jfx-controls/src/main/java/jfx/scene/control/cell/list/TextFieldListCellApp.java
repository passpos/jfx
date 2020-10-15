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
import jfx.utils.app.ContentBox;
import jfx.utils.entity.FxPerson;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TextFieldListCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListCell - TextFieldListCell";
    private ListView<String> lv1;
    private ObservableList<String> oal1;
    private ListView<FxPerson> lv2;
    private ObservableList<FxPerson> oal2;

    @Override
    public void index() {
        base1();
        textFieldListCellDemo();

        base2();
        stringConverterDemo();
    }

    public void base1() {
        lv1 = new ListView<>();
        lv1.setPrefHeight(200.0);

        // 用于list选项
        oal1 = lv1.getItems();
        oal1.add("A");
        oal1.add("B");
        oal1.add("C");

        getChildren().add(lv1);
    }

    /**
     * setCellFactory | Callback - TextFieldListCell
     * 修改会对数据源生效，但要持久化到数据库，需要另外设置；
     */
    public void textFieldListCellDemo() {
        lv1.setEditable(true);
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

        lv1.setCellFactory(callback);
    }

    public void base2() {
        lv2 = new ListView<>();
        lv2.setPrefHeight(200.0);

        // 用于list选项
        oal2 = lv2.getItems();
        FxPerson pe1 = new FxPerson("教化", "15");
        FxPerson pe2 = new FxPerson("北京饭店", "56");
        FxPerson pe3 = new FxPerson("ggu", "69");
        oal2.add(pe1);
        oal2.add(pe2);
        oal2.add(pe3);

        getChildren().add(lv2);
        setLeftAnchor(lv2, 300.0);
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
    public void stringConverterDemo() {
        lv2.setEditable(true);
        Callback<ListView<FxPerson>, ListCell<FxPerson>> callback = TextFieldListCell.forListView(
                new StringConverter<FxPerson>() {

            @Override
            public String toString(FxPerson t) {
                return t.getName() + " - OK!";
            }

            @Override
            public FxPerson fromString(String string) {
                FxPerson pe = new FxPerson("newPe - " + string, "0");
                return pe;
            }

        });
        lv2.setCellFactory(callback);
    }

}
