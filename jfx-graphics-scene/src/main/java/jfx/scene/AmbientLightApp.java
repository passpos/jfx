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
import javafx.scene.AmbientLight;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class AmbientLightApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Scene - AmbientLight 环境光";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        Box box = new Box(150, 150, 150);
        Cylinder cyl = new Cylinder(50, 150, 128);
        cyl.setRotate(-30);
        cyl.setRotationAxis(new Point3D(1, 0, 0));
        Sphere sphere = new Sphere(50, 128);

        getBaseScene().setCamera(new PerspectiveCamera());

        /*
         * AmbientLight是节点类型，使用时添加到需要环境光的组件上即可；
         * 环境光十分均匀的施加到场景中对象的各个角落，不产生明暗对比
         */
        AmbientLight al = new AmbientLight();
        al.setColor(Color.CORAL);
        al.setLightOn(true);
        al.getScope().add(box);

        HBox hBox = new HBox();
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 50.0);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(box, cyl, sphere, al);
        getChildren().add(hBox);
    }

}
