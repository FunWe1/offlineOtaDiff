package com.hxzj.javafxboot.ctrl;

import cn.hutool.core.date.DateUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@FXMLController
public class selectFile implements Initializable {

    // 主容器
    public Pane filePane;

//    public Button btnAlert, btnChooseFile;

    @FXML
    private Label baseFileText;

    @FXML
    private Label upgradeFileText;

    private File baseFile = null;

    private File upgradeFile = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("initialize: {}", location.getPath());
    }

    /**
     * 弹出框按钮单击事件
     *
     * @param actionEvent
     */
    public void onBtnAlertClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("当前时间：" + DateUtil.now());
        alert.show();
    }

    public void onBtnChooseBaseFileClick(ActionEvent actionEvent) {
        Window window = filePane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        // 文件类型过滤器
        /*FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);*/
        // ...
        baseFile = fileChooser.showOpenDialog(window);
        if (baseFile != null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("文件路径：" + fileAbsolutePath);
//            baseFileText.setText(fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("/")+1));
            baseFileText.setText(baseFile == null ? "" : baseFile.getName());
//            alert.show();
        }
    }

    public void onBtnChooseUpgradeFileClick(ActionEvent actionEvent) {
        Window window = filePane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        // 文件类型过滤器
        /*FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);*/
        // ...
        upgradeFile = fileChooser.showOpenDialog(window);
        if (upgradeFile != null) {
            upgradeFileText.setText(upgradeFile == null ? "" : upgradeFile.getName());
        }
    }

    public void onBtnGenerateDiffFileClick(ActionEvent actionEvent) {
        if (baseFile == null) {
            showAlert("请选择基线包");
        } else if (upgradeFile == null) {
            showAlert("请选择升级包");
        } else {

        }

        try{

        } catch (Exception e) {

        }

    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}

