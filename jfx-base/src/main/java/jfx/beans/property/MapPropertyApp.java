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

import java.util.function.BiConsumer;
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
    public static final String TITLE = "Property - MapProperty";
    private SimpleMapProperty<String, String> smp;
    private ObservableMap<String, String> map;

    @Override
    public void index() {
        setConsole();

        map = FXCollections.observableHashMap();
        map.put("教主", "任我行");
        map.put("圣女", "任盈盈");
        smp = new SimpleMapProperty<>(map);

        baseDemo();
    }

    public void baseDemo() {
        smp.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String t, String u) {
                ob(t + " - " + u);
            }
        });

    }

    public void listenerDemo() {
        smp.addListener(new MapChangeListener<String, String>() {
            @Override
            public void onChanged(MapChangeListener.Change<? extends String, ? extends String> change) {
                ob(change.getKey());
            }
        });
    }
}
