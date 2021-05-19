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
package jfx.core.common;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.core.entity.FxPerson;
import utils.entity.demo.sample.Person;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class Data {

    public static ObservableList<Person> getPersonList() {
        Person m1 = new Person("独树花发自分明", 54);
        Person m2 = new Person("简繁", 32);
        Person m3 = new Person("甫和", 85);
        Person m4 = new Person("桃仁", 6);

        ObservableList<Person> list = FXCollections.observableArrayList();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        return list;
    }

    public static ObservableList<FxPerson> getFxPersonList() {
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

    public static ObservableList<SimpleStringProperty> getStringPropertyList() {
        ObservableList<SimpleStringProperty> list = FXCollections.observableArrayList();
        SimpleStringProperty ssp1 = new SimpleStringProperty("A");
        SimpleStringProperty ssp2 = new SimpleStringProperty("B");
        SimpleStringProperty ssp3 = new SimpleStringProperty("C");
        SimpleStringProperty ssp4 = new SimpleStringProperty("D");
        list.addAll(ssp1, ssp2, ssp3, ssp4);

        return list;
    }

}
