package jfx.core.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FxPerson {

    private SimpleStringProperty name;
    private SimpleStringProperty age;
    private SimpleBooleanProperty gender;

    public FxPerson() {
        name = new SimpleStringProperty();
        age = new SimpleStringProperty();
        gender = new SimpleBooleanProperty();
    }

    public FxPerson(String name, String age) {
        this();
        this.name.set(name);
        this.age.set(age);
    }

    public FxPerson(String name, String age, boolean gender) {
        this.name.set(name);
        this.age.set(age);
        this.gender.set(gender);
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

    public boolean getGender() {
        return gender.get();
    }

    public SimpleBooleanProperty getGenderProperty() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }

}
