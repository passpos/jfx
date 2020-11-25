/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.view.tree;

import jfx.core.app.ContentBox;

/**
 * 该模型允许注册侦听器，当项数改变，它们的位置或值本身改变时，将通知他们。
 * 但是请注意，TreeItem不是节点，因此不会在TreeItem上触发任何视觉事件。
 * 为了获得这些事件，有必要将相关的观察者添加到TreeCell实例中
 * （通过自定义单元工厂-有关更多详细信息，请参见Cell类文档）。
 * @author passpos <paiap@outlook.com>
 */
public class TreeItemApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Tree - TreeItem";

    @Override
    public void index() {
    }

}
