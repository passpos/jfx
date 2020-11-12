/*
 * Copyright (C) 2020 realpai <paiap@outlook.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY;
 * without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jfx.app;

import javafx.geometry.Pos;
import jfx.core.app.AbstractSideBox;
import jfx.core.app.ButtonWrapper;
import jfx.core.app.ContentBox;

/**
 *
 * @author realpai <paiap@outlook.com>
 */
public class SideBox extends AbstractSideBox {

    @Override
    public void setButtonStyle(ButtonWrapper btn) {
        String title = btn.getTitle();

        // 为按钮设置宽度；
        btn.setPrefWidth(this.getBtnBox().getPrefWidth());

        if (title.startsWith("Animation - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else if (title.contains("Transition")) {
                btn.setStyle("-fx-background-color: #aa66aa");
            } else {
                btn.setStyle("-fx-background-color: #aa66aa");
            }
        }

        if (title.startsWith("Application - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Concurrent - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else {
                btn.setStyle("-fx-background-color: #ffe4c4");
            }
        }

        // jfx.scene
        if (title.startsWith("Canvas - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Effect - ")) {
            if (title.contains("Blur")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else if (title.contains("Shadow")) {
                btn.setStyle("-fx-background-color: #aa99ac");
            } else {
                btn.setStyle("-fx-background-color: #aa99b5");
            }
        }

        if (title.startsWith("Geometry - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else {
                btn.setStyle("-fx-background-color: #ffe499");
            }
        }

        if (title.startsWith("Image - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae499");
            }
        }

        if (title.startsWith("Input - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa99b5");
            }
        }

        if (title.startsWith("Layout - ")) {
            if (title.contains("Pane")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else if (title.contains("Box")) {
                btn.setStyle("-fx-background-color: #ffe499");
            } else if (title.contains("Background")) {
                btn.setStyle("-fx-background-color: #ffe4cc");
            } else if (title.contains("Border")) {
                btn.setStyle("-fx-background-color: #ffe499");
            } else if (title.contains("Constraints")) {
                btn.setStyle("-fx-background-color: #ffe4cc");
            } else {
                btn.setStyle("-fx-background-color: #ffe499");
            }
        }

        if (title.startsWith("Paint - ")) {
            if (title.contains("Material")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Robot - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa99aa");
            }
        }

        if (title.startsWith("Shape - ")) {
            if (title.contains("Line") || title.contains("line")) {
                btn.setStyle("-fx-background-color: #ffe4aa");
            } else if (title.contains("Curve")) {
                btn.setStyle("-fx-background-color: #ffe499");
            } else if (title.contains("Path")) {
                btn.setStyle("-fx-background-color: #ffe4cc");
            } else if (title.contains("3D")) {
                btn.setStyle("-fx-background-color: #ffe499");
            } else {
                btn.setStyle("-fx-background-color: #ffe4cc");
            }
        }

        if (title.startsWith("Text - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aae4aa");
            } else {
                btn.setStyle("-fx-background-color: #aae4c4");
            }
        }

        if (title.startsWith("Transform - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa99aa");
            }
        }

        if (title.startsWith("Stage - ")) {
            if (title.contains("Demo")) {
                btn.setStyle("-fx-background-color: #aa99c4");
            } else {
                btn.setStyle("-fx-background-color: #aa66aa");
            }
        }

        btn.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    public void setDefaultBtn() {
        if (defaultBtn == null) {
            this.defaultBtn = (ButtonWrapper) this.getBtnBox().getChildren().get(0);
        }
        ContentBox.getPrimaryStage().setTitle(defaultBtn.getTitle());
    }

}
