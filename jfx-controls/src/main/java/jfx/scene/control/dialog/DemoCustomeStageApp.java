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
package jfx.scene.control.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DemoCustomeStageApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Dialog - 自定义Stage";
    private Stage stage;

    @Override
    public void index() {
        baseDemo();
        initStageDemo();
    }

    public void baseDemo() {
        stage = new Stage();
        Button b = new Button("点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.show();
            }
        });
        getChildren().add(b);
    }

    public void initStageDemo() {
        stage.setWidth(500);
        stage.setHeight(300);
        stage.initOwner(getPrimaryStage());
        stage.initStyle(StageStyle.UTILITY);
        stage.setAlwaysOnTop(true);

        AnchorPane ap = new AnchorPane();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
    }
}
