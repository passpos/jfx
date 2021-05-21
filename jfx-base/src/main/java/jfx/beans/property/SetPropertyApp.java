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
package jfx.beans.property;

import java.util.Iterator;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import jfx.core.app.ContentBox;

/**
 * B-53
 *
 * @author realpai <paiap@outlook.com>
 */
public class SetPropertyApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - SetProperty SetChange";
    private SimpleSetProperty<String> ssp;
    private ObservableSet<String> set;

    @Override
    public void index() {
        setConsole();
        base();
        modify();
    }

    /**
     * 从不同的时间和代码位置，多次获取迭代器，迭代器的指针位置都是共享的；
     * 例如，从一个位置获取了迭代器，迭代元素到某个位置A，那么在其他位置获取的迭
     * 代器的迭代位置将会从该位置A开始，而不是从头开始；
     *
     * 但是，当集合发生改变时，迭代器中存储的指针会被重置到起始位置；
     */
    public void base() {
        set = FXCollections.observableSet("A", "B", "C");
        ssp = new SimpleSetProperty<>(set);

        ssp.addListener(new SetChangeListener<String>() {
            @Override
            public void onChanged(SetChangeListener.Change<? extends String> c) {
                change(c);
            }
        });

        ol("集合中的所有元素：");
        Iterator<String> it = ssp.iterator();
        while (it.hasNext()) {
            o("\t" + it.next());
        }
        // ssp.forEach((e) -> ol(e));
        // ssp.forEach(ContentBox::ol);
    }

    private void change(SetChangeListener.Change<? extends String> c) {
        ol("\n集合中的所有元素：");
        ol(c.getSet());

        if (c.wasAdded()) {
            ol("wasAdded() - " + c.wasAdded());
            ol("getElementAdded() - " + c.getElementAdded());
        }
        if (c.wasRemoved()) {
            ol("wasRemoved() - " + c.wasRemoved());
            ol("getElementRemoved() - " + c.getElementRemoved());
        }
    }

    public void modify() {
        // ssp.set("E");
        ssp.add("D");
        ssp.remove("B");
    }
}
