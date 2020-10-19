/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.scene.control;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.PopupWindow;
import javafx.stage.WindowEvent;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TooltipApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Control - Tooltip";
    private Tooltip tt;

    @Override
    public void index() {
        tt = new Tooltip("这是提示文本！");

        baseDemo();
        eventDemo();
        customLayoutDemo();
    }

    public void baseDemo() {

        Button btn = new Button("按钮");
//        btn.setTooltip(tt);

        this.getChildren().add(btn);

        tt.setFont(new Font(20));
        tt.setPrefWidth(100);
        tt.setPrefHeight(50);

        // 文本换行
        tt.setWrapText(true);

        // 裁切超出文本；
        tt.setTextOverrun(OverrunStyle.CLIP);

        // 超出文本，省略号在末尾；
        // tt.setTextOverrun(OverrunStyle.ELLIPSIS);
        // 超出文本，省略号在中间；
        // tt.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        // 超出文本，省略号在最前；
        // tt.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);
        // 省略尾部文本；
        // tt.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);
        // 省略中间文本；
        // tt.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS);
        // 省略首部文本；
        // tt.setTextOverrun(OverrunStyle.LEADING_WORD_ELLIPSIS);
        // 内容居中，仅对多行文本有效；
        tt.setTextAlignment(TextAlignment.CENTER);
        // 提示的弹出位置，默认为右下角
        tt.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_RIGHT);
        tt.setAnchorLocation(PopupWindow.AnchorLocation.CONTENT_TOP_RIGHT);
        // 不透明度
        tt.setOpacity(0.7);
        // 解除关联
        Tooltip.uninstall(btn, tt);
        Tooltip.install(btn, tt);

        // 设置提示的出现窗口；
        tt.show(getPrimaryStage());
        tt.setAutoHide(false);
        // tt.setX(100);
    }

    public void eventDemo() {
        tt.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                ol("正在显示");
            }
        });
        tt.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                ol("显示");
            }
        });
        tt.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                ol("正在隐藏");
            }
        });
        tt.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                ol("隐藏");
            }
        });
    }

    public void customLayoutDemo() {
        VBox vBox = new VBox();
        vBox.setPrefWidth(100);
        vBox.setPrefHeight(50);
        vBox.setStyle("-fx-background-color:#556699");
        tt.setGraphic(vBox);
        tt.setStyle("-fx-background-color:#55669900");
    }
}
