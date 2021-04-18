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

import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jfx.core.app.ContentBox;

/**
 * Properties 参考：
 *
 * Module javafx.base
 * Package javafx.beans.property
 *
 * @author realpai <paiap@outlook.com>
 */
public class PropertyApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - SimpleProperty";

    @Override
    public void index() {
        setConsole();
        baseDemo();
        simplePropertyDemo();
        expressionDemo();
        memberPropertyDemo();
        flexiblePropertyDemo();
    }

    /**
     * SimpleProperty对属性的监听演示
     */
    public void baseDemo() {
        ob("baseDemo", 200);

        SimpleIntegerProperty sip = new SimpleIntegerProperty(100);
        ob(sip.negate().get()); // 取反（不影响原属性值）；
        ob(sip.get());  // 仍为100；

        sip.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ob("t：" + t);
                ob("t1：" + t1);
            }
        });

        sip.set(50);
    }

    public void simplePropertyDemo() {

    }

    public void expressionDemo() {
        ob("Expression", 200);

        SimpleIntegerProperty sip1 = new SimpleIntegerProperty(5);
        SimpleIntegerProperty sip2 = new SimpleIntegerProperty(7);
        IntegerExpression iexp = SimpleIntegerProperty.integerExpression(sip1);
        ob(iexp.get());

        sip1.set(10);
        ob(iexp.get());
    }

    /**
     * 对象的成员属性被设置为 SimpleProperty类型
     *
     * 这仅当成员属性的类型是简单类型时；
     */
    public void memberPropertyDemo() {
        Student s = new Student("simpleProperty", 15);
        s.getSimpleStringPropertyName().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ob("t：" + t);
                ob("t1：" + t1);
            }
        });

        Button btn = new Button("点击修改（simpleProperty）");
        this.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                s.setName("杜燕歌");
            }
        });
    }

    /**
     * 仅在需要的时候，获得事件监听的能力；
     * 如果不需要，不会浪费资源（不生成SimpleProperty对象）；
     */
    public void flexiblePropertyDemo() {
        Data d = new Data("flexibleProperty");

        Button btn = new Button("点击修改（flexibleProperty）");
        AnchorPane.setTopAnchor(btn, 30.0);
        this.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                d.setName("杜燕舞");
            }
        });

        d.nameProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ob("t：" + t);
                ob("t1：" + t1);
            }
        });
    }

}

class Student {

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();

    Student(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }

    public SimpleStringProperty getSimpleStringPropertyName() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty getSimpleIntegerPropertyAge() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

}

class Data {

    private String name;
    private SimpleStringProperty nameProperty;

    Data(String name) {
        if (nameProperty == null) {
            this.name = name;
        } else {
            this.nameProperty.set(name);
        }
    }

    /**
     * 激活并获取 nameProperty
     *
     * 用户在访问 nameProperty 时，才会创建 StringProperty 的对象；
     * 其他情况下，该对象就是一个普通的 bean ，没有事件监听的能力；
     * @return StringProperty
     */
    public StringProperty nameProperty() {
        if (nameProperty == null) {
            nameProperty = new SimpleStringProperty(this, "name", name);
        }
        return nameProperty;
    }

    /**
     * getter
     *
     * @return
     */
    public String getName() {
        if (nameProperty == null) {
            return this.name;
        } else {
            return this.nameProperty.get();
        }
    }

    /**
     * setter
     * @param name
     */
    public void setName(String name) {
        if (nameProperty == null) {
            this.name = name;
        } else {
            this.nameProperty.set(name);
        }
    }

}
