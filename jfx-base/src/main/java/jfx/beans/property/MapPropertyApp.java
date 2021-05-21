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

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MapPropertyApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - MapProperty MapChange";
    private SimpleMapProperty<String, String> smp;
    private ObservableMap<String, String> map;

    @Override
    public void index() {
        setConsole();
        baseDemo();
        modify();
    }

    public void baseDemo() {
        map = FXCollections.observableHashMap();
        map.put("教主", "任我行");

        smp = new SimpleMapProperty<>(map);
        smp.addListener(new MapChangeListener<String, String>() {
            @Override
            public void onChanged(MapChangeListener.Change<? extends String, ? extends String> c) {
                change(c);
            }
        });

        ol("集合中的所有元素：");
        smp.forEach((t, u) -> {
            ol(t + " - " + u);
        });
    }

    public void change(MapChangeListener.Change<? extends String, ? extends String> c) {
        if (c.wasAdded()) {
            ol("wasAdded() - " + c.wasAdded());
            ol("added - " + c.getKey() + " - " + c.getValueAdded());
        }
        if (c.wasRemoved()) {
            ol("wasRemoved() - " + c.wasRemoved());
            ol("removed - " + c.getKey() + " - " + c.getValueRemoved());
        }

        ol("\n集合中的所有元素：");
        ol(c.getMap());
    }

    public void modify() {
        map.put("圣女", "任盈盈");
        map.put("副教主", "向问天");
    }
}
