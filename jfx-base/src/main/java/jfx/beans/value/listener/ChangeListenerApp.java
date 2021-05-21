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
package jfx.beans.value.listener;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ChangeListenerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Listener - Values ChangeListener";
    private SimpleIntegerProperty sip;

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        sip = new SimpleIntegerProperty();
        sip.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                ol("更改了！");
            }
        });
    }

    public void modify() {
        sip.set(5);
        // 由于与上次设置的值相同，所以这里不会触发更改监听；
        sip.set(5);

    }
}
