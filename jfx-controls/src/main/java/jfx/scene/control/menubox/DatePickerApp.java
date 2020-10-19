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
package jfx.scene.control.menubox;

import jfx.core.app.ContentBox;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class DatePickerApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "MenuBox - DatePicker";
    private DatePicker dp;

    @Override
    public void index() {
        baseDemo();

        eventDemo();

        cellDemo();
    }

    public void baseDemo() {
        dp = new DatePicker(LocalDate.now());
        this.getChildren().add(dp);
        dp.setEditable(false);
//        dp.getValue().getDayOfMonth();
    }

    public void eventDemo() {
        dp.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> ov, LocalDate t, LocalDate t1) {
                ol(t1);
                ol(t1.getYear());
                ol(t1.getMonth());
                ol(t1.getMonthValue());
                ol(t1.getDayOfMonth());
                ol(t1.getDayOfWeek());
                ol(t1.getDayOfYear());
                ol(t1.getChronology());
            }
        });
    }

    public void cellDemo() {
        DatePicker dp2 = new DatePicker();
        dp2.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                DateCell dc = new DateCell() {
                    @Override
                    public void updateItem(LocalDate ld, boolean bln) {
                        super.updateItem(ld, bln);
                        this.setText("dp1");
                        this.setGraphic(new Button("dp2"));
                    }

                };
                return dc;
            }
        });
        setTopAnchor(dp2, 300.0);
        this.getChildren().add(dp2);
    }
}
