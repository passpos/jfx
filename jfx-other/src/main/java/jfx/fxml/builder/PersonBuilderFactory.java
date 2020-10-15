/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.fxml.builder;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import utils.entity.demo.sample.Person;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PersonBuilderFactory implements BuilderFactory {

    private final JavaFXBuilderFactory jbf = new JavaFXBuilderFactory();

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (type == Person.class) {
            return new PersonBuilder();
        } else {
            return jbf.getBuilder(type);
        }
    }

}
