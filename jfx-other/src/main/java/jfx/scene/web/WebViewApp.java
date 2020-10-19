/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.web;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jfx.core.app.ContentBox;
import org.w3c.dom.Document;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WebViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - WebView";
    private WebView wv;
    private WebEngine engine;

    @Override
    public void index() {
        baseDemo();
        webEngineDemo();
    }

    public void baseDemo() {
        wv = new WebView();
        getChildren().add(wv);

        wv.prefWidthProperty().bind(this.widthProperty());
        wv.prefHeightProperty().bind(this.heightProperty());

        // 设置字体
        // wv.setFontScale(0.5);
        // 设置缩放
        // wv.setZoom(0.5);
        // 开启右键菜单
        wv.setContextMenuEnabled(true);
    }

    public void webEngineDemo() {
        engine = wv.getEngine();
        engine.load("http://192.168.1.2:10000/Java/JavaFX/javafx.web/javafx/scene/web/WebView.html");
        // engine.loadContent("<p>这是一个HTML段落</p>");
        Document document = engine.getDocument();
        String title = engine.getTitle();
        String ua = engine.getUserAgent();
        String ussl = engine.getUserStyleSheetLocation();
        boolean jsSupport = engine.isJavaScriptEnabled();

        // 重载某网址或字符文档
        engine.reload();

    }

}
