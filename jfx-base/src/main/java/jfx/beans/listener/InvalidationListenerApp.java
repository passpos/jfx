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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class InvalidationListenerApp extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Listener - Beans InvalidationListener";
    private SimpleIntegerProperty sip1;
    private SimpleIntegerProperty sip2;

    @Override
    public void index() {
        baseDemo();
        modify();
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
        sip1 = new SimpleIntegerProperty();
        sip2 = new SimpleIntegerProperty();

        sip1.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                ol("失效监听");
            }
        });

        sip2.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                ol("更改监听：" + o);
            }
        });
    }

    public void modify() {
        ol("第一次修改");
        sip1.set(2);

        ol("第二次修改");
        sip1.set(3);

        sip1.get(); // 上面的两次因为这条语句，合并为一次；

        ol("第三次修改");
        sip1.set(4);

        sip2.set(2);
        sip2.set(3);
    }

}
