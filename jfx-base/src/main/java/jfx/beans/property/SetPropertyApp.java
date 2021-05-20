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

    public static final boolean SHOWING = true;
    public static final String TITLE = "Property - SetProperty SetChange";
    private SimpleSetProperty<String> ssp;
    private ObservableSet<String> set;

    @Override
    public void index() {
        setConsole();
        base();
    }

    public void base() {
        set = FXCollections.observableSet("A", "B", "C");
        ssp = new SimpleSetProperty<>(set);

        ssp.addListener(new SetChangeListener<String>() {
            @Override
            public void onChanged(SetChangeListener.Change<? extends String> c) {
                change(c);
            }
        });

        // ssp.set("E");
        ssp.add("D");
        ssp.remove("D");
    }

    public void change(SetChangeListener.Change<? extends String> c) {
        c.getSet().forEach((e) -> ol(e));

        // 没有索引所以不需要next()方法
        Iterator<String> it = ssp.iterator();
        while (it.hasNext()) {
            ol("next() - " + it.next());
        }

        ol("所有元素：");
        ssp.forEach((e) -> ol(e));
    }
}
