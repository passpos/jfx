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

import javafx.beans.property.SimpleIntegerProperty;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class UnidirectionalBindingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - 单向绑定";

    @Override
    public void index() {
        baseDemo();
    }

    /**
     * 单向绑定
     *
     * 绑定的两边，数据类型必须一致；
     * 解绑后，A的数据已经被修改；
     * A 单向绑定到 B，以B为主（括号内），A将不能做出修改；
     */
    public void baseDemo() {
        SimpleIntegerProperty sip1 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty sip2 = new SimpleIntegerProperty(5);
        ol("绑定前：");
        ol("sip1：" + sip1.get());
        ol("sip2：" + sip2.get());

        // sip1单向绑定到sip2上，sip1不能控制sip2
        sip1.bind(sip2);
        ol("绑定后：");
        ol("sip1：" + sip1.get());
        ol("sip2：" + sip2.get());

        ol("绑定关系：");
        ol("sip1：" + sip1.isBound());
        ol("sip2：" + sip2.isBound());
        // sip1.set(8); // 抛出异常

        // 解绑
        sip1.unbind();
        ol("即使解绑，sip1的数据已经改变：" + sip1.get());

        sip1.set(8);
        ol("解除绑定后：");
        ol("sip1：" + sip1.get());
        ol("sip2：" + sip2.get());
    }
}
