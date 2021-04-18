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

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.core.app.ContentBox;

/**
 * 关于绑定
 *
 * 当在应用中的某处，绑定了一个属性值时，就不应当再去手动设定值；
 *
 * @author realpai <paiap@outlook.com>
 */
public class BindingApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Binding - Bindings";
    private SimpleIntegerProperty sip;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        sip = new SimpleIntegerProperty(10);
        MyIntegerBinding mib = new MyIntegerBinding(0);
        sip.bind(mib);

        ob("MyIntegerBinding = " + mib);
        ob("SimpleIntegerProperty = " + sip);

        mib.setValue(20);

        ob("MyIntegerBinding = " + mib);
        ob("SimpleIntegerProperty = " + sip);
    }
}

class MyIntegerBinding extends IntegerBinding {

    private SimpleIntegerProperty sip = new SimpleIntegerProperty();

    MyIntegerBinding(int value) {
        this.bind(sip);
        sip.set(value);
    }

    public void setValue(int value) {
        sip.set(value);
    }

    /**
     * 使用这样的方式可以将一个数据绑定到经过计算的数据上，而不必要求绑定的两方
     * 数据相同；
     *
     * @return
     */
    @Override
    protected int computeValue() {
        return sip.get() * 4;
    }

}
