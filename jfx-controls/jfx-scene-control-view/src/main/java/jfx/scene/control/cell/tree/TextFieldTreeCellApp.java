/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfx.scene.control.cell.tree;

import jfx.core.app.ContentBox;

/**
 * index，该属性在TreeView各项的展开状态不同时，所有项的索引不同，但总是自上而下
 * ，从0开始，null项为-1；
 *
 * updateItem(T item, boolean empty)，开发人员不应调用该方法，但它是开发人员重写
 * 以允许他们自定义单元格外观的最佳方法。
 * - item 数据，而非TreeItem
 * - empty 此单元格是否代表列表中的数据。 如果为空，则它不表示任何域数据，而是一
 * 个用于呈现“空”行的单元格。
 *
 * 在TreeView中，有时会使用“缓加载”技术，Tree中较深层次的子项。仅在需要时才会
 * 被加载并渲染。此时，以加载的项目就会应用该方法，而缓加载的项，在加载时才会应
 * 用该方法；
 * @author passpos <paiap@outlook.com>
 */
public class TextFieldTreeCellApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Cell - TextFieldTreeCell";

    @Override
    public void index() {
    }
}
