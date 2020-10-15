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

import java.util.Locale;
import java.util.concurrent.Callable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.utils.app.ContentBox;

/**
 * Class final javafx.beans.binding.Bindings
 * 工具类
 *
 * @author realpai <paiap@outlook.com>
 */
public class BindingsApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Bindings";
    private SimpleIntegerProperty sip;

    @Override
    public void index() {
        concatDemo();
        formatDemo();
        maxAndMinDemo();
        createBindingDemo();
        selectStringDemo();
    }

    /**
     * concat() 将多个对象的值进行连接；
     * 接收同类型的可变参数；
     *
     * sip.asString() 把sip当做字符串进行输出；
     * 参数1 地区语言；
     * 参数2 java占位符；
     */
    public void concatDemo() {
        sip = new SimpleIntegerProperty(10);
        StringExpression sep = Bindings.concat("sip = ", sip.asString(Locale.getDefault(), "%s"));
        ob(sep.get());
    }

    public void formatDemo() {
        StringExpression sep1 = Bindings.format("sip = ", sip);
        ob(sep1.get());

        StringExpression sep2 = Bindings.format("sip = %s", sip);
        ob(sep2.get());
    }

    public void maxAndMinDemo() {
        SimpleIntegerProperty s1 = new SimpleIntegerProperty(10);
        SimpleIntegerProperty s2 = new SimpleIntegerProperty(20);

        ob(Bindings.max(s2, s1).intValue());
        ob(Bindings.min(s1, s2).intValue());
    }

    /**
     * 该方法允许绑定的两个数据的值和类型不同；
     *
     * 参数1 Callable接口实例；
     * 参数2 属性类型；
     *
     * 这里是数字绑定到字符串（createStringBinding与SimpleIntegerProperty）；
     * 最终形成了 StringBinding；
     */
    public void createBindingDemo() {
        ob("createBinding()", 200.0);

        SimpleIntegerProperty s1 = new SimpleIntegerProperty(10);
        SimpleIntegerProperty s2 = new SimpleIntegerProperty(20);
        StringBinding csb = Bindings.createStringBinding(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String v = "Hello";
                        if (s1.get() == 1) {
                            v = "A";
                        } else if (s1.get() == 2) {
                            v = "B";
                        }
                return v;
            }
        },
                s1
        );

        ob(s1.get());
        ob(csb.get());

        s1.set(1);

        ob(s1.get());
        ob(csb.get());
    }

    /**
     * 适用于嵌套结构（一个类型的对象是另一个类型的成员属性）的数据类型；
     *
     */
    public void selectStringDemo() {
        StringBinding ss = Bindings.selectString(sip, "");
    }
}
