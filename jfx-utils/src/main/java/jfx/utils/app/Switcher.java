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
package jfx.utils.app;

import fx.image.ImageMaker;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class Switcher {

    private Application launcher;
    private Stage primaryStage;
    private Switcher switcher;
    private Scene baseScene;
    private HBox root;
    private AbstractAppBox appBox;
    private AbstractSideBox sideBox;

    /**
     * 初始化成员
     * step-3
     * @param launcher
     * @param primaryStage
     * @param sideBox
     * @param appBox
     */
    public Switcher(Application launcher, Stage primaryStage, AbstractSideBox sideBox, AbstractAppBox appBox) {
        this.launcher = launcher;     // step-4
        this.primaryStage = primaryStage;

        this.sideBox = sideBox;
        this.appBox = appBox;

        this.root = new HBox();
        this.baseScene = new Scene(root);
        root.getChildren().addAll(sideBox, appBox);

        init();     // step-5
    }

    /**
     * 将成员数据传递给sideBox、appBox和ContentBox.
     */
    private void init() {
        ContentBox.init(this);     // step-6
        sideBox.setSwitcher(this);

        appBox.initAppList();              // step-7

        sideBox.init();                     // step-8

        setStageStyle();
        setRootStyle();
    }

    public Application getLauncher() {
        return launcher;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Switcher getSwitcher() {
        return switcher;
    }

    public Scene getBaseScene() {
        return baseScene;
    }

    public HBox getRoot() {
        return root;
    }

    public AbstractAppBox getAppBox() {
        return appBox;
    }

    public AbstractSideBox getSideBox() {
        return sideBox;
    }

    public void setStageStyle() {
        primaryStage.setWidth(950);
        primaryStage.setHeight(650);
        primaryStage.setResizable(true);

        Image resourceImage = ImageMaker.getImageFromResource("icon/fav.jpg");
        primaryStage.getIcons().add(resourceImage);
    }

    public void setRootStyle() {
        root.setStyle("-fx-background-color:#aaa8a4");

        addRootListener();
    }

    private void addRootListener() {
        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                appBox.setPrefWidth(t1.doubleValue() - sideBox.getWidth());
            }
        });
        root.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                appBox.setPrefHeight(t1.doubleValue());
                sideBox.setPrefHeight(t1.doubleValue());
            }
        });
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }
}
