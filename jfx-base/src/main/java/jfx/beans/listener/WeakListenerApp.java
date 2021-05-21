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
package jfx.beans.listener;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import jfx.core.app.ContentBox;

/**
 * B-54 防止内存泄漏
 *

 *
 * 也可以将匿名内部类提取出来，不再通过“new”的方式获取。
 * 参考：InvalidationListenerDemo
 *
 * @author realpai <paiap@outlook.com>
 */
public class WeakListenerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Listener - Beans WeakListener";
    private SimpleIntegerProperty sip;

    @Override
    public void index() {
        base();
        noLeakDemo();
        modify();
    }

    public void base() {
        sip = new SimpleIntegerProperty();

    }

    public void noLeakDemo() {
        Chl chl = new Chl();
        WeakChangeListener<Number> wcListener = new WeakChangeListener<>(chl);

        sip.addListener(wcListener);
    }

    public void modify() {
        sip.set(5);
    }
}

class Chl implements ChangeListener<Number> {

    @Override
    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        ContentBox.ol(t1);
    }

}
