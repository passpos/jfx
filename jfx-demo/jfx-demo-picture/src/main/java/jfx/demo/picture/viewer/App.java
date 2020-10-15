/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.demo.picture.viewer;

import fx.image.viewer.PictureViewer;
import fx.image.viewer.PreViewer;
import fx.image.viewer.ToolBar;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class App {

    private VBox root;
    private HBox toolBar;
    private PreViewer viewer;
    private PictureViewer pictureViewer;

    public App() {
        setRoot();
        setToolBar();
        setChooser();
    }

    public VBox getRoot() {
        return root;
    }

    private void setRoot() {
        root = new VBox(10);
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("#404049"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setChooser() {
        Button btn = new Button("浏览");
        toolBar.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();

                DirectoryChooser dc = new DirectoryChooser();
                dc.setTitle("选择文件夹");
                dc.setInitialDirectory(new File("D:" + File.separator));

                File f = dc.showDialog(stage);
                String path;
                if (f != null) {
                    path = f.getAbsolutePath();
                } else {
                    return;
                }
                initViewer(path);
            }
        });
    }

    private void initViewer(String path) {
        pictureViewer = new PictureViewer(path);
        pictureViewer.build();
        ToolBar tool = pictureViewer.getToolBar();
        toolBar.getChildren().addAll(tool.getPreviousButton(), tool.getNextButton());

        setViewer();
        VBox v = new VBox(viewer);
        v.setAlignment(Pos.CENTER);
        root.getChildren().add(v);
    }

    private void setToolBar() {
        toolBar = new HBox(10);
        toolBar.setSpacing(15);
        root.getChildren().add(toolBar);
    }

    private void setViewer() {
        viewer = pictureViewer.getViewer();
        viewer.fitWidthProperty().bind(root.widthProperty());
        viewer.fitHeightProperty().bind(root.heightProperty());
    }
}
