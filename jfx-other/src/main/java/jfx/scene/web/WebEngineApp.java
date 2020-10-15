package jfx.scene.web;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jfx.utils.app.ContentBox;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLDocument;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class WebEngineApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - WebEngine";
    private WebView wv;
    private WebEngine engine;

    @Override
    public void index() {
        initEngine();
        baseDemo();
        DocumentDemo();
    }

    public void initEngine() {
        wv = new WebView();
        getChildren().add(wv);

        wv.prefWidthProperty().bind(this.widthProperty());
        wv.prefHeightProperty().bind(this.heightProperty());

        // 开启右键菜单
        wv.setContextMenuEnabled(true);

        engine = wv.getEngine();
        // engine.load("https://www.baidu.com");
    }

    public void baseDemo() {
        String ua = engine.getUserAgent();

        // 网页网址
        String location = engine.getLocation();
        String title = engine.getTitle();

        // engine.loadContent("<p>这是一个段落</p>");
        engine.loadContent(ua + "<br/>"
                + location + "<br/>"
                + title + "<br/>"
                + "<p>这是一个段落</p>", "text/html");

    }

    public void DocumentDemo() {
        Document doc = engine.getDocument();
        ol("Document - " + doc);

        JSObject window = (JSObject) engine.executeScript("window");
        ol("JSObject/Window - " + window);

        HTMLDocument doc2 = (HTMLDocument) engine.executeScript("document");
        ol("HTMLDocument - " + doc2);

        Element pe = doc2.createElement("p");
        pe.setTextContent("这是一个段落，通过createElement()方法创建。");

    }
}
