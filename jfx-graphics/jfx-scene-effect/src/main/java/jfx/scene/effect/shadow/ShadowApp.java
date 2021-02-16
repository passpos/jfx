
package jfx.scene.effect.shadow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class ShadowApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Effect - Shadow";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        setTopAnchor(hBox, 50.0);
        setLeftAnchor(hBox, 50.0);

        Shadow s1 = new Shadow();
        Shadow s2 = new Shadow();
        Shadow s3 = new Shadow();
        Shadow s4 = new Shadow();

        Button btn = new Button("按钮");
        btn.setEffect(s1);

        Text text = new Text("这是一行文本");
        s2.setColor(Color.CORAL);
        s2.setRadius(3);
        text.setFont(new Font(20));
        text.setEffect(s2);

        Rectangle r = new Rectangle(100, 100, Paint.valueOf("#8470FF"));
        r.setEffect(s3);

        Circle circle = new Circle(50, Paint.valueOf("#FF69B4"));
        circle.setEffect(s4);

        hBox.getChildren().addAll(btn, text, r, circle);
        getChildren().add(hBox);
    }
}
