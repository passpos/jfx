/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.menubox;

import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Student;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class ComboBoxDemo3 extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "MenuBox - ComboBox 关键字搜索";
    private ComboBox<Student> cb;
    private ObservableList<Student> items;

    @Override
    public void index() {
        base();
    }

    public void base() {
        cb = new ComboBox<>();
        cb.setPrefWidth(200);
        getChildren().add(cb);

        cb.setEditable(true);
        cb.setPromptText("关键字搜索");

        Student stu1 = new Student("小A", 10, 90);
        Student stu2 = new Student("小B", 16, 92);
        Student stu3 = new Student("小C", 10, 97);
        Student stu4 = new Student("小D", 19, 94);
        Student stu5 = new Student("小E", 12, 99);
        cb.getItems().addAll(stu1, stu2, stu3, stu4, stu5);
        items = cb.getItems();

        // 设置属性值转换
        cb.setConverter(getConverter());

        // ComboBox自带文本输入框
        TextField tf = cb.editorProperty().get();

        // 对输入文本进行监听
        tf.textProperty().addListener(getListener());
    }

    private StringConverter<Student> getConverter() {
        StringConverter<Student> sc = new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                if (t == null) {
                    return "t = null";
                } else {
                    return t.toString();
                }
            }

            @Override
            public Student fromString(String input) {
                return null;
            }
        };

        return sc;
    }

    private ChangeListener<String> getListener() {
        ChangeListener<String> cl = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                // 过滤输入文本的特殊内容
                if (t1 == null || "".equals(t1)) {
                    cb.setPlaceholder(new Label("输入内容非法！"));
                    cb.setItems(null);
                    cb.show();
                    return;
                }

                /*
                 * 过滤选项菜单
                 * 将过滤结果放入 FilteredList 中；
                 */
                FilteredList<Student> list = items.filtered(getPerdicate(t1));

                if (list.isEmpty()) {
                    cb.setItems(null);
                    cb.setPlaceholder(new Label("未找到匹配的Student"));
                    cb.hide();
                } else {
                    cb.setItems(list);
                    cb.show();
                }
            }
        };
        return cl;
    }

    private Predicate<Student> getPerdicate(String t1) {
        Predicate<Student> p = new Predicate<Student>() {
            @Override
            public boolean test(Student t) {
                return t.toString().contains(t1);
            }
        };

        return p;
    }
}
