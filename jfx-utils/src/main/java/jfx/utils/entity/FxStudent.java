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
package jfx.utils.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FxStudent {

    private SimpleStringProperty name;
    private SimpleIntegerProperty age;
    private SimpleDoubleProperty score;
    private SimpleBooleanProperty gender;

    public FxStudent() {
        name = new SimpleStringProperty();
        age = new SimpleIntegerProperty();
        score = new SimpleDoubleProperty();
        gender = new SimpleBooleanProperty();
    }

    public FxStudent(String name, int age) {
        this();
        this.name.set(name);
        this.age.set(age);
    }

    public FxStudent(String name, int age, double score, boolean gender) {
        this();
        this.name.set(name);
        this.age.set(age);
        this.score.set(score);
        this.gender.set(gender);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getAge() {
        return age.get();
    }

    public void setAge(Integer age) {
        this.age.set(age);
    }

    public Double getScore() {
        return score.get();
    }

    public void setScore(Double score) {
        this.score.set(score);
    }

    public Boolean getGender() {
        return gender.get();
    }

    public Boolean isGender() {
        return gender.get();
    }

    public void setGender(Boolean gender) {
        this.gender.set(gender);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public SimpleIntegerProperty getAgeProperty() {
        return age;
    }

    public SimpleBooleanProperty getGenderProperty() {
        return gender;
    }

    public SimpleDoubleProperty getScoreProperty() {
        return score;
    }

}
