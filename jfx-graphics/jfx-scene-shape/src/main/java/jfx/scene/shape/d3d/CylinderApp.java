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
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CylinderApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - 3D Cylinder 圆柱体";
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
            Cylinder c = new Cylinder(50, 150, 64);
            children.add(c);
        }
        secondaryRoot.getChildren().addAll(hBox);

        Cylinder c1 = (Cylinder) hBox.getChildren().get(0);
        c1.setRotate(30);
        // 下面的设置（使立方体沿X轴旋转）会影响上面的设置
        c1.setRotationAxis(new Point3D(1.0, 0.0, 0.0));

        // 填充模式
        Cylinder c2 = (Cylinder) hBox.getChildren().get(1);
        c2.setDrawMode(DrawMode.LINE);

        Cylinder c3 = (Cylinder) hBox.getChildren().get(2);
        c3.setDrawMode(DrawMode.LINE);
        c3.setCullFace(CullFace.FRONT);

        Cylinder c4 = (Cylinder) hBox.getChildren().get(3);
        c4.setDrawMode(DrawMode.LINE);
        c4.setCullFace(CullFace.BACK);

        Cylinder c5 = (Cylinder) hBox.getChildren().get(4);
        c5.setDrawMode(DrawMode.LINE);
        c5.setCullFace(CullFace.NONE);

        Cylinder c6 = (Cylinder) hBox.getChildren().get(5);
        c6.setDrawMode(DrawMode.FILL);
        c6.setCullFace(CullFace.FRONT);

        Cylinder c7 = (Cylinder) hBox.getChildren().get(6);
        c7.setDrawMode(DrawMode.FILL);
        c7.setCullFace(CullFace.BACK);

        Cylinder c8 = (Cylinder) hBox.getChildren().get(7);
        c8.setDrawMode(DrawMode.FILL);
        c8.setCullFace(CullFace.NONE);

    }

}