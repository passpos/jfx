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
package jfx.scene.control.view.treetable;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.entity.FxStudent;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TreeTableViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "View - TreeTableView";
    private TreeTableView<FxStudent> ttv;
    private TreeTableColumn<FxStudent, String> nc;
    private TreeTableColumn<FxStudent, Number> ac;
    private TreeTableColumn<FxStudent, Number> sc;
    private TreeTableColumn<FxStudent, Boolean> gc;

    @Override
    public void index() {
        baseDemo();
        simpleLoadDataDemo();
//        builtinTreeTableCellDemo();
        customTreeTableCellDemo();
    }

    @SuppressWarnings("unchecked")
    public void baseDemo() {
        FxStudent s1 = new FxStudent("A", 16, 88, true);
        FxStudent s2 = new FxStudent("B", 46, 87, false);
        FxStudent s3 = new FxStudent("C", 32, 93, false);
        FxStudent s4 = new FxStudent("D", 36, 96, false);
        FxStudent s5 = new FxStudent("E", 26, 97, false);

        TreeItem<FxStudent> tiRoot = new TreeItem<>(s1);
        TreeItem<FxStudent> ti1 = new TreeItem<>(s2);
        TreeItem<FxStudent> ti2 = new TreeItem<>(s3);
        TreeItem<FxStudent> ti3 = new TreeItem<>(s4);
        TreeItem<FxStudent> ti4 = new TreeItem<>(s5);
        tiRoot.getChildren().addAll(ti1, ti2, ti3, ti4);
        tiRoot.setExpanded(true);

        ttv = new TreeTableView<>();
        getChildren().add(ttv);
        setTopAnchor(ttv, 30.0);
        ttv.setRoot(tiRoot);

        nc = new TreeTableColumn<>("姓名");
        ac = new TreeTableColumn<>("年龄");
        sc = new TreeTableColumn<>("分数");
        gc = new TreeTableColumn<>("性别");
        ttv.getColumns().addAll(nc, ac, sc, gc);
    }

    public void simpleLoadDataDemo() {
        nc.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        nc.setPrefWidth(70.0);
        ac.setCellValueFactory(new TreeItemPropertyValueFactory<>("age"));
        sc.setCellValueFactory(new TreeItemPropertyValueFactory<>("score"));
        gc.setCellValueFactory(new TreeItemPropertyValueFactory<>("gender"));
    }

    public void builtinTreeTableCellDemo() {
        ttv.setEditable(true);
        nc.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        nc.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn("F", "G"));
        nc.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn("F", "G"));
        ac.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number t) {
                return t.toString();
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        }));
        gc.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(new TreeTableColumn<>()));
        // sc.setCellFactory(ProgressBarTreeTableCell.forTreeTableColumn());
    }

    public void customTreeTableCellDemo() {
        nc.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FxStudent, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FxStudent, String> param) {
                return param.getValue().getValue().getNameProperty();
            }
        });
        nc.setCellFactory(new Callback<TreeTableColumn<FxStudent, String>, TreeTableCell<FxStudent, String>>() {
            @Override
            public TreeTableCell<FxStudent, String> call(TreeTableColumn<FxStudent, String> param) {
                TreeTableCell<FxStudent, String> ttc;
                ttc = new TreeTableCell<FxStudent, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            this.setGraphic(hBox);
                        } else if (empty) {
                            this.setGraphic(null);
                        }
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();
                    }

                    @Override
                    public void commitEdit(String newValue) {
                        super.commitEdit(newValue); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void startEdit() {
                        super.startEdit();
                        HBox hBox = new HBox();
                        TextField tf = new TextField(this.getTreeTableRow().getItem().getName());
                        hBox.getChildren().add(tf);
                        this.setGraphic(hBox);
                    }

                };
                return ttc;
            }
        });
    }
}
