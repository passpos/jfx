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
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class InvalidationListenerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Listener - InvalidationListener";

    @Override
    public void index() {
        baseDemo();
    }

    /**
     * 下面的两个例子，
     * 例1 修改三次，打印 1 次；
     * 例2 修改两次，打印 2 次，因为例2的打印中包含了Observable o；
     *
     * 亦即，对sip的修改并不会立即生效，只在需要访问sip中的数据的时候，才会真正
     * 的设置到sip中；
     */
    public void baseDemo() {
        SimpleIntegerProperty sip1 = new SimpleIntegerProperty();
        sip1.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                ob("失效监听", 300);
            }
        });
        sip1.set(2);
        sip1.set(3);    // 两次合并为一次；
        sip1.get();
        sip1.set(4);

        SimpleIntegerProperty sip2 = new SimpleIntegerProperty();
        sip2.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                ob("失效监听：" + o, 300);
            }
        });
        sip2.set(2);
        sip2.set(3);
    }

}
