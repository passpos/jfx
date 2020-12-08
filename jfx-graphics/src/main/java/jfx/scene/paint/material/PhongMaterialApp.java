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
package jfx.scene.paint.material;

import fx.image.ImageMaker;
import java.util.function.Consumer;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class PhongMaterialApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Paint - PhongMaterial";

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

        HBox hBox = new HBox();
        setTopAnchor(hBox, 100.0);
        setLeftAnchor(hBox, 50.0);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(box, cyl, sphere);
        hBox.getChildren().forEach(new Consumer<Node>() {
            @Override
            public void accept(Node t) {
                Shape3D s = (Shape3D) t;
                PhongMaterial pmtrl = new PhongMaterial();
                Image img = ImageMaker.getImageFromResource("imgs/1.jpg");

                // 漫反射颜色
                // pmtrl.setDiffuseColor(Color.CADETBLUE);
                // 自发光贴图
                pmtrl.setSelfIlluminationMap(img);

                // 高光颜色
                pmtrl.setSpecularColor(Color.WHITE);

                // 高光图片
                // pmtrl.setSpecularMap(image);
                // 漫反射贴图
                pmtrl.setDiffuseMap(img);
                // 法线贴图
                pmtrl.setBumpMap(img);
                s.setMaterial(pmtrl);
            }
        });
        getChildren().add(hBox);
    }

}
