/*
 * Copyright (C) 2021 passpos <paiap@outlook.com>
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
package jfx.core.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.core.entity.FxPerson;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class Data {

    public static ObservableList<FxPerson> getPersonList() {
        ObservableList<FxPerson> list = FXCollections.observableArrayList();
        FxPerson pe1 = new FxPerson("教化", "15", true);
        FxPerson pe2 = new FxPerson("北京饭店", "56", false);
        FxPerson pe3 = new FxPerson("复活卡", "69", true);
        list.add(pe1);
        list.add(pe2);
        list.add(pe3);
        return list;
    }

    public static ObservableList<String> getStringList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("data - a");
        list.add("data - b");
        list.add("data - c");
        list.add("data - d");
        list.add("data - e");
        return list;
    }

}
