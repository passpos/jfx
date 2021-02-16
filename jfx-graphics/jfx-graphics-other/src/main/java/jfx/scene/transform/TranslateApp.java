/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.transform;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.transform.Translate;
import jfx.core.app.ContentBox;

/**
 * 平移转换
 *
 * 表示相对原位置，在X、Y、Z轴方向上的位移；
 * @author realpai <paiap@outlook.com>
 */
public class TranslateApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Transform - Translate 平移";

    @Override
    public void index() {
        baseDemo();
        boundsDemo();
        point2DDemo();
    }

    /**
     * 节点最终位置：setLayout()设置的数值与平移量相加（而不是重新设置坐标为
     * Layout数值）；
     * 但是，getLayout()的数值，仍然是最后通过setLayout()设置的数值，而不是加上
     * 平移量。
     */
    public void baseDemo() {
        Button btn1 = new Button("按钮1");
        Button btn2 = new Button("按钮2");

        Translate ts = new Translate(100, 100);
        btn2.getTransforms().add(ts);
        btn2.setLayoutX(50.0);

        // 获取节点相对于父组件的位置
        ol(btn2.getLocalToParentTransform().getTx());
        ol(btn2.getLocalToSceneTransform().getTx());

        getChildren().addAll(btn1, btn2);
    }

    public void boundsDemo() {
        Button btn = new Button("按钮3");
        setTopAnchor(btn, 100.0);

        Bounds lb = btn.getLayoutBounds();

        Bounds ltp = btn.localToParent(lb);
        ol(ltp.getMinX() + " - " + ltp.getMinY());

        // 下面似乎获取不到 lts
        // Bounds lts = btn.localToScreen(lb);
        // ol(lts.getMinX() + " - " + lts.getMinY());

        getChildren().add(btn);
    }

    public void point2DDemo() {
        Button btn = new Button("按钮3");
        setTopAnchor(btn, 200.0);

        Translate tl = new Translate(70, 70);
        Point2D tf = tl.transform(50, 50);
        ol(tf.getX());

        Point2D dtf = tl.deltaTransform(30, 30);
        ol(dtf.getX());

        getChildren().add(btn);
    }

}
