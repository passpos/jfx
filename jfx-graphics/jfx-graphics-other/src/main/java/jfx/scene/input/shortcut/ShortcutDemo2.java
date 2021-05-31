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
package jfx.scene.input.shortcut;

import jfx.core.app.ContentBox;

/**
 * 表示在键盘快捷键中使用的键的组合。组合键由一个主键和一组修改键组成。主键可以
 * 通过其键代码-KeyCodeCombination或键字符-KeyCharacterCombination指定。修改键是
 * shift、control、alt、meta或shortcut，可以定义为DOWN、UP或ANY。
 *
 * @author realpai <paiap@outlook.com>
 */
public class ShortcutDemo2 extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Shortcut - KeyCombination";

    @Override
    public void index() {
    }

}
