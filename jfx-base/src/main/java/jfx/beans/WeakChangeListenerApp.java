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
package jfx.beans;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WeakChangeListenerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Beans - Listener WeakChangeListener";

    @Override
    public void index() {
        baseDemo();
        noLeakDemo();
    }

    public void baseDemo() {
        Chl chl = new Chl();
        WeakChangeListener<Number> wcl = new WeakChangeListener<>(chl);
        SimpleIntegerProperty sip = new SimpleIntegerProperty();
        sip.addListener(wcl);
        sip.set(5);
    }

    /**
     * 防止内存泄露
     *
     * 使用匿名内部类的方式，本身潜在一些安全隐患，这在事件监听中尤其凸显；
     * 为了避免这种问题，可以将匿名内部类提取出来，不再通过“new”的方式获取。
     *
     * 也可以使用 Interface javafx.beans.WeakListener 的实现：
     * WeakInvalidationListener
     * WeakChangeListener
     */
    public void noLeakDemo() {
        SimpleIntegerProperty sip = new SimpleIntegerProperty();
        InvaL il = new InvaL();

        sip.addListener(il);
        sip.set(5);

        sip.removeListener(il);
        sip.set(10);
    }
}

class Chl implements ChangeListener<Number> {

    @Override
    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        ContentBox.ol(t1);
    }

}

class InvaL implements InvalidationListener {

    @Override
    public void invalidated(Observable o) {
        ContentBox.ol("noLeakDemo：" + o);
    }
}
