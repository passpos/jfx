/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.fxml.builder;

import java.util.HashMap;
import javafx.util.Builder;
import utils.entity.demo.sample.Person;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PersonBuilderMap extends HashMap<String, Object> implements Builder<Person> {

    private String name;
    private int age;

    @Override
    public Person build() {
        return new Person(name, age);
    }

    @Override
    public Object put(String key, Object value) {
        if ("name".equals(key)) {
            this.name = String.valueOf(value);
        } else if ("age".equals(key)) {
            this.age = Integer.valueOf(String.valueOf(value));
        }
        return super.put(key, value);
    }

}
