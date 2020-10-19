package jfx.scene.paint;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import jfx.core.app.ContentBox;

/**
 * 线性渐变填充
 * 使用线性颜色渐变，从一种颜色渐变到另一种颜色。用户可以指定两个或多个渐变色，
 * 此绘制将在每个颜色之间提供插值。
 *                                     @
 * param @author realpai <paiap@outlook.com>
 */
public class LinearGradientApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Paint - LinearGradient";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle r = new Rectangle(150, 150);
                r.setFill(Paint.valueOf("#EDEDED"));
                gp.add(r, j, i);
            }
        }

        ArrayList<Paint> list = new ArrayList<>();
        Stop[] stops = new Stop[]{
            new Stop(0, Color.RED),
            new Stop(1, Color.AQUA)
        };
        LinearGradient lg1 = getLinearGradientByArray(0, 0, 150, 150, false, CycleMethod.NO_CYCLE, stops);
        list.add(lg1);

        Stop[] stops1 = new Stop[]{
            new Stop(0, Color.RED),
            new Stop(0.5, Color.AQUA),
            new Stop(1, Color.valueOf("#3238b0"))
        };
        LinearGradient lg2 = getLinearGradientByArray(0, 0, 150, 150, false, CycleMethod.NO_CYCLE, stops1);
        list.add(lg2);

        LinearGradient lg3 = getLinearGradientByArray(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        list.add(lg3);

        LinearGradient lg4 = getLinearGradientByArray(0, 0, 150, 150, true, CycleMethod.NO_CYCLE, stops);
        list.add(lg4);

        for (int i = 0; i < list.size(); i++) {
            Rectangle r = (Rectangle) gp.getChildren().get(i);
            r.setFill(list.get(i));
        }

        getChildren().add(gp);
    }

    /**
     * @param startX       - 梯度轴起点的X坐标；
     * @param startY       - 梯度轴起点的Y坐标；
     * @param endX         - 梯度轴终点的X坐标；
     * @param endY         - 梯度轴终点的X坐标；
     * @param proportional - 坐标是否与此渐变填充的形状成比例；
     *                     为true时，上面四个坐标为0-1之间的小数；
     * @param cycleMethod  - 应用于梯度的循环法；
     * @param stops        - 渐变的颜色规范（允许向可变参数传入数组类型）；
     * @return
     */
    private LinearGradient getLinearGradientByArray(double startX, double startY, double endX, double endY, boolean proportional, CycleMethod cycleMethod, Stop... stops) {
        LinearGradient lg = new LinearGradient(startX, startY, endX, endY, proportional, cycleMethod, stops);
        return lg;
    }

    /**
     * @param startX       - 梯度轴起点的X坐标；
     * @param startY       - 梯度轴起点的Y坐标；
     * @param endX         - 梯度轴终点的X坐标；
     * @param endY         - 梯度轴终点的X坐标；
     * @param proportional - 坐标是否与此渐变填充的形状成比例；
     * @param cycleMethod  - 应用于梯度的循环法；
     * @param stopList     - 渐变的颜色规范；
     * @return
     */
    private LinearGradient getLinearGradient(double startX, double startY, double endX, double endY, boolean proportional, CycleMethod cycleMethod, List<Stop> stopList) {
        LinearGradient lg = new LinearGradient(startX, startY, endX, endY, proportional, cycleMethod, stopList);
        return lg;
    }
}
