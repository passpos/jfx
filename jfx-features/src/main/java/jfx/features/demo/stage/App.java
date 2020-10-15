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
package jfx.features.demo.stage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class App extends Application {

    public Stage stage;
    public Scene scene;
    public AnchorPane ap;
    public double a;
    public double b;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        ap = new AnchorPane();
        scene = new Scene(ap);

        baseSetting();
        dragStageDemo();
        titleBarDemo();

        stage.show();
    }

    public void baseSetting() {
        stage.setTitle(Thread.currentThread().getName());
        stage.getIcons().add(new Image("file:icon/fav.jpg"));

        stage.setWidth(700);
        stage.setHeight(400);

        stage.setResizable(true);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);

    }

    /**
     * 拖拽窗口；
     */
    public void dragStageDemo() {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                a = t.getScreenX() - stage.getX();
                b = t.getScreenY() - stage.getY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setX(t.getScreenX() - a);
                stage.setY(t.getScreenY() - b);
            }
        });
    }

    public void titleBarDemo() {
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#AA5566"), new CornerRadii(3), new Insets(15)));
        ap.setBackground(bg);
        ap.setBorder(new Border(new BorderStroke(
                Color.BLUE,
                BorderStrokeStyle.DASHED,
                new CornerRadii(3),
                new BorderWidths(2),
                new Insets(15)
        )));
        // 设置透明的场景图背景；
        scene.setFill(Paint.valueOf("#ffffff00"));
    }
}
