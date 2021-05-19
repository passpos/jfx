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
import javafx.util.StringConverter;
import utils.entity.demo.sample.Person;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class Converter {

    /**
     * 设置选项列对象的展示方式（字符串转换）
     *
     * 将ListCell中数据的属性类型，转换为普通的字符串类型；
     * @return
     */
    public static StringConverter<SimpleStringProperty> forStringProperty() {
        StringConverter<SimpleStringProperty> sc = new StringConverter<>() {
            @Override
            public String toString(SimpleStringProperty t) {
                return t.get();
            }

            @Override
            public SimpleStringProperty fromString(String string) {
                return new SimpleStringProperty(string);
            }

        };

        return sc;
    }

    public static StringConverter<Person> getPersonConverter() {
        StringConverter<Person> sc = new StringConverter<>() {
            @Override
            public String toString(Person t) {
                return t.getName();
            }

            @Override
            public Person fromString(String string) {
                Person p = new Person();
                p.setName(string);
                return p;
            }
        };

        return sc;
    }

}
