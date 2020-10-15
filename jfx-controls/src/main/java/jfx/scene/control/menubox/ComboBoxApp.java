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
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComboBoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - ComboBox 下拉菜单";
    private ComboBox<String> cb;
    private ObservableList<Student> itemsOfCb;

    @Override
    public String toString() {
        return "ComboBoxApp{" + '}';
    }

    @Override
    public void index() {
        // 基本使用演示
        baseDemo();

        // 菜单选择事件
        eventDemo();

        // 点击事件
        onClickDemo();

        // 动态添加数据
        addDataDemo();

        // 关键字搜索
        searchDemo();

        // 自定义单元格
        cellDemo();
    }

    /**
     * ComboBox 基本使用演示
     */
    public void baseDemo() {
        cb = new ComboBox<>();
        cb.getItems().addAll("bt1", "bt2", "bt3", "bt4", "bt5", "bt6");

        Button btn = new Button("按钮");
        AnchorPane.setLeftAnchor(btn, 200.0);

        /*
         * 设置可以编辑
         *
         * 注意：如果设置为false，
         * setPromptText()设置的内容就会展示到ComboBox上（类似于标题）；
         * 若setPromptText()未设置，当setConverter()的toString()方法，在处理null
         * 时，返回的字符串会展示在同一个地方；
         */
        cb.setEditable(true);

        // 文本提示
        cb.setPromptText("请输入数据");

        // 占位符
        cb.setPlaceholder(new Button("占位后，其他元素不可见"));

        // 可见的行数（滚动条）
        cb.setVisibleRowCount(4);
        cb.setPrefWidth(200.0);

        this.getChildren().addAll(cb, btn);
    }

    /**
     * 菜单选择事件
     */
    public void eventDemo() {
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ol("菜单选择事件" + t1);
            }
        });
    }

    /**
     * 点击事件
     */
    public void onClickDemo() {
        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ol("点击事件");
            }
        });
    }

    /**
     * 动态添加数据
     */
    public void addDataDemo() {
        Student stu1 = new Student("小A", 10, 90);
        Student stu2 = new Student("小B", 16, 92);
        Student stu3 = new Student("小C", 10, 97);
        Student stu4 = new Student("小D", 19, 94);
        Student stu5 = new Student("小E", 12, 99);

        ComboBox<Student> lcb = new ComboBox<>();
        lcb.setPrefWidth(200);
        lcb.setEditable(true);
        lcb.setPromptText("动态添加数据");
        lcb.getItems().addAll(stu1, stu2, stu3, stu4, stu5);
        itemsOfCb = lcb.getItems();
        AnchorPane.setTopAnchor(lcb, 150.0);
        this.getChildren().add(lcb);

        // 选项事件与点击事件
        selectEvent(lcb);

        // 设置属性值转换
        convertData(lcb);
    }

    /**
     * 选项事件与点击事件
     *
     * itemsOfCb是引用类型；
     * 每次执行cb.getItems()，getItems()都会执行一次；
     * 所以每次得到的结果，总是与为 itemsOfCb赋值时的地址不同；
     *
     * itemsOfCb虽然是引用类型，但只被赋值过一次；
     * 后面调用的结果不会写入itemsOfCb引用的空间中；
     *
     * @param lcb ComboBox
     */
    public void selectEvent(ComboBox<Student> lcb) {
        // 选项的选择状态
        lcb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> ov, Student t, Student t1) {
                ol(t1);
            }
        });

        // 设置点击事件
        lcb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

            }
        });

    }

    /**
     * 转换数据对象的属性值为字符串
     *
     * 将对象数据转换为字符串，并展示到下拉菜单的选项中；
     * 如果没有设置setConverter()方法，只要用于展示的对象重写了toString()方法，也可
     * 以将toString()的结果展示出来；
     *
     * @param lcb ComboBox
     */
    public void convertData(ComboBox<Student> lcb) {
        lcb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                if (t == null) {
                    return "t = null";
                }

                /*
                 * 动态添加数据
                 *
                 * 如果lcb下的选项中包含该元素，则将其转换为字符串并展示出来；
                 * 否则添加到选项中；
                 */
                if (lcb.getItems().contains(t)) {
                    return t.toString();
                } else {
                    lcb.getItems().add(t);
                    return t.toString();
                }
            }

            /*
             * 此方法接收从ComboBox输入的数据
             * 如果ComboBox不可编辑，则次方法不运行；
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
        });
    }

    /**
     * 关键字搜索
     */
    public void searchDemo() {
        ComboBox<Student> lcb = new ComboBox<>();
        lcb.setPrefWidth(200);
        lcb.setEditable(true);
        lcb.setPromptText("关键字搜索");
        lcb.getItems().addAll(itemsOfCb);
        AnchorPane.setTopAnchor(lcb, 150.0);
        AnchorPane.setLeftAnchor(lcb, 250.0);
        this.getChildren().add(lcb);

        TextField tf = lcb.editorProperty().get();

        // 对输入文本进行监听
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> ov,
                    String t,
                    String t1
            ) {
                // 过滤输入文本的特殊内容
                if (t1 == null || "".equals(t1)) {
                    lcb.setPlaceholder(new Label("输入内容非法！未找到匹配的Student"));
                    return;
                }

                /*
                 * 过滤选项菜单
                 * 将过滤结果放入 FilteredList 中；
                 */
                FilteredList<Student> restList = itemsOfCb.filtered(new Predicate<Student>() {
                    @Override
                    public boolean test(Student t) {
                        return t.toString().contains(t1);
                    }
                });

                if (restList.isEmpty()) {
                    lcb.setItems(null);
                    lcb.setPlaceholder(new Label("未找到匹配的Student"));
                } else {
                    lcb.setItems(restList);
//                    lcb.hide();
//                    lcb.show();
                }
            }

        });
    }

    /**
     * 自定义单元格
     */
    public void cellDemo() {
        ComboBox<String> lcb = new ComboBox<>();
        lcb.setPromptText("自定义单元格");
        lcb.setPrefWidth(200.0);
        lcb.getItems().addAll("abc", "abc", "abc", "abc");
        AnchorPane.setTopAnchor(lcb, 300.0);
        this.getChildren().add(lcb);

        lcb.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String t) {
                if (t == null) {
                    return "cellDemo的toString()返回了null";
                }
                if (!"".equals(t)) {
                    return t;
                } else {
                    return null;
                }
            }

            @Override
            public String fromString(String input) {
                if (input == null) {
                    ol("input是null");
                    return null;
                }
                if ("".equals(input)) {
                    ol("input是空");
                    return null;
                }
                return input;
            }
        });

        lcb.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                MyListCell<String> mlc = new MyListCell<>();
                return mlc;
            }
        });

    }
}

class MyListCell<T> extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty == false) {
            HBox hb = new HBox();
            hb.getChildren().addAll(new Button("btn1"), new Button("btn2"));

            this.setPrefWidth(200.0);
            this.setGraphic(hb);
            this.setContentDisplay(ContentDisplay.RIGHT);
        }
    }
}
