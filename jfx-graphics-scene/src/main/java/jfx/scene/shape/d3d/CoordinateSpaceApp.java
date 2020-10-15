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
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfx.utils.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class CoordinateSpaceApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shape - 3D 坐标空间";
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
                // 只有设置为透明，在Z轴上的各层才能全部显示出来；
                secondaryRoot.setStyle("-fx-background-color:#44333300");
                secondaryScene = new Scene(secondaryRoot, 800, 600, true);

                baseDemo();

                secondaryStage.setScene(secondaryScene);
                secondaryStage.show();
            }
        });

        getChildren().add(b);
    }

    protected void baseDemo() {
        Button b1 = new Button("按钮1");
        b1.setTranslateZ(0);

        Button b2 = new Button("按钮2");
        b2.setTranslateZ(300);

        Button b3 = new Button("按钮3");
        b3.setTranslateZ(600);

        b1.setDepthTest(DepthTest.ENABLE);
        b2.setDepthTest(DepthTest.ENABLE);
        b3.setDepthTest(DepthTest.ENABLE);
        secondaryRoot.getChildren().addAll(b1, b2, b3);

        secondaryScene.setCamera(new PerspectiveCamera());

        ol(b1.getBoundsInParent());
        ol(b2.getBoundsInParent());
        ol(b3.getBoundsInParent());
    }
}
