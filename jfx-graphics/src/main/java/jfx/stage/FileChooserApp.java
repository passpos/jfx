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
package jfx.stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class FileChooserApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Stage - FileChooser";
    private VBox vBox;
    private TextArea ta;

    @Override
    public void index() {
        vBox = new VBox(10);
        ta = new TextArea();
        ta.setWrapText(true);
        setLeftAnchor(ta, 100.0);

        Button btn = new Button("简单文件窗口");
        baseDemo(btn);

        Button btn1 = new Button("单选文件窗口");
        singleFileChooserDemo(btn1);

        Button btn2 = new Button("多选文件窗口");
        multipleFileChooserDemo(btn2);

        Button btn3 = new Button("打开文本窗口");
        openTextFileDemo(btn3);

        Button btn4 = new Button("保存文本窗口");
        saveTextFileDemo(btn4);

//        Button btn5 = new Button("文件夹选择窗口");
        vBox.getChildren().addAll(btn, btn1, btn2, btn3, btn4);
        this.getChildren().addAll(vBox, ta);
    }

    public void baseDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();
                FileChooser fc = new FileChooser();
                fc.showOpenDialog(stage);

            }
        });
    }

    public void singleFileChooserDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();
                stage.setTitle("单选文件窗口"); // 这里无用，默认为“打开”；

                FileChooser fc = new FileChooser();
                // 这里的标题才会生效；
                fc.setTitle("单选文件窗口");

                // 窗口打开时会处于一个路径中，Win下默认为资源管理器磁盘根目录；
                // fc.setInitialDirectory(new File("D:" + File.separator + "Application"));
                fc.setInitialDirectory(new File("D://"));

                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("图片类型", "*.jpg", "*.png"),
                        new FileChooser.ExtensionFilter("视频类型", "*.mkv", "*.mp4"),
                        new FileChooser.ExtensionFilter("所有文件", "*.*"));

                // 展示打开窗口，返回文件的路径；
                File f = fc.showOpenDialog(stage);

                // 打开窗口后，如果不做选择，返回一个空；
                if (f == null) {
                    return;
                }
                ob(f, 300);
                ob(f.getAbsolutePath(), 300);

            }
        });
    }

    public void multipleFileChooserDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();

                FileChooser fc = new FileChooser();
                fc.setTitle("多选文件窗口");

                fc.setInitialDirectory(new File("D://"));

                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("图片类型", "*.jpg", "*.png"),
                        new FileChooser.ExtensionFilter("视频类型", "*.mkv", "*.mp4"),
                        new FileChooser.ExtensionFilter("所有文件", "*.*"));

                // 展示打开窗口，返回文件的路径；
                List<File> fl = fc.showOpenMultipleDialog(stage);

                // 打开窗口后，如果不做选择，返回一个空；
                if (fl.isEmpty()) {
                    return;
                }
                fl.forEach((f) -> ol(f.getAbsolutePath()));
            }
        });
    }

    /**
     * JDK会自动检测平台字符集，并以此种编码方式打开文本文件。如果另外设置了字符
     * 集，就会使用该字符集的编码方式；
     *
     * @param btn
     */
    public void openTextFileDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();

                FileChooser fc = new FileChooser();
                fc.setTitle("打开文本");

                fc.setInitialDirectory(new File("D://"));

                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("文本类型", "*.txt"),
                        new FileChooser.ExtensionFilter("网页类型", "*.html"),
                        new FileChooser.ExtensionFilter("所有文件", "*.*"));

                File f = fc.showOpenDialog(stage);

                if (f == null) {
                    return;
                }

                try {
                    FileInputStream fis = new FileInputStream(f);
                    InputStreamReader isr = new InputStreamReader(fis, "GBK");
                    BufferedReader br = new BufferedReader(isr);

                    // FileReader fr = new FileReader(f);
                    // BufferedReader br = new BufferedReader(fr);
                    String str;
                    while ((str = br.readLine()) != null) {
                        ta.appendText(str);
                    }
                    isr.close();
                    br.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void saveTextFileDemo(Button btn) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Stage stage = new Stage();

                FileChooser fc = new FileChooser();
                fc.setTitle("另存文本文件");

                // 设置文件名（供默认使用）；
                fc.setInitialFileName("保存的文件");
                fc.setInitialDirectory(new File("D://"));

                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("文本类型", "*.txt"),
                        new FileChooser.ExtensionFilter("网页类型", "*.html"),
                        new FileChooser.ExtensionFilter("所有文件", "*.*"));

                File f = fc.showSaveDialog(stage);

                if (f == null) {
                    return;
                }

                try {
                    // f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(ta.getText());
                    osw.close();    // 关闭后，才能完成文件内容的输出；
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileChooserApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
