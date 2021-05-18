/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.view.list;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class ListViewDemo1 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "ListView - Demo1";
    private ListView<String> lv;
    private ObservableList<String> oList;

    @Override
    public void index() {
    }
}
