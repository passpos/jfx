package jfx.core.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FxPerson {

    private SimpleStringProperty name;
    private SimpleStringProperty age;

    public FxPerson() {
        name = new SimpleStringProperty();
        age = new SimpleStringProperty();
    }

    public FxPerson(String name, String age) {
        this();
        this.name.set(name);
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty getAgeProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

}
