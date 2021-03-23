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

import java.util.Comparator;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ListPropertyApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - ListProperty";
    private ObservableList<String> list;
    private SimpleListProperty<String> slp;

    @Override
    public void index() {
        setConsole();

        list = FXCollections.observableArrayList();
        slp = new SimpleListProperty<>(list);

        baseDemo();
        listenerDemo();
    }

    /**
     * 注意：
     * 新旧列表相同，且打印时，列表的所有属性元素（forEach()）都会被
     * 打印.
     * t1.forEach(ListPropertyApp::ol);
     */
    public void baseDemo() {
        slp.addListener(new ChangeListener<ObservableList<String>>() {
            @Override
            public void changed(
                    ObservableValue<? extends ObservableList<String>> ov,
                    ObservableList<String> t,
                    ObservableList<String> t1
            ) {
//                ob("t：");
//                t.forEach(ListPropertyApp.this::ob);
//                ob("t1：");
//                t1.forEach(ListPropertyApp.this::ob);
            }

        });

        // 添加元素到列表属性的方法
        list.add("A");
        slp.add("B");
    }

    /**
     * 因为list集合有索引，所以需要next()方法
     */
    public void listenerDemo() {
        slp.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                ob(c, 200);

                // 获得更改后的元素列表
                c.getList().forEach((str) -> ob(str));

                c.reset();
                // 获得更改的元素（注意：必须调用 next()，否则出错）
                if (c.next()) {
                    ob("起始位置：" + c.getFrom(), 200);
                    ob("结束位置：" + c.getTo(), 200);
                    ob("wasAdded()：" + c.wasAdded(), 200);
                    // ob("wasAdded()：" + c.getAddedSubList(), 200);
                    // ob("wasAdded()：" + c.getAddedSize(), 200);
                    // ob("wasAdded()：" + c.getRemoved(), 200);
                    ob("wasRemoved()：" + c.wasRemoved(), 200);
                    ob("wasReplaced()：" + c.wasReplaced(), 200);
                    // 排序变化
                    ob("wasPermutated()：" + c.wasPermutated(), 200);
                    ob("wasUpdated()：" + c.wasUpdated(), 200);
                }
            }
        });
        slp.add("C");
        // slp.addAll("C","D");
        slp.remove("C");
        slp.set(0, "D");
        slp.get(0);
        // 使用 list 而不是 slp（排序过程被拆分），才能激活 wasPermutated()
        slp.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    }
}
