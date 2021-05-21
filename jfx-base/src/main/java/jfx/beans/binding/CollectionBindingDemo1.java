/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.binding;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.ol;

/**
 * B-60
 *
 * @author passpos <paiap@outlook.com>
 */
public class CollectionBindingDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Binding - Collection 双向绑定";
    private SimpleListProperty<String> slp1;
    private SimpleListProperty<String> slp2;
    private ObservableList<String> ol1;
    private ObservableList<String> ol2;

    @Override
    public void index() {
        base();
        bindBidirectional();
    }

    public void base() {
        ol1 = FXCollections.observableArrayList();
        slp1 = new SimpleListProperty<>(ol1);
        slp1.add("A");
        slp1.add("B");

        ol2 = FXCollections.observableArrayList();
        slp2 = new SimpleListProperty<>(ol2);
        slp2.add("C");
        slp2.add("D");

        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
    }

    public void bindBidirectional() {
        slp1.bindBidirectional(slp2);

        ol("-----------------------双向绑定：");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());

        ol("分别向slp1和slp2追加元素：");
        slp1.add("E");
        slp2.add("F");
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp1排序操作：");
        slp1.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("对slp2排序操作：");
        slp2.sort(((item1, item2) -> item2.compareTo(item1)));
        ol("slp1 = " + slp1.getValue() + "\n" + "slp2 = " + slp2.getValue());
        ol("ol1 = " + ol1 + "\n" + "ol2 = " + ol2);

        ol("由此可见：");
        ol("单向绑定与双向绑定实质相同！");
    }

}
