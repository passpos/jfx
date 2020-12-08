/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx.scene.control.view.tree;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import jfx.core.app.ContentBox;

/**
 *
 * @author passpos <paiap@outlook.com>
 */
public class TreeCellDemo3 extends ContentBox {

    public static final boolean SHOWING = true;
    public static final String TITLE = "Tree - TreeCell Demo3 拖拽操作";
    private TreeView<String> tv;
    private TreeCell<String> temp = null;

    @Override
    public void index() {
        fillData();
        dragDemo();
    }

    public void fillData() {
        TreeUtils tu = new TreeUtils();
        tv = tu.getTreeView();

        getChildren().add(tv);
    }

    /**
     * 拖拽操作
     */
    public void dragDemo() {
        Callback<TreeView<String>, TreeCell<String>> callback = new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> param) {
                TreeCell<String> tc = setDragAction();
                return tc;
            }
        };
        tv.setCellFactory(callback);
    }

    private TreeCell<String> setDragAction() {
        TreeCell<String> tc = new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    HBox hBox = new HBox();
                    hBox.getChildren().add(new Label(item));
                    setGraphic(hBox);
                } else if (empty) {
                    setGraphic(null);
                }
            }
        };

        // 检测到拖拽动作时
        tc.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Dragboard db = tc.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                ClipboardContent cc = new ClipboardContent();
                cc.putString(tc.getItem());
                db.setContent(cc);

                // 将节点数据转换为文本；
                Text text = new Text(tc.getItem());
                text.setFont(new Font(16));

                // 对文本创建快照，并将快照写入到一张图片内；
                WritableImage wi = new WritableImage((int) tc.getWidth(), 20);
                text.snapshot(new SnapshotParameters(), wi);

                // 使其跟随鼠标的指针移动；
                db.setDragView(wi);
            }
        });

        // 当拖拽到这里时
        tc.setOnDragOver(new EventHandler<DragEvent>() {
            /**
             * 这里的DragEvent的事件源不是被拖节点，而是拖拽指针经过的节点；
             * 该节点我们将其设置到 temp 变量中；
             * @param t
             */
            @Override
            public void handle(DragEvent t) {
                t.acceptTransferModes(TransferMode.COPY_OR_MOVE);

                /* 拖拽经过到此，就会有一个位置指示，拖拽经过多个不同的TreeCell，
                 * 就会有多个指示，所以总是要把上一个（temp）指示清除掉；
                 * 并将当前的TreeCell设置到temp；
                 */
                if (temp != null) {
                    temp.setBorder(null);
                }
                temp = tc;

                /* 所有的拖拽动作都会有一个水平或垂直方向的位移；
                 * 这里判断这个位移是否达到指定位置；
                 *
                 * TreeView总是垂直的，所以拖拽总是检测垂直方向是否发生了偏移；
                 * 该偏移的符号是从上向下计算的，即：向下拖为正，向上拖则为负；
                 */
                double y = t.getY();
                double h = tc.getHeight();

                // 如果偏移大于0，且超过了被拖拽组件高度的一半，就执行
                if (y >= 0 && y <= h - 10) {

                }

                /* 当拖拽指针位于这个TreeCell的下边缘附近时，设置该TreeCell的下
                 * 边框为突出的样式；
                 */
                if (y > h - 10 && y <= h) {
                    BorderStroke bs = new BorderStroke(
                            null, null, Paint.valueOf("#71C671"), null,
                            BorderStrokeStyle.SOLID, null, null, null,
                            null,
                            new BorderWidths(0, 0, 2, 0),
                            null
                    );
                    Border border = new Border(bs);
                    tc.setBorder(border);
                }
            }
        });

        // 当在这里释放拖拽时
        tc.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                if (temp != null) {
                    temp.setBorder(null);
                }

                String value = t.getDragboard().getString();
                tc.getTreeItem().isLeaf();

                if (tc.getTreeItem().getParent() != null) {
                    int index = tc.getTreeItem().getParent().getChildren().indexOf(tc.getTreeItem());
                    tc.getTreeItem().getParent().getChildren().add(index + 1, new TreeItem<>(value));
                }
            }
        });
        return tc;
    }
}
