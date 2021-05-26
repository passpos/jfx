/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control;

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
import jfx.core.app.ContentBox;
import utils.entity.demo.sample.Student;

/**
 * B-40
 *
 * @author passpos <paiap@outlook.com>
 */
public class CellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Cell - ComboBox";

    @Override
    public void index() {
        base();
    }

    public void base() {
        ComboBox<Student> cb = new ComboBox<>();
        cb.setPrefWidth(200);
        cb.setEditable(true);
        cb.setPromptText("ComboBox");
        getChildren().add(cb);

        Student stu1 = new Student("小A", 10, 90);
        Student stu2 = new Student("小B", 16, 92);
        Student stu3 = new Student("小C", 10, 97);
        cb.getItems().addAll(stu1, stu2, stu3);

        cb.setConverter(getConverter());
        cb.setCellFactory(getCall());

    }

    private StringConverter<Student> getConverter() {
        StringConverter<Student> sc = new StringConverter<Student>() {
            @Override
            public String toString(Student t) {
                if (t == null) {
                    return "cellDemo的toString()返回了null";
                } else {
                    return t.toString();
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
        };

        return sc;
    }

    private Callback<ListView<Student>, ListCell<Student>> getCall() {
        Callback<ListView<Student>, ListCell<Student>> callback;
        callback = new Callback<>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {
                MyStuListCell<Student> cell = new MyStuListCell<>();
                return cell;
            }
        };

        return callback;
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
