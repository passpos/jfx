/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.demo.tray;

import fx.image.viewer.PictureViewer;
import fx.image.viewer.PreViewer;
import fx.image.viewer.ToolBar;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

    private Stage stage;
    private VBox root;
    private HBox toolBar;
    private PreViewer viewer;
    private PictureViewer pictureViewer;

    public App(Stage stage) {
        this.stage = stage;
        setRoot();
        setToolBar();
        setChooser();
        setMinimumAction();
    }

    private void setMinimumAction() {
        Button b = new Button("最小化到托盘");
        toolBar.getChildren().add(b);

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    setTray();
                } catch (AWTException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void setTray() throws AWTException {
        Platform.setImplicitExit(false);
        stage.hide();

        SystemTray st = SystemTray.getSystemTray();

        // ImageMaker.getImageFromResource("icon/fav.jpg");
        Image img = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon/fav.jpg");
        String str = "JavaFX系统托盘";

        // 托盘右键菜单
        PopupMenu pm = new PopupMenu();
        MenuItem mi1 = new MenuItem("还原");
        MenuItem mi2 = new MenuItem("退出");
        pm.add(mi1);
        pm.add(mi2);

        TrayIcon ti = new TrayIcon(img, str, pm);
        st.add(ti);

        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 这里的线程不是JavaFX Application Thread，所以不能直接执行stage.show()；
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.show();
                    }
                });
                st.remove(ti);
            }
        });
        mi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.setImplicitExit(true);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
                st.remove(ti);
            }
        });
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
