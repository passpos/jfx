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

import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComboBoxDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ComboBox 动态添加数据";
    private ComboBox<Student> cb;

    @Override
    public void index() {
        base();
    }

    public void base() {
        cb = new ComboBox<>();
        cb.setPrefWidth(200);
        cb.setEditable(true);
        cb.setPromptText("动态添加数据");
        getChildren().add(cb);

        Student stu1 = new Student("小A", 10, 90);
        Student stu2 = new Student("小B", 16, 92);
        cb.getItems().addAll(stu1, stu2);

        // 设置属性值转换
        cb.setConverter(getConverter());
    }

    /**
     * 转换数据对象的属性值为字符串
     *
     * 将对象数据转换为字符串，并展示到下拉菜单的选项中；
     * 如果没有设置setConverter()方法，只要用于展示的对象重写了toString()方法，也可
     * 以将toString()的结果展示出来；
     */
    private StringConverter<Student> getConverter() {
        StringConverter<Student> sc = new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                if (t == null) {
                    return "t = null";
                }

                /*
                 * 动态添加数据
                 *
                 * 如果cb下的选项中包含该元素，则将其转换为字符串并展示出来；
                 * 否则添加到选项中；
                 */
                if (cb.getItems().contains(t)) {
                    return t.toString();
                } else {
                    cb.getItems().add(t);
                    return t.toString();
                }
            }

            /*
             * 此方法接收从ComboBox输入的数据
             * 如果ComboBox不可编辑，则此方法不运行；
             *
             * 转换为Student对象，并传递给toString()；
             */
            @Override
            public Student fromString(String input) {
                // 输入的文本
                if (input == null) {
                    ol("input是null");
                    return null;
                }
                if ("".equals(input)) {
                    ol("input是空");
                    return null;
                }
                Student stu = new Student(input, 15, 100.0);
                return stu;
            }
        };

        return sc;
    }

}
