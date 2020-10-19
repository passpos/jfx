/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class BarChartApp extends ContentBox {

    public final static boolean SHOWING = false;
    public final static String TITLE = "Chart - BarChart/直方图";
    private ObservableList<XYChart.Series<String, Number>> oal;

    @Override
    public void index() {
        oal = FXCollections.observableArrayList();

//        dataDemo1();
//        dataDemo2();
        dataDemo3();
        baseDemo();
    }

    public void dataDemo1() {
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("data1", 30);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("data2", 20);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("data3", 50);
        XYChart.Data<String, Number> d4 = new XYChart.Data<>("data4", 80);
        XYChart.Data<String, Number> d5 = new XYChart.Data<>("data5", 20);

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        data.add(d1);
        data.add(d2);
        data.add(d3);
        data.add(d4);
        data.add(d5);

        // 将一个数据组包装到一个系列中（允许存在多个系列，例如：今年、去年、前年三个系列）
        XYChart.Series<String, Number> xy = new XYChart.Series<>();
        xy.setData(data);

        // 将所有的系列添加到图表的数据源中
        oal.add(xy);
    }

    public void dataDemo2() {
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("data1", 40);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("data2", 10);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("data3", 30);
        XYChart.Data<String, Number> d4 = new XYChart.Data<>("data4", 60);
        XYChart.Data<String, Number> d5 = new XYChart.Data<>("data5", 40);

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        data.add(d1);
        data.add(d2);
        data.add(d3);
        data.add(d4);
        data.add(d5);

        // 将一个数据组包装到一个系列中（允许存在多个系列，例如：今年、去年、前年三个系列）
        XYChart.Series<String, Number> xy = new XYChart.Series<>();
        xy.setData(data);

        // 将所有的系列添加到图表的数据源中
        oal.add(xy);
    }

    /**
     * 将不同分组方式的数据添加到序列，可以得到不同效果的表格；
     */
    public void dataDemo3() {
        XYChart.Data<String, Number> d1 = new XYChart.Data<>("去年", 40);
        XYChart.Data<String, Number> d2 = new XYChart.Data<>("去年", 10);
        XYChart.Data<String, Number> d3 = new XYChart.Data<>("去年", 30);

        XYChart.Data<String, Number> d4 = new XYChart.Data<>("今年", 60);
        XYChart.Data<String, Number> d5 = new XYChart.Data<>("今年", 40);
        XYChart.Data<String, Number> d6 = new XYChart.Data<>("今年", 40);

        // 将一个数据组包装到一个系列中（允许存在多个系列，例如：今年、去年、前年三个系列）
        XYChart.Series<String, Number> xy1 = new XYChart.Series<>();
        xy1.setName("中国");
        xy1.getData().addAll(d1, d4);

        XYChart.Series<String, Number> xy2 = new XYChart.Series<>();
        xy2.setName("美国");
        xy2.getData().addAll(d2, d5);

        XYChart.Series<String, Number> xy3 = new XYChart.Series<>();
        xy3.setName("日本");
        xy3.getData().addAll(d3, d6);

        // 将所有的系列添加到图表的数据源中
        oal.addAll(xy1, xy2, xy3);
        // bc.getData().addAll(xy1, xy2, xy3);
    }

    /**
     * 纵横坐标可以调换位置，以获得转换的效果；
     */
    public void baseDemo() {
        // X轴
        CategoryAxis cax = new CategoryAxis();
        cax.setLabel("国家");

        // 水平方向上开头与结尾保留尺寸
        cax.setStartMargin(50);
        cax.setEndMargin(50);
        cax.setGapStartAndEnd(true);

        // X轴的位置
        cax.setSide(Side.BOTTOM);
        // X轴上的标签文本颜色
        cax.setTickLabelFill(Paint.valueOf("#ff55ff"));
        cax.setTickLabelFont(new Font(16));
        // 标签文本与X轴间的距离
        cax.setTickLabelGap(30);
        cax.setTickLabelRotation(50);
        // 刻度的长度
        cax.setTickLength(10);

        // Y轴（最大值、最小值、单位值）
        NumberAxis nay = new NumberAxis(0, 100, 10);
        nay.setLabel("GDP");

        BarChart<String, Number> bc = new BarChart<>(cax, nay, oal);
        // 直方条间的间距
        bc.setBarGap(5);
        // 各组直方条间的间距
        bc.setCategoryGap(10);

        getChildren().add(bc);
    }
}
