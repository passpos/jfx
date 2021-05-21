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
package jfx.beans.binding;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.core.app.ContentBox;

/**
 * B-60
 *
 * @author realpai <paiap@outlook.com>
 */
public class CollectionBindingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Collection 单向绑定";
    private SimpleListProperty<String> slp1;
    private SimpleListProperty<String> slp2;
    private ObservableList<String> ol1;
    private ObservableList<String> ol2;

    @Override
    public void index() {
        base();
        bind();
    }

    public void base() {
        ol1 = FXCollections.observableArrayList();
        slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ol2 = FXCollections.observableArrayList();
        slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
    }

    public void bind() {
        slp1.bind(slp2);

        ol("-----------------------单向绑定（以slp2为准）：");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：");
        ol("列表属性对象被绑定，但各自维护的列表却未被绑定；");
        ol("slp1和slp2都可以继续添加元素；");
        ol("slp1和slp2访问的列表，都是slp2维护的列表；");
        ol("slp1的列表被搁置");
    }

}
