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

import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 * Lesson64
 * 常用子类：
 * SimpleListProperty
 *
 * @author realpai <paiap@outlook.com>
 */
public class ObservableListApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Collections - ObservableList";

    @Override
    public void index() {
        baseDemo();
        listenerDemo();
        wrapperDemo();
    }

    public void baseDemo() {
        SimpleStringProperty s = new SimpleStringProperty("a");

        ObservableList<SimpleStringProperty> list = FXCollections.observableArrayList(new Callback<SimpleStringProperty, Observable[]>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                ob("call = " + param, 500);
                Observable[] o = new Observable[]{param};
                return o;
            }
        });

        list.addListener(new ListChangeListener<SimpleStringProperty>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SimpleStringProperty> c) {
                while (c.next()) {
                    ob("onChanged = " + c + " wasUpdated = " + c.wasUpdated(), 500);
                }
            }
        });
        list.add(s);
        list.add(s);
        ob(list.get(0), 500);

        s.set("c");

    }

    public void listenerDemo() {
        SimpleStringProperty s = new SimpleStringProperty("a");

        ObservableList<SimpleStringProperty> list1 = FXCollections.observableArrayList();
        ObservableList<SimpleStringProperty> list2 = FXCollections.observableArrayList(new Callback<SimpleStringProperty, Observable[]>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                ob("call = " + param, 500);
                Observable[] o = new Observable[]{param};
                return o;
            }
        });

        SimpleListProperty<SimpleStringProperty> slp = new SimpleListProperty<>(list2);
        slp.addListener(new ListChangeListener<SimpleStringProperty>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SimpleStringProperty> c) {
                while (c.next()) {
                    ob("onChanged = " + c + " wasUpdated = " + c.wasUpdated(), 500);
                }
            }
        });
        slp.add(s);
        s.set("b");
    }

    public void wrapperDemo() {
        SimpleStringProperty s1 = new SimpleStringProperty("a");
        SimpleStringProperty s2 = new SimpleStringProperty("a");
        ObservableList<SimpleStringProperty> list1 = FXCollections.observableArrayList(new Callback<SimpleStringProperty, Observable[]>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                ob("call1 = " + param, 500);
                Observable[] o = new Observable[]{param};
                return o;
            }
        });
        list1.addListener(new ListChangeListener<SimpleStringProperty>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SimpleStringProperty> c) {
                while (c.next()) {
                    ob("onChanged = " + c + " wasUpdated = " + c.wasUpdated(), 500);
                }
            }
        });
        ObservableList<SimpleStringProperty> list2 = FXCollections.observableList(list1, new Callback<SimpleStringProperty, Observable[]>() {
            @Override
            public Observable[] call(SimpleStringProperty param) {
                ob("call2 = " + param, 500);
                Observable[] o = new Observable[]{param};
                return o;
            }
        });
        list2.addListener(new ListChangeListener<SimpleStringProperty>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SimpleStringProperty> c) {
                while (c.next()) {
                    ob("onChanged = " + c + " wasUpdated = " + c.wasUpdated(), 500);
                }
            }
        });

        list1.add(s1);
        list2.add(s2);
        list1.forEach(i -> ob("list2 = " + i.get()));
        list1.forEach(i -> ob("list1 = " + i.get()));

//        s1.set("b");
//        list1.forEach(i -> ob("list2 = " + i.get()));
//        list1.forEach(i -> ob("list1 = " + i.get()));
    }
}
