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

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SimpleListBindingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - SimpleListPreperty";

    @Override
    public void index() {
        bindDemo();
        bindBidirectionalDemo();
        bindContentDemo();
        bindContentBidirectionalDemo();
        valueAtDemo();
    }

    public void bindDemo() {
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ObservableList<String> ol2 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("-----------------------单向绑定（以slp2为准）：");
        slp1.bind(slp2);
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：\n"
                + "列表属性对象被绑定，列表元素却未被绑定，slp1和slp2都可以继续添加元素。");

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);
    }

    public void bindBidirectionalDemo() {
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ObservableList<String> ol2 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("-----------------------双向绑定：");
        slp1.bindBidirectional(slp2);
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：\n"
                + "列表属性对象被绑定，列表元素却未被绑定，slp1和slp2都可以继续添加元素。");

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：\n"
                + "单向绑定与双向绑定结果相同！");
    }

    public void bindContentDemo() {
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ObservableList<String> ol2 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("-----------------------单向“内容”绑定（以slp2为准）：");
        slp1.bindContent(slp2);
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：\n"
                + "列表属性对象被绑定，列表元素也被绑定；\n"
                + "slp1和slp2都可以继续添加元素；\n"
                + "向slp1添加的元素不会添加到slp2中；\n"
                + "向slp2添加的元素则会添加到slp1中；");

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);
    }

    public void bindContentBidirectionalDemo() {
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ObservableList<String> ol2 = FXCollections.observableArrayList();
        SimpleListProperty<String> slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("-----------------------双向“内容”绑定（以slp2为准）：");
        slp1.bindContentBidirectional(slp2);
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：\n"
                + "列表属性对象被绑定，列表元素也被绑定；\n"
                + "slp1和slp2都可以继续添加元素；\n"
                + "向slp1和slp2添加的元素会添加到slp2和slp1中；");

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);
    }

    /**
     * 在列表的键上设置“绑定”
     * 通过这个“绑定”对象，就可以访问键对应的具体的值；
     *
     * 这仅对于list与map类型，由于set没有顺序，即没有key，所以不可以设置；
     *
     * valueAt()方法继承自抽象的ListExpression类和MapExpression类；
     */
    public void valueAtDemo() {
        ObservableList<String> ol = FXCollections.observableArrayList();
        SimpleListProperty<String> slp = new SimpleListProperty<>(ol);
        slp.add("A");
        slp.add("B");
        slp.add("C");
        slp.add("D");
        slp.add("E");

        VBox vb = new VBox();
        for (int i = 0; i < slp.size(); i++) {
            Label label = new Label("A");
            label.setFont(new Font(40));

            ObjectBinding<String> vab = slp.valueAt(i);
            label.textProperty().bind(vab);

            vb.getChildren().add(label);
        }
        this.getChildren().add(vb);

        TextField tf1 = new TextField();
        setLeftAnchor(tf1, 150.0);

        TextField tf2 = new TextField();
        setTopAnchor(tf2, 30.0);
        setLeftAnchor(tf2, 150.0);

        this.getChildren().addAll(tf1, tf2);

        tf2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.equals("")
                        || tf1.getText().equals("")) {
                    ob("请输入内容！");
                } else {
                    int i = -1;
                    try {
                        i = Integer.parseInt(tf1.getText());
                    } catch (NumberFormatException e) {
                        ob("请输入数字！");
                    }
                    if (i > -1 && i < slp.size() - 1) {
                        slp.set(i, t1);
                    } else {
                        ob("超出索引！");
                    }
                }
            }
        }
        );
    }
}
