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

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class LogicApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - 绑定判断";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        SimpleIntegerProperty sip1 = new SimpleIntegerProperty(10);
        SimpleIntegerProperty sip2 = new SimpleIntegerProperty(10);
        sip1.add(1).subtract(7).multiply(4);

        boolean rel1 = sip1.greaterThan(sip2).get();
        ob(rel1);

        BooleanBinding rel2 = sip1.greaterThan(sip2);
        ob(rel2.get());

        sip2.set(1000);
        ob(rel2.get());

        BooleanBinding rel3 = sip1.lessThan(sip2);
        ob(rel3.get());

        BooleanBinding rel4 = sip1.isEqualTo(sip2);
        BooleanBinding rel5 = sip1.isEqualTo(10, 1);    // 参数2是误差；

        BooleanBinding rel6 = sip1.isNotEqualTo(sip2);
        BooleanBinding rel7 = sip1.isNotEqualTo(sip2);  // 参数2是误差；

        BooleanBinding rel8 = sip1.greaterThanOrEqualTo(sip2);
        BooleanBinding rel9 = sip1.lessThanOrEqualTo(sip2);
    }
}
