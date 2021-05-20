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
package jfx.beans.property;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.core.app.ContentBox;

/**
 * SimpleListProperty<E>
 * abstract class ListProperty<E>
 *
 * java.lang.Object
 * |—javafx.beans.binding.ListExpression<E>
 * |——javafx.beans.property.ReadOnlyListProperty<E>
 * |———javafx.beans.property.ListProperty<E>
 * |————javafx.beans.property.ListPropertyBase<E>
 * |—————javafx.beans.property.SimpleListProperty<E>
 *
 * SimpleListProperty<String> 与 ObservableList<String>的关系是：
 * SimpleListProperty<String>仅仅只是一个属性实例，本身并不存储任何List集合数据，
 * 它是通过 ObservableList<String> 来管理集合数据的。
 *
 * 新创建的SimpleListProperty<String>实例本身不会包含 ObservableList<String> 对
 * 象，它由用户创建并设定，如果不设定，会报告 UnsupportedOperationException ；
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListPropertyApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - ListProperty ChangeListener";
    private ObservableList<String> list;
    private SimpleListProperty<String> slp;

    @Override
    public void index() {
        base();
        changeListenerDemo();
        modifyList();
    }

    public void base() {
        setConsole();

        // 必须将 list 传入ListProperty，否则报错；
        list = FXCollections.observableArrayList();
        slp = new SimpleListProperty<>(list);
    }

    /**
     * 注意：
     * 新旧列表相同，且打印时，列表的所有属性元素（forEach()）都会被打印.
     * t1.forEach(ListPropertyApp::ol);
     */
    public void changeListenerDemo() {
        slp.addListener(new ChangeListener<ObservableList<String>>() {
            @Override
            public void changed(
                    ObservableValue<? extends ObservableList<String>> ov,
                    ObservableList<String> t,
                    ObservableList<String> t1
            ) {
                ob("t：");
                t.forEach(ListPropertyApp.this::ob);

                ob("-----------------------------------------------------");

                ob("t1：");
                t1.forEach(ListPropertyApp.this::ob);
            }

        });
    }

    public void modifyList() {
        // 添加元素到列表属性的方法
        list.add("A");
        slp.add("B");
    }
}
