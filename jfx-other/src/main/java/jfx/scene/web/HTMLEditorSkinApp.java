package jfx.scene.web;

import javafx.scene.web.HTMLEditor;
import javafx.scene.web.HTMLEditorSkin;
import jfx.utils.app.ContentBox;

/**
 * 工具栏1 - ToolBar toolbar1
 *
 * 剪切-复制-粘贴
 * Button cutButton;
 * Button copyButton;
 * Button pasteButton;
 *
 * 撤销-重做
 * Button undoButton;
 * Button redoButton;
 *
 * 对齐方式 - alignmentToggleGroup;
 * ToggleButton alignLeftButton;
 * ToggleButton alignCenterButton;
 * ToggleButton alignRightButton;
 * ToggleButton alignJustifyButton;
 *
 * 缩进-凸排
 * Button indentButton;
 * Button outdentButton;
 *
 * 列表
 * ToggleButton bulletsButton;
 * ToggleButton numbersButton;
 *
 * 背景颜色-前景颜色
 * ColorPicker fgColorButton;
 * ColorPicker bgColorButton;
 *
 *
 * 工具栏2 - ToolBar toolbar2
 *
 * 字体风格
 * ComboBox<String> fontFamilyComboBox;
 *
 * 字体大小
 * ComboBox<String> fontSizeComboBox;
 *
 * Map<String, String> fontSizeMap;
 * Map<String, String> sizeFontMap;
 *
 * 格式化
 * ComboBox<String> formatComboBox;
 * Map<String, String> formatStyleMap;
 * Map<String, String> styleFormatMap;
 *
 * 加粗-斜体-下划线-删除线
 * ToggleButton boldButton;
 * ToggleButton italicButton;
 * ToggleButton underlineButton;
 * ToggleButton strikethroughButton;
 *
 * 水平标线
 * Button insertHorizontalRuleButton;
 *
 *
 * 其他属性：
 *
 * WebView webView;
 * WebPage webPage;
 * ParentTraversalEngine engine
 *
 *
 *
 * 缓存HTML文本（用于设置编辑）
 * String cachedHTMLText =
 * "<html><head></head><body contenteditable=\"true\"></body></html>"
 *
 *
 * @author realpai <paiap@outlook.com>
 */
public class HTMLEditorSkinApp extends ContentBox {

    public static final boolean SHOWING = false;
    public static final String TITLE = "Web - HTMLEditorSkin";

    @Override
    public void index() {
        baseDemo();
    }

    public void baseDemo() {
        HTMLEditorSkin hes = new HTMLEditorSkin(new HTMLEditor());
        hes.performCommand(HTMLEditorSkin.Command.FORMAT);
    }
}
