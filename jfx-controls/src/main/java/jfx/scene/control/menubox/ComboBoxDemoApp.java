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
package jfx.scene.control.menubox;

import jfx.utils.app.ContentBox;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ComboBoxDemoApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - Demo ComboBox CellFactory";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Student stu1 = new Student("小A", 10, 90);
        Student stu2 = new Student("小B", 16, 92);
        Student stu3 = new Student("小C", 10, 97);
        Student stu4 = new Student("小D", 19, 94);
        Student stu5 = new Student("小E", 12, 99);

        ComboBox<Student> lcb = new ComboBox<>();
        lcb.setPrefWidth(200);
        lcb.setEditable(true);
        lcb.setPromptText("CellFactory");
        lcb.getItems().addAll(stu1, stu2, stu3, stu4, stu5);
        this.getChildren().add(lcb);

        lcb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                if (t == null) {
                    return "cellDemo的toString()返回了null";
                }
                if (t instanceof Student) {
                    return t.toString();
                } else {
                    return null;
                }
            }

            @Override
            public Student fromString(String input) {
                if (input == null) {
                    ol("input是null");
                    return null;
                }
                if ("".equals(input)) {
                    ol("input是空");
                    return null;
                }
                return new Student(input, 100, 50);
            }
        });

        lcb.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {
                MyStuListCell<Student> mlc = new MyStuListCell<>();
                return mlc;
            }
        });

    }

}

class MyStuListCell<T> extends ListCell<Student> {

    @Override
    protected void updateItem(Student item, boolean empty) {
        super.updateItem(item, empty);

        if (empty == false) {
            HBox hb = new HBox(5);

            ImageView iv = new ImageView("file:src/main/java/icon/fav.jpg");
            iv.setFitHeight(15);
            iv.setFitWidth(15);

            Label name = new Label(item.getName());
            Label age = new Label(String.valueOf(item.getAge()));
            Label score = new Label(String.valueOf(item.getScore()));

            hb.getChildren().addAll(iv, name, age, score);
            hb.setAlignment(Pos.CENTER_LEFT);

            this.setPrefWidth(200.0);
            this.setGraphic(hb);
            this.setContentDisplay(ContentDisplay.RIGHT);
        }
    }
}
