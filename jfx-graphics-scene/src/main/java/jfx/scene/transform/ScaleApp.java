/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.transform;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.transform.Scale;
import jfx.utils.app.ContentBox;

/**
 * 缩放转换
 *
 * Scale()
 * Scale​(double x, double y)
 * - 创建一个二维尺度；
 *
 * Scale​(double x, double y, double z)
 * - 创建一个三维尺度；
 *
 * Scale​(double x, double y, double pivotX, double pivotY)
 * - 用pivot（二维中心点）创建一个二维尺度。
 * - 参数1、2： X、Y坐标上的缩放倍数；
 * - 参数3、4： 缩放时，基于某个中心点；
 *
 * Scale​(double x, double y, double z, double pivotX, double pivotY, double pivotZ)
 * - 用pivot（三维中心点）创建一个三维尺度。
 * @author realpai <paiap@outlook.com>
 */
public class ScaleApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Transform - Scale 缩放";

    @Override
    public void index() {
        baseDemo();
        reflectionDemo();
    }

    public void baseDemo() {
        Scale s1 = new Scale(0.5, 0.5, 0, 0);
        Scale s2 = new Scale(2, 2, 0, 0);

        Button btn1 = new Button("按钮1");
        btn1.getTransforms().add(s1);

        Button btn2 = new Button("按钮2");
        setLeftAnchor(btn2, 50.0);
        getChildren().addAll(btn1, btn2);

        ol(btn2.getPrefWidth());
        btn2.getTransforms().add(s2);

        // 转换不影响预设的尺寸；
        ol(btn2.getPrefWidth());

        // 获取显示时的实际尺寸；
        Bounds lb = btn2.getLayoutBounds();
        Bounds ltp = btn2.localToParent(lb);
        ol("转换后的宽度：" + (ltp.getMaxX() - ltp.getMinX()));
        ol("转换后的高度：" + (ltp.getMaxY() - ltp.getMinY()));

    }

    public void reflectionDemo() {
        Button btn3 = new Button("按钮3");
        setTopAnchor(btn3, 150.0);
        setLeftAnchor(btn3, 50.0);

        Button btn4 = new Button("按钮4");
        setTopAnchor(btn4, 150.0);
        setLeftAnchor(btn4, 100.0);

        Button btn5 = new Button("按钮5");
        setTopAnchor(btn5, 150.0);
        setLeftAnchor(btn5, 200.0);

        Scale s1 = new Scale(-1, 1, 0, 0);
        Scale s2 = new Scale(1, -1, 0, 0);
        Scale s3 = new Scale(-1, -1, 0, 0);
        btn3.getTransforms().add(s1);
        btn4.getTransforms().add(s2);
        btn5.getTransforms().add(s3);

        getChildren().addAll(btn3, btn4, btn5);
    }
}
