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
package jfx.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PerspectiveCameraApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Scene - PerspectiveCamera 透视相机";
    private Stage secondaryStage;
    private Scene secondaryScene;
    private AnchorPane secondaryRoot;
    private Button b2;
    private Button b3;
    private Button b4;

    @Override
    public void index() {
        secondStage();
    }

    public void secondStage() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Button b1 = new Button("点击");
        b2 = new Button("右移相机");
        b3 = new Button("下移相机");
        b4 = new Button("移远相机");
        vBox.getChildren().addAll(b1, b2, b3, b4);
        getChildren().add(vBox);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                secondaryStage = new Stage();
                secondaryRoot = new AnchorPane();
                secondaryScene = new Scene(secondaryRoot, 800, 600, true);

                baseDemo();

                secondaryStage.setScene(secondaryScene);
                secondaryStage.show();
            }
        });
    }

    protected void baseDemo() {
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 100.0);

        Box box1 = new Box(100, 100, 300);
        Box box2 = new Box(100, 100, 300);
        Box box3 = new Box(100, 100, 300);
        hBox.getChildren().addAll(box1, box2, box3);
        secondaryRoot.getChildren().addAll(hBox);
        PerspectiveCamera pc = new PerspectiveCamera();
        secondaryScene.setCamera(pc);

        // 旋转
        // pc.setRotationAxis(Rotate.Y_AXIS);
        // pc.setRotate(60);
        // 设置视角
        pc.setFieldOfView(120);
        // pc.setNearClip(1.2);
        // pc.setFarClip(1.6);

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pc.setTranslateX(100);
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pc.setTranslateY(100);
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                pc.setTranslateZ(-100);
            }
        });
    }

}
