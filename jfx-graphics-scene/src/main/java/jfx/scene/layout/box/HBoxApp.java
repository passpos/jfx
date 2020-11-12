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

package jfx.scene.layout.box;

/**
 * 水平排列子组件的布局方式
 * 1. 当未设置子组件时，HBox的尺寸为0，而不是填充父组件；
 * 2. 若设置了子组件，HBox的尺寸就会被撑开；
 * 3. 当HBox的水平尺寸较小且不可调整（设置了maxSize），而子组件的总尺寸较大时 ：
 * - 若每个子组件的尺寸可调整（只设置了prefSize），垂直方向会压缩子组件，水平方
 * 向也被压缩；
 * - 若每个子组件的尺寸不可调整（设置了minSize），则子组件不会被挤压，HBox的水
 * 平尺寸会被强制撑开，但不会溢出窗体；
 * @author realpai <paiap@outlook.com>
 */
public class HBoxApp {

}
