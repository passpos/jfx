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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class MeshViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - 3D MeshView";
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
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 100.0);

        // 点坐标（按行索引）
        float[] points = {
            0, 0, 0,
            100, 0, 0,
            100, 100, 0,
            0, 100, 0,
            100, 0, 100,
            100, 100, 100
        };
        float[] texCoords = {
            0, 0,
            0.5f, 0,
            0.5f, 0.5f,
            0, 0.5f,
            1, 0,
            1, 0.5f
        };
        // 三角网格组合（正逆序）
        int[] faces = {
            // 正序
            0, 0, 1, 1, 3, 3,
            // 逆序
            0, 0, 3, 3, 1, 1,
            1, 1, 2, 2, 3, 3,
            1, 1, 3, 3, 2, 2,
            1, 1, 5, 5, 4, 4,
            1, 1, 4, 4, 5, 5,
            2, 2, 5, 5, 1, 1,
            2, 2, 1, 1, 5, 5

        };

        TriangleMesh tm = new TriangleMesh();
        tm.getPoints().addAll(points);
        tm.getTexCoords().addAll(texCoords);
        tm.getFaces().addAll(faces);

        MeshView mv = new MeshView(tm);
        mv.setRotationAxis(new Point3D(1, 0, 0));
        mv.setRotate(30);

        Cylinder c1 = new Cylinder(50, 150, 64);
        hBox.getChildren().add(mv);
        secondaryRoot.getChildren().addAll(hBox);

    }

}
