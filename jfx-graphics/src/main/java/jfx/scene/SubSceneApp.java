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

import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SubSceneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Scene - SubScene";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Box box = new Box(150, 150, 150);
        Cylinder cyl = new Cylinder(50, 150, 128);
        cyl.setRotate(30);
        cyl.setRotationAxis(new Point3D(1, 0, 0));
        Sphere sphere = new Sphere(50, 128);

        getBaseScene().setCamera(new PerspectiveCamera());

        /*
         * PointLight是节点类型，使用时添加到需要环境光的组件上即可；
         * 环境光十分均匀的施加到场景中对象的各个角落，不产生明暗对比
         */
        PointLight pl1 = new PointLight();
        pl1.setColor(Color.CORAL);
        pl1.setLightOn(true);
        // pl.getScope().add(cyl);
        pl1.setTranslateX(-400);
        pl1.setTranslateY(200);
        pl1.setTranslateZ(-50);

        PointLight pl2 = new PointLight();
        pl2.setColor(Color.GREEN);
        pl2.setLightOn(true);
        pl2.setTranslateY(200);
        pl2.setTranslateZ(-50);

        HBox hBox = new HBox();
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 100.0);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(box, cyl, sphere, pl1, pl2);

        SubScene subScene = new SubScene(hBox, 400, 300, true, SceneAntialiasing.BALANCED);
        getChildren().add(subScene);
        subScene.widthProperty().bind(this.prefWidthProperty());
    }

}
