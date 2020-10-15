/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;
import jfx.utils.app.ContentBox;

/**
 * JEditorPane得到的文本传递到TextArea中后乱码
 * @author realpai <paiap@outlook.com>
 */
public class Demo2JEditorPaneApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - Demo JEditorPane";
    private SwingNode swingNode;
    private JEditorPane jep;
    private Button btn;

    @Override
    public void index() {
        try {
            baseDemo();
        } catch (IOException ex) {
            Logger.getLogger(Demo2JEditorPaneApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baseDemo() throws IOException {
        swingNode = new SwingNode();

        setEditor();
        // setEditorDemo();
        swingNode.setContent(jep);
        swingNode.resize​(500, 200);
        swingNode.prefWidth(500);
        swingNode.prefHeight(200);

        // 查看HTML源码
        TextArea ta = new TextArea();
        ta.setPrefWidth(500);
        ta.setPrefHeight(300);

        // 转换（getText()会导致SwingNode样式重设）
        btn = new Button("转换");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String text = jep.getText();
                ta.setText(text);
            }
        });

        getChildren().addAll(btn, swingNode, ta);
        setTopAnchor(swingNode, 50.0);
        setTopAnchor(ta, 250.0);
    }

    /**
     * jep.setPage()方法会使jep不可编辑，即使设置了setEnabled(true)；
     * @throws IOException
     */
    public void setEditorDemo() throws IOException {
        jep = new JEditorPane();

        jep.setContentType("text/html;charset=utf-8");
        jep.setPage("https://www.baidu.com");
        jep.setEnabled(true);
        jep.setSize(400, 100);
    }

    public void setEditor() throws IOException {
        jep = new JEditorPane();
        // jep = new JEditorPane("text/html;charset=utf-8", "");

        HTMLEditorKit kit = new HTMLEditorKit();
        jep.setEditorKit(kit);
        jep.setContentType("text/html;charset=utf-8");
        jep.setText("<p>JEditorPane——这是一个段落。</p>");
        ol(jep.getText());
    }
}
