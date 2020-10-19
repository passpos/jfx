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

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import jfx.core.app.ContentBox;

/**
 * 参考：
 * javafx.beans.binding.NumberBinding
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComputingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - 绑定计算";
    private SimpleIntegerProperty sip1;
    private SimpleIntegerProperty sip2;
    private IntegerBinding ib;
    private NumberBinding nb;

    @Override
    public void index() {
        setConsole();
        baseDemo();
        bindingDemo();
        bindingComputingDemo();
    }

    public void baseDemo() {
        sip1 = new SimpleIntegerProperty(1);
        sip2 = new SimpleIntegerProperty(5);

        ib = sip1.add(4);                     // 加法；
        ob(ib.get());        // 返回 5（1+4）；
        ob(sip1.get());      // 返回 1（计算过程不会影响到原来的值）；

        nb = sip1.add(sip2);                // 加法；
        ob(nb.intValue());   // 返回 6（1+5）；

        ob(sip1.subtract(3).get());     // 减法；
        ob(sip2.divide(5).get());       // 除法；
        ob(sip2.multiply(5).get());     // 乘法；

        sip1.add(1).subtract(7).multiply(4).get();  // 允许连续计算；
    }

    /**
     * 计算结果会随着参与计算的数据的变化而变化，而不必通过再次计算才能的到结果。
     *
     * 对于一般的数值计算，是必须在次调用一次计算过程，才能得到新的结果；
     *
     * 延迟计算：
     * 对于 sip1、sip2的修改，虽然 ib、nb 会收到通知，但并不会执行计算过程。
     */
    public void bindingDemo() {
        ob("绑定计算：", 200);

        sip1.set(10);
        ob(ib.get());

        sip2.set(18);
        ob(nb.intValue());
    }

    public void bindingComputingDemo() {
        // 方法1：
        Button btn1 = new Button("绑定计算1");
        this.getChildren().add(btn1);

        btn1.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        // 方法2：
        SimpleIntegerProperty x = new SimpleIntegerProperty(2);
        DoubleBinding bind = this.widthProperty().divide(x);

        Button btn2 = new Button("绑定计算2");
        this.getChildren().add(btn2);
        setTopAnchor(btn2, 50.0);

        btn2.prefWidthProperty().bind(bind);

        // 方法3（类型不同时）：
        TextField tf = new TextField();
        this.getChildren().add(tf);
        setTopAnchor(tf, 80.0);

        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                try {
                    int value = Integer.parseInt(t1);
                    x.set(value);
                } catch (NumberFormatException e) {
                    ol("异常");
                }
            }

        });
    }
}
