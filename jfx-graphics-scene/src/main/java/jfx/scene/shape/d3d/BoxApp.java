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
package jfx.scene.shape.d3d;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BoxApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - 3D Box 立方体";
    private Stage secondaryStage;
    private Scene secondaryScene;
    private AnchorPane secondaryRoot;

    @Override
    public void index() {
        secondStage();
    }

    public void secondStage() {
        Button b = new Button("点击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                secondaryStage = new Stage();
                secondaryRoot = new AnchorPane();
                secondaryRoot.setStyle("-fx-background-color:#443333");

                secondaryScene = new Scene(secondaryRoot, 800, 600, true, SceneAntialiasing.BALANCED);
                baseDemo();
                secondaryScene.setCamera(new PerspectiveCamera());
                secondaryStage.setScene(secondaryScene);
                secondaryStage.show();

            }
        });
        getChildren().add(b);
    }

    protected void baseDemo() {
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 100.0);

        ObservableList<Node> children = hBox.getChildren();
        for (int i = 0; i < 9; i++) {
            Box box = new Box(50, 50, 300);
            children.add(box);
        }
        secondaryRoot.getChildren().addAll(hBox);

        Box box1 = (Box) hBox.getChildren().get(0);
        box1.setRotate(30);
        // 下面的设置（使立方体沿X轴旋转）会影响上面的设置
        box1.setRotationAxis(new Point3D(1.0, 0.0, 0.0));

        // 填充模式
        Box box2 = (Box) hBox.getChildren().get(1);
        box2.setDrawMode(DrawMode.LINE);

        Box box3 = (Box) hBox.getChildren().get(2);
        box3.setDrawMode(DrawMode.LINE);
        box3.setCullFace(CullFace.FRONT);

        Box box4 = (Box) hBox.getChildren().get(3);
        box4.setDrawMode(DrawMode.LINE);
        box4.setCullFace(CullFace.BACK);

        Box box5 = (Box) hBox.getChildren().get(4);
        box5.setDrawMode(DrawMode.LINE);
        box5.setCullFace(CullFace.NONE);

        Box box6 = (Box) hBox.getChildren().get(5);
        box6.setDrawMode(DrawMode.FILL);
        box6.setCullFace(CullFace.FRONT);

        Box box7 = (Box) hBox.getChildren().get(6);
        box7.setDrawMode(DrawMode.FILL);
        box7.setCullFace(CullFace.BACK);

        Box box8 = (Box) hBox.getChildren().get(7);
        box8.setDrawMode(DrawMode.FILL);
        box8.setCullFace(CullFace.NONE);

    }

}
