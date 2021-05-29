/*
 * Copyright (C) 2021 passpos <paiap@outlook.com>
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
package jfx.scene.web;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jfx.core.app.ContentBox;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLDocument;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class WebViewDemo6 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - DOM";
    private WebView wv;
    private WebEngine engine;

    @Override
    public void index() {
        initEngine();
        baseDemo();
        DocumentDemo();
    }

    public static <T> void ol(T arg) {
        System.out.println(arg);
    }

    public void initEngine() {
        wv = new WebView();
        getChildren().add(wv);

        wv.prefWidthProperty().bind(this.widthProperty());
        wv.prefHeightProperty().bind(this.heightProperty());
        wv.setContextMenuEnabled(true);

        engine = wv.getEngine();
    }

    public void baseDemo() {
        String ua = engine.getUserAgent();
        // 网页网址
        String location = engine.getLocation();
        String title = engine.getTitle();
        engine.loadContent(ua + "<br/>"
                + location + "<br/>"
                + title + "<br/>"
                + "<p>这是一个段落</p>", "text/html");

    }

    public void DocumentDemo() {
        Document doc = engine.getDocument();
        ol(doc);

        JSObject window = (JSObject) engine.executeScript("window");
        ol(window);

        HTMLDocument doc2 = (HTMLDocument) engine.executeScript("document");
        ol(doc2);
        Element pe = doc2.createElement("p");
        pe.setTextContent("这是一个段落，通过createElement()方法创建。");
        engine.reload();
    }

}
