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
package jfx.collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FXCollectionsApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Collections - FXCollections";
    private ObservableList<String> oal;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        // FXCollections.observableSet();
        // FXCollections.observableMap(map);
        oal = FXCollections.observableArrayList();
        oal.add("a");
        oal.add("b");
        oal.add("c");
        oal.add("d");
        oal.add("e");

        FXCollections.rotate(oal, 1);   // 把最后1个放到前面；
        oal.forEach((s) -> ol(s));

        FXCollections.rotate(oal, 2);   // 把最后两个放到前面；
        oal.forEach((s) -> ol(s));

    }

    public void commonMethod() {
        // 打乱顺序；
        FXCollections.shuffle(oal);

        // 返回单个元素不可修改的列表；
        FXCollections.singletonObservableList(oal);

        // 返回不可修改的列表；
        FXCollections.unmodifiableObservableList(oal);
//        FXCollections.obser


    }
}
