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

import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WhenApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - When";

    @Override
    public void index() {
        baseDemo();
        whenDemo();
    }

    /**
     * 这里类似于绑定计算；
     */
    public void baseDemo() {
        int a = 1;
        int b = 2;

        int c = a < b ? 10 : 20;
        ob(c);

        a = 3;
        /*
         * 这里的 c 是int类型，为值传递，即使参与运算的数据发生了改变，由于运算
         * 过程并不会在此发生，所以，c 的值并不会改变；
         */
        ob(c);
    }

    /**
     * When的构造函数接受一个 ObservableBooleanValue 类型的参数；
     */
    public void whenDemo() {
        SimpleIntegerProperty sip1 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty sip2 = new SimpleIntegerProperty(2);

        // 简单演示（使用基本类型进行比较）；
        SimpleBooleanProperty sbp = new SimpleBooleanProperty(SHOWING);
        When when1 = new When(sbp);
        NumberBinding owBinding1 = when1.then(10).otherwise(20);
        ob(owBinding1.intValue());

        // 使用属性类型进行比较；
        When when2 = new When(sip1.lessThan(sip2));
        NumberBinding owBinding2 = when2.then(10).otherwise(20);
        ob(owBinding2.intValue());

        sip1.set(3);
        ob(owBinding2.intValue());

    }
}
