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
public class BidirectionalBindingApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - 双向绑定";

    @Override
    public void index() {
        baseDemo();
    }

    /**
     * 双向绑定
     *
     * 绑定的两边，数据类型必须一致；
     * A 双向绑定到 B，以B为主（括号内），A仍可以被修改；
     * 解绑后，A的数据已经被修改；
     */
    public void baseDemo() {
        SimpleIntegerProperty sip1 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty sip2 = new SimpleIntegerProperty(5);
        ob("绑定前：", 400);
        ob(sip1.get());
        ob(sip2.get());

        sip1.bindBidirectional(sip2);
        ob("绑定后：", 400);
        ob(sip1.get());
        ob(sip2.get());

        // 解绑
        sip1.unbindBidirectional(sip2);
        ob(sip1.get());

        // 反向绑定
        sip2.bindBidirectional(sip1);
        ob("反向绑定后：", 400);
        ob(sip1.get());
        ob(sip2.get());

        ob("绑定关系：", 400);
        ob("sip1：" + sip1.isBound());
        ob("sip2：" + sip2.isBound());
    }
}
