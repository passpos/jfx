/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.beans.property;

import java.util.Comparator;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import jfx.core.app.ContentBox;
import static jfx.core.app.ContentBox.ol;

/**
 * B-53
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListPropertyDemo extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Property - ListProperty ListChange";
    private ObservableList<String> list;
    private SimpleListProperty<String> slp;

    @Override
    public void index() {
        base();

        modifyList();
    }

    public void base() {
        setConsole();

        // 必须将 list 传入ListProperty，否则报错；
        list = FXCollections.observableArrayList();
        slp = new SimpleListProperty<>(list);
        slp.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                change(c);
            }
        });
    }

    /**
     * ListChangeListener.Change标记list的改变；
     * 改变可能有多个，next()方法检查是否还有改变；
     *
     * @param c
     */
    private void change(ListChangeListener.Change<? extends String> c) {
        ol("\nChange：" + c);

        // 获得更改后的元素列表
        o("当前列表：");
        c.getList().forEach((str) -> o(str));

        /* 重置到初始状态。在调用此方法之后，必须在处理第一个更改之前调用next()。
         * 一般不必调用此方法；
         */
        // c.reset();

        // 获得更改的元素（注意：必须调用 next()，否则出错）
        if (c.next()) {
            ol("\n起始位置：" + c.getFrom() + "\t结束位置：" + c.getTo());

            if (c.wasAdded()) {
                ol("wasAdded()：" + c.wasAdded());
                ol("getAddedSize()：" + c.getAddedSize());
                ol("getAddedSubList()：" + c.getAddedSubList());
            }

            if (c.wasRemoved()) {
                ol("wasRemoved()：" + c.wasRemoved());
                ol("getRemovedSize()：" + c.getRemovedSize());
                ol("getRemoved()：" + c.getRemoved());
            }

            // 对应set()操作
            if (c.wasReplaced()) {
                ol("wasReplaced()：" + c.wasReplaced());
                ol("getAddedSubList()：" + c.getAddedSubList());
                ol("getRemoved()：" + c.getRemoved());
            }

            // 排序变化
            if (c.wasPermutated()) {
                ol("wasPermutated()：" + c.wasPermutated());
                ol("getPermutation(0)：" + c.getPermutation(0));
            }

            // 更新操作参照：B-85 ListView 5种数据的加载与界面更新；
            if (c.wasUpdated()) {
                ol("wasUpdated()：" + c.wasUpdated());
            }
        }
    }

    /**
     * 修改List
     */
    public void modifyList() {
        // 增
        slp.addAll("A", "B");
        slp.add("C");

        // 删
        slp.remove("B");

        // 改 - replace
        ol("\nset：");
        slp.set(0, "D");

        // 使用 list 才能激活 wasPermutated()
        ol("\nsortByList：");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        // 使用 slp 排序过程被拆分，不能激活 wasPermutated()
        ol("\nsortByProperty：");
        slp.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

    }
}
