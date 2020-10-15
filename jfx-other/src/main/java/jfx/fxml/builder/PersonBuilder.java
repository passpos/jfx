/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.fxml.builder;

import javafx.util.Builder;
import utils.entity.demo.sample.Person;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PersonBuilder implements Builder<Person> {

    private String name;
    private int age;

    @Override
    public Person build() {
        return new Person(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
