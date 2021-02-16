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
package jfx.scene.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SpinnerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - Spinner 微调";
    public Spinner<Integer> spinner;
    private ObservableList<Student> list;

    @Override
    public void index() {
        baseDemo();
        listenerDemo();
        constDemo();
        setUserData();
        setObjData();
    }

    public void baseDemo() {
        // 前两个参数表示范围（闭区间），第三个参数表示初始化值；
        spinner = new Spinner<>(0, 10, 5);

        // 如果输入了非法字符，再去点击上下键，会抛出异常；
        spinner.setEditable(true);
        this.getChildren().add(spinner);
    }

    public void listenerDemo() {
        spinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> ov, Integer t, Integer t1) {
                ol(t1);
            }
        });
    }

    public void constDemo() {
        // 第四个参数表示步进值
        Spinner<Integer> sp1 = new Spinner<>(0, 10, 5, 2);
        sp1.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
        setLeftAnchor(sp1, 200.0);

        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.addAll("A", "B", "C", "D");
        Spinner<String> sp2 = new Spinner<>(ol);
        sp2.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        setTopAnchor(sp2, 30.0);

        this.getChildren().addAll(sp1, sp2);
    }

    public void setUserData() {
        Student st1 = new Student("小护法", 45, 454);
        Student st2 = new Student("大护法", 54, 545);
        Student st3 = new Student("护法长老", 88, 999);
        list = FXCollections.observableArrayList();
        list.addAll(st1, st2, st3);

        SVF svf = new SVF(list);
        /*
         * SpinnerValueFactory接收一个列表类型的参数；
         * 该列表中的数据应该被转换为字符串。同时要设置次序（这在SVF的内部进行实现）；
         */
        svf.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                return t.toString();
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });

        Spinner<Student> sp = new Spinner<>();
        sp.setValueFactory(svf);

        // 设置默认值（不设置时为空）
        // svf.setValue(st2);
        setTopAnchor(sp, 30.0);
        setLeftAnchor(sp, 200.0);
        this.getChildren().add(sp);
    }

    /**
     * 下面是使用内置方式实现的数据呈现
     */
    public void setObjData() {
        SpinnerValueFactory.ListSpinnerValueFactory<Student> lsvf = new SpinnerValueFactory.ListSpinnerValueFactory<>(list);
        lsvf.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                return t.toString();
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });

        Spinner<Student> sp = new Spinner<>();
        sp.setValueFactory(lsvf);
        setTopAnchor(sp, 60.0);
        this.getChildren().add(sp);
    }

}

class SVF extends SpinnerValueFactory<Student> {

    private int index = -1;
    private ObservableList<Student> list;

    public SVF(ObservableList<Student> list) {
        this.list = list;
    }

    @Override
    public void decrement(int steps) {
        if (index - steps <= -1) {
            index = 0;
        } else {
            index -= steps;
        }
        this.setValue(list.get(index));
    }

    @Override
    public void increment(int steps) {
        if (index + steps >= list.size()) {
            index = list.size() - 1;
        } else {
            index += steps;
        }
        this.setValue(list.get(index));
    }

}
