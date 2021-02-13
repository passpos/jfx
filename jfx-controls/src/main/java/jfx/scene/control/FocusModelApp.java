/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class FocusModelApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Model - FocusModel";

    @Override
    public void index() {

    }

    public void demo() {
        ListView<String> lv = new ListView<>();
        ListCell<String> lc = new ListCell<>();

        int position = 0;

        if (lc.getIndex() >= lv.getItems().size()) {
            position = lv.getItems().size() - 1;
        } else {
            position = lc.getIndex();
        }

        /* focus(position)中，position 指的是：
         * listCell.getIndex()
         * treeCell.getIndex()
         *
         * 如此获得的索引，指的是该 Cell 在 View 下，所有Cell中的位置；
         *
         * 令 item = treeCell.getTreeItem()，position不是：
         * item.getParent().getChildren().indexOf(item);
         *
         * 如此获得的索引，是与此 Cell 在同一目录下，且在同一层次上的部分Cell中
         * 的位置；
         */
        lv.getFocusModel().focus(position);

        getChildren().add(lv);
    }
}
