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
package jfx.scene.control.menubox;

import jfx.utils.app.ContentBox;
import java.util.Comparator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChoiceBoxDemoApp extends ContentBox {

    public static final boolean SHOWING = false;

    public static final String TITLE = "MenuBox - Demo ChoiceBox数据转换";
    public ChoiceBox<Student> cb;
    private Student modifiedStudent;

    @Override
    public void index() {
        baseDemo();
        this.getChildren().add(cb);

        // 实时修改数据并展示
        modifyDemo();

        // 数据关联
        mapDemo();

        // 数据排序
        sortDemo();
    }

    public void baseDemo() {
        Student s1 = new Student("小A", 18, 93);
        Student s2 = new Student("小B", 11, 45);
        Student s3 = new Student("小C", 15, 97);
        Student s4 = new Student("小D", 18, 56);
        Student s5 = new Student("小E", 16, 79);

        cb = new ChoiceBox<>();
        cb.getItems().addAll(s1, s2, s3, s4, s5);
        cb.setPrefWidth(150.0);

        // 设置属性值转换（将对象的属性转换为可供选项菜单显示的字符串）
        cb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                String value = t.getName()
                        + " - 年龄：" + t.getAge()
                        + " - 分数：" + t.getScore();
                return value;
            }

            @Override
            public Student fromString(String string) {
                // 这里执行不到（不执行）；
                ol("jdsbhb");
                return null;
            }
        });
    }

    /**
     * 实时修改数据并展示
     */
    private void modifyDemo() {
        TextField tf = new TextField();
        Button btn = new Button("修改名称");

        // 获取选项数据
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> ov, Student t, Student t1) {
                modifiedStudent = t1;
            }
        });

        // 设置提交后的修改动作
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (modifiedStudent != null) {
                    int index = cb.getItems().indexOf(modifiedStudent);
                    cb.getItems().remove(modifiedStudent);

                    modifiedStudent.setName(tf.getText());
                    cb.getItems().add(index, modifiedStudent);
                } else {
                    ol("Student数据为空！");
                }
            }
        });
        tf.setPrefWidth(100);
        AnchorPane.setLeftAnchor(tf, 200.0);
        AnchorPane.setLeftAnchor(btn, 300.0);
        this.getChildren().addAll(tf, btn);
    }

    /**
     * 数据关联
     */
    private void mapDemo() {
//        SimpleListProperty<String> slp = new SimpleListProperty<>();
//        slp.add("");
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll("数字", "字母");

        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("1", "2", "3", "4", "5", "6");

        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3.addAll("A", "B", "C", "D");

        ChoiceBox<String> cb1 = new ChoiceBox<>();
        cb1.setItems(list1);
        cb1.setPrefWidth(100);

        ChoiceBox<String> cb2 = new ChoiceBox<>();
        cb2.setPrefWidth(100);

        AnchorPane.setTopAnchor(cb1, 200.0);
        AnchorPane.setTopAnchor(cb2, 200.0);
        AnchorPane.setLeftAnchor(cb2, 200.0);

        cb1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.equals("数字")) {
                    cb2.setItems(list2);
                } else if (t1.equals("字母")) {
                    cb2.setItems(list3);
                }
            }
        });

        this.getChildren().addAll(cb1, cb2);
    }

    /**
     * 数据排序
     */
    public void sortDemo() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("1", "2", "3", "4", "5", "6");

        ChoiceBox<String> lcb = new ChoiceBox<>();
        lcb.setPrefWidth(100.0);
        lcb.setItems(list);
        AnchorPane.setTopAnchor(lcb, 400.0);

        Button btn = new Button("排序");
        AnchorPane.setTopAnchor(btn, 400.0);
        AnchorPane.setLeftAnchor(btn, 200.0);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                list.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        // compare()是对数据源中的所有数据进行处理（排序），比较两个参数的顺序；
                        // 返回负整数、零或正整数，因为第一个参数小于、等于或大于第二个参数。
                        // 下面我们得到了两个数据的差；
                        int i1 = Integer.valueOf(o1);
                        int i2 = Integer.valueOf(o2);
                        return i2 - i1;
                    }
                });
            }
        });
        this.getChildren().addAll(lcb, btn);
    }

}
