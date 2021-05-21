/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.binding;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jfx.core.app.ContentBox;

/**
 * B-61 在集合的键上设置“绑定”
 * 通过这个“绑定对象”，就可以访问键对应的具体的值；
 *
 * valueAt()方法继承自抽象的ListExpression类和MapExpression类；
 *
 * 这仅对于list与map类型，由于set没有顺序，即没有key，所以不可以设置；
 *
 * @author passpos <paiap@outlook.com>
 */
public class CollectionBindingDemo4 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Collection valueAt";
    private SimpleListProperty<String> slp;
    private VBox vb;

    @Override
    public void index() {
        base();
        valueAt();
        modify();
    }

    public void base() {
        ObservableList<String> ol = FXCollections.observableArrayList();
        slp = new SimpleListProperty<>(ol);
        slp.add("A");
        slp.add("B");
        slp.add("C");
        slp.add("D");
        slp.add("E");

        vb = new VBox();
        setLeftAnchor(vb, 100.0);
        getChildren().add(vb);
    }

    /**
     * 将集合属性中的值逐个绑定、转换到 VBox 的子组件中；
     */
    public void valueAt() {
        for (int i = 0; i < slp.size(); i++) {
            Label label = new Label("A");
            label.setFont(new Font(40));

            // 将集合中的值转换为一个可绑定的对象；
            ObjectBinding<String> b = slp.valueAt(i);

            // 将某个属性与这个“可绑定的对象”绑定；
            label.textProperty().bind(b);

            vb.getChildren().add(label);
        }
    }

    public void modify() {
        Button b = new Button("点击修改源数据");
        getChildren().add(b);
        setTopAnchor(b, 300.0);
        setLeftAnchor(b, 100.0);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                slp.get(2);
                slp.set(2, "HHH");
            }
        });
    }

    public void demo() {
        TextField tf1 = new TextField();
        setLeftAnchor(tf1, 150.0);

        TextField tf2 = new TextField();
        setTopAnchor(tf2, 30.0);
        setLeftAnchor(tf2, 150.0);

        getChildren().addAll(tf1, tf2);

        tf2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if ("".equals(t1)
                        || tf1.getText().equals("")) {
                    ol("请输入内容！");
                    return;
                }

                int i = -1;
                try {
                    i = Integer.parseInt(tf1.getText());
                } catch (NumberFormatException e) {
                    ol("请输入数字！");
                }

                if (i > -1 && i < slp.size() - 1) {
                    slp.set(i, t1);
                } else {
                    ol("超出索引！");
                }
            }
        });
    }
}
