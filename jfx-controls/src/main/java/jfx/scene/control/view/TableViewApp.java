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
package jfx.scene.control.view;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfx.core.app.ContentBox;
import jfx.core.entity.FxStudent;
import utils.entity.demo.sample.Student;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class TableViewApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "View - TableView";
    private TableView<Student> tbv;
    private ObservableList<Student> oal1;
    private ObservableList<FxStudent> oal2;
    private ObservableList<HashMap<String, SimpleStringProperty>> oal3;
    private TableColumn<Student, String> nameColumn;
    private TableColumn<Student, Number> ageColumn;
    private TableColumn<Student, Boolean> genderColumn;
    private TableColumn<Student, Number> scoreColumn;

    @Override
    public void index() {
        fillData();

        baseDemo();
//        selectDemo();
//        customColumnWidthDemo();
//        sortDemo();

//        propertyTypeDemo();
//        mergeColumnDemo();
//        mapColumnDemo();
//        editDemo();
//        builtinTableCellDemo();
//        customTableCellDemo();
        customTableRowDemo();
    }

    @SuppressWarnings("unchecked")
    public void fillData() {
        List<Student> oal = List.of(
                new Student("William", 25),
                new Student("James", 65),
                new Student("Julius", 13)
        );

        oal1 = FXCollections.observableArrayList();
        Student s1 = new Student("William", 25, 92, true);
        Student s2 = new Student("James", 65, 50, true);
        Student s3 = new Student("Julius", 13, 99, false);
        Student s4 = new Student("Maria", 16, 94.5, false);
        oal1.add(s1);
        oal1.add(s2);
        oal1.add(s3);
        oal1.add(s4);

        oal2 = FXCollections.observableArrayList();
        FxStudent fs1 = new FxStudent("William", 25, 92, true);
        FxStudent fs2 = new FxStudent("James", 65, 87, true);
        FxStudent fs3 = new FxStudent("Julius", 13, 99, false);
        FxStudent fs4 = new FxStudent("Maria", 16, 94.5, false);
        oal2.add(fs1);
        oal2.add(fs2);
        oal2.add(fs3);
        oal2.add(fs4);

        oal3 = FXCollections.observableArrayList();
        HashMap<String, SimpleStringProperty> hm1 = new HashMap<>();
        hm1.put("name", new SimpleStringProperty("A"));
        hm1.put("age", new SimpleStringProperty("18"));
        hm1.put("gender", new SimpleStringProperty("true"));

        HashMap<String, SimpleStringProperty> hm2 = new HashMap<>();
        hm2.put("name", new SimpleStringProperty("B"));
        hm2.put("age", new SimpleStringProperty("26"));
        hm2.put("gender", new SimpleStringProperty("false"));

        oal3.addAll(hm1, hm2);
    }

    public void baseDemo() {
        tbv = new TableView<>(oal1);

        // 无数据时的占位符。有数据时，即使设置也显示；
        tbv.setPlaceholder(new Button("占位符"));
        tbv.setPrefHeight(200);
        tbv.setTableMenuButtonVisible(true);
        // 表列的排列顺序（不是列中数据的排列顺序）
        tbv.setNodeOrientation(NodeOrientation.INHERIT);

        // 创建表列与表头标题
        nameColumn = new TableColumn<>("姓名");
        nameColumn.setPrefWidth(60.0);
        nameColumn.setVisible(true);
        ageColumn = new TableColumn<>("年龄");
        genderColumn = new TableColumn<>("性别");
        scoreColumn = new TableColumn<>("分数");

        /*
         * 加载数据到列中
         *
         * 以下属性类型实现的ObservableValue接口的泛型是Number类型，不是包装类型；
         * SimpleIntegerProperty、
         * SimpleDoubleProperty、
         * SimpleFloatProperty、
         * SimpleLongProperty
         *
         * 便利方案：
         * 使用 PropertyValueFactory<>("name") 代替 CallBack；
         */
        // nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
                // 获取当前的一列
                // param.getTableColumn();

                // 获取当前TableColumn所在的TableView
                // param.getTableView();
                // 返回一行的数据（一条数据对象）
                // param.getValue();
                SimpleStringProperty name = new SimpleStringProperty(param.getValue().getName());
                return name;
            }
        });
        ageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Student, Number> param) {
                SimpleIntegerProperty age = new SimpleIntegerProperty(param.getValue().getAge());
                return age;
            }
        });
        scoreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Student, Number> param) {
                SimpleDoubleProperty score = new SimpleDoubleProperty(param.getValue().getScore());
                return score;
            }
        });
        genderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> param) {
                SimpleBooleanProperty gender = new SimpleBooleanProperty(param.getValue().getGender());
                return gender;
            }
        });

        // 将列填充到TableView
        tbv.getColumns().add(nameColumn);
        tbv.getColumns().add(ageColumn);
        tbv.getColumns().add(genderColumn);
        tbv.getColumns().add(scoreColumn);

        Button b = new Button("单击");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tbv.getItems().add(new Student("John", 16, 63.3, true));
                Student ms = tbv.getItems().get(0);
                ms.setName("任我行");
                tbv.refresh();
            }
        });
        getChildren().addAll(b, tbv);
        setTopAnchor(tbv, 30.0);
    }

    /**
     * 选择
     */
    public void selectDemo() {
        tbv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // 按行选择
        tbv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> ov, Student t, Student t1) {
            }
        });

        // 按单元格选择
        tbv.getSelectionModel().setCellSelectionEnabled(true);
        tbv.getSelectionModel().getSelectedCells().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                @SuppressWarnings("unchecked")
                ObservableList<TablePosition<ObservableList<Student>, Object>> obl = (ObservableList<TablePosition<ObservableList<Student>, Object>>) o;
                for (int i = 0; i < obl.size(); i++) {
                    TablePosition<ObservableList<Student>, Object> tp = obl.get(i);
                    Object rd = tp.getTableColumn().getCellData(tp.getRow());
                    ol("行= " + tp.getRow() + " 列= " + tp.getColumn() + " 数据= " + rd.toString());
                }
            }
        });
    }

    public void customColumnWidthDemo() {
        // 设置列宽显示策略
        tbv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 自定义策略
        tbv.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
            @Override
            public Boolean call(TableView.ResizeFeatures param) {
                // 输出鼠标拖动的变化量
                ol(param.getDelta());
                if (param.getColumn() != null) {
                    double pw = param.getColumn().getPrefWidth();
                    param.getColumn().setPrefWidth(pw);
                }
                return false;
            }
        });
    }

    public void sortDemo() {
        // nameColumn.setSortable(true);
        // ageColumn.setSortType(TableColumn.SortType.ASCENDING);
        nameColumn.setComparator(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        // tbv.getSortOrder().addAll(nameColumn, scoreColumn);
        tbv.setSortPolicy(new Callback<TableView<Student>, Boolean>() {
            @Override
            public Boolean call(TableView<Student> param) {
                param.getColumns().forEach(new Consumer<TableColumn<Student, ?>>() {
                    @Override
                    public void accept(TableColumn<Student, ?> t) {
                        if (t.getText().equals("姓名")
                                && t.getSortType() == TableColumn.SortType.ASCENDING) {
                            t.setSortNode(new Label("升"));
                            param.getItems().sort(new Comparator<Student>() {
                                @Override
                                public int compare(Student o1, Student o2) {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            });
                        } else if (t.getText().equals("姓名")
                                && t.getSortType() == TableColumn.SortType.DESCENDING) {
                            t.setSortNode(new Label("降"));
                            param.getItems().sort(
                                    new Comparator<Student>() {
                                @Override
                                public int compare(Student o1, Student o2) {
                                    return o2.getName().compareTo(o1.getName());
                                }
                            });
                        }
                    }
                });
                return true;
            }
        });
        tbv.sort(); // 执行此方法，实际调用的是上面的排序策略；

        // 直接对数据源排序（上面的排序结果也会作用于数据源）
        tbv.getItems().sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });

    }

    public void propertyTypeDemo() {
        TableView<FxStudent> tv = new TableView<>(oal2);
        tv.setPrefHeight(200);
        setTopAnchor(tv, 30.0);
        setLeftAnchor(tv, 350.0);

        TableColumn<FxStudent, String> nc = new TableColumn<>("姓名");
        TableColumn<FxStudent, Number> ac = new TableColumn<>("年龄");
        TableColumn<FxStudent, Boolean> gc = new TableColumn<>("性别");
        TableColumn<FxStudent, Number> sc = new TableColumn<>("分数");

        // nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FxStudent, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FxStudent, String> param) {

                return param.getValue().getNameProperty();
            }
        });
        ac.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FxStudent, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<FxStudent, Number> param) {
                return param.getValue().getAgeProperty();
            }
        });
        sc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FxStudent, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<FxStudent, Number> param) {
                return param.getValue().getScoreProperty();
            }
        });
        gc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FxStudent, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<FxStudent, Boolean> param) {
                return param.getValue().getGenderProperty();
            }
        });

        // 将列填充到TableView
        tv.getColumns().add(nc);
        tv.getColumns().add(ac);
        tv.getColumns().add(gc);
        tv.getColumns().add(sc);

        Button b = new Button("单击");
        setLeftAnchor(b, 350.0);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                tv.getItems().add(new FxStudent("John", 16, 63.3, true));
                FxStudent ms = tv.getItems().get(0);
                ms.setName("任我行");
                // 这里就不需要使用refresh()方法了；
                // tv.refresh();
            }
        });
        getChildren().addAll(b, tv);

    }

    public void mergeColumnDemo() {
        TableView<FxStudent> tv = new TableView<>(oal2);
        tv.setPrefHeight(200);
        setTopAnchor(tv, 250.0);

        TableColumn<FxStudent, String> nc = new TableColumn<>("姓名");
        TableColumn<FxStudent, Number> ac = new TableColumn<>("年龄");
        TableColumn<FxStudent, Boolean> gc = new TableColumn<>("性别");
        TableColumn<FxStudent, Number> sc = new TableColumn<>("分数");

        // 合并名称与年龄为一列
        TableColumn<FxStudent, Object> mColumn = new TableColumn<>("信息");
        mColumn.getColumns().add(nc);
        mColumn.getColumns().add(ac);

        nc.setCellValueFactory(new PropertyValueFactory<>("name"));
        ac.setCellValueFactory(new PropertyValueFactory<>("age"));
        sc.setCellValueFactory(new PropertyValueFactory<>("score"));
        gc.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // 将合并后的列填充到TableView
        tv.getColumns().add(mColumn);
        tv.getColumns().add(gc);
        tv.getColumns().add(sc);

        getChildren().add(tv);
    }

    public void mapColumnDemo() {
        TableView<HashMap<String, SimpleStringProperty>> tv = new TableView<>(oal3);
        tv.setPrefHeight(200);
        setLeftAnchor(tv, 350.0);
        setTopAnchor(tv, 250.0);

        TableColumn<HashMap<String, SimpleStringProperty>, String> nc = new TableColumn<>("姓名");
        TableColumn<HashMap<String, SimpleStringProperty>, String> ac = new TableColumn<>("年龄");
        TableColumn<HashMap<String, SimpleStringProperty>, String> gc = new TableColumn<>("性别");

        // nameColumn.setCellValueFactory(new MapValueFactory("name"));
        nc.setCellValueFactory(
                new Callback<
                        TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String> param) {
                return param.getValue().get("name");
            }
        });
        ac.setCellValueFactory(
                new Callback<
                        TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String> param) {
                return param.getValue().get("age");
            }
        });
        gc.setCellValueFactory(
                new Callback<
                        TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap<String, SimpleStringProperty>, String> param) {
                return param.getValue().get("gender");
            }
        });

        // 将合并后的列填充到TableView
        tv.getColumns().add(nc);
        tv.getColumns().add(ac);
        tv.getColumns().add(gc);

        getChildren().add(tv);
    }

    public void editDemo() {
        TableView<FxStudent> tv = new TableView<>(oal2);
        setTopAnchor(tv, 250.0);
        tv.setEditable(true);

        TableColumn<FxStudent, String> nc = new TableColumn<>("姓名");
        TableColumn<FxStudent, Number> ac = new TableColumn<>("年龄");
        TableColumn<FxStudent, Boolean> gc = new TableColumn<>("性别");
        TableColumn<FxStudent, Number> sc = new TableColumn<>("分数");

        // 编辑的工厂方法
        nc.setCellFactory(TextFieldTableCell.forTableColumn());
        ac.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number t) {
                return String.valueOf(t.intValue());
            }

            @Override
            public Number fromString(String string) {
                return null;
            }

        }));
        gc.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Boolean>() {
            @Override
            public String toString(Boolean t) {
                return String.valueOf(t.booleanValue());
            }

            @Override
            public Boolean fromString(String string) {
                return null;
            }
        }));
        sc.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number t) {
                return String.valueOf(t.doubleValue());
            }

            @Override
            public Number fromString(String string) {
                return null;
            }

        }));

        nc.setCellValueFactory(new PropertyValueFactory<>("name"));
        ac.setCellValueFactory(new PropertyValueFactory<>("age"));
        sc.setCellValueFactory(new PropertyValueFactory<>("score"));
        gc.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // 将合并后的列填充到TableView
        tv.getColumns().add(nc);
        tv.getColumns().add(ac);
        tv.getColumns().add(gc);
        tv.getColumns().add(sc);

        getChildren().add(tv);
        tv.setPrefHeight(200);
    }

    public void builtinTableCellDemo() {
        tbv.setEditable(true);

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Number>() {
            @Override
            public String toString(Number t) {
                return String.valueOf(t.intValue());
            }

            @Override
            public Number fromString(String string) {
                return null;
            }

        }));
//        genderColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(new StringConverter<Boolean>() {
//            @Override
//            public String toString(Boolean t) {
//                if (t) {
//                    return "男";
//                } else {
//                    return "女";
//                }
//            }
//
//            @Override
//            public Boolean fromString(String string) {
//                return null;
//            }
//        },
//                true, false));
        genderColumn.setCellFactory(CheckBoxTableCell.forTableColumn(genderColumn));
        // scoreColumn.setCellFactory(ProgressBarTableCell.forTableColumn());
    }

    public void customTableCellDemo() {
        nameColumn.setCellFactory(new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
            @Override
            public TableCell<Student, String> call(TableColumn<Student, String> param) {
                TableCell<Student, String> tc = new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false && item != null) {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            hBox.getChildren().add(new Label(item));
                            this.setGraphic(hBox);
                        }
                    }
                };
                return tc;
            }
        });
        scoreColumn.setCellFactory(new Callback<TableColumn<Student, Number>, TableCell<Student, Number>>() {
            @Override
            public TableCell<Student, Number> call(TableColumn<Student, Number> param) {
                TableCell<Student, Number> tc = new TableCell<>() {
                    @Override
                    protected void updateItem(Number item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false && item != null) {
                            ProgressBar pb = new ProgressBar(item.doubleValue() / 100);
                            this.setGraphic(pb);
                        }
                    }

                    @Override
                    public void startEdit() {
                        super.startEdit();

                        TableCell<Student, Number> tc = this;
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER);
                        TextField tf = new TextField(String.valueOf(this.getUserData()));
                        hBox.getChildren().add(tf);
                        this.setGraphic(hBox);

                        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent t) {
                                if (t.getCode() == KeyCode.ENTER) {
                                    tc.commitEdit(Integer.valueOf(tf.getText()));
                                }
                            }
                        });
                    }

                    @Override
                    public void commitEdit(Number newValue) {
                        super.commitEdit(newValue);
                    }

                    @Override
                    public void cancelEdit() {
                        super.cancelEdit();
                    }

                };
                return tc;
            }
        });
    }

    public void customTableRowDemo() {
        tbv.setRowFactory(new Callback<TableView<Student>, TableRow<Student>>() {
            @Override
            public TableRow<Student> call(TableView<Student> param) {
                TableRow<Student> tr = new TableRow<>() {
                    @Override
                    protected void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false && item != null) {
                            if (item.getScore() < 70) {
                                this.setStyle("-fx-background-color:#886633");
                                // 提示一行
                                this.setTooltip(new Tooltip(item.getName()));
                            }
                            this.setBorder(new Border(new BorderStroke(
                                    Paint.valueOf("#FFB5C5"),
                                    BorderStrokeStyle.DASHED,
                                    CornerRadii.EMPTY, BorderWidths.FULL)));
                        }
                    }

                };
                return tr;
            }
        });
    }
}
