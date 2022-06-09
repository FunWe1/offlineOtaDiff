package com.hxzj.javafxboot.ctrl;

import com.alibaba.fastjson.JSON;
import com.hxzj.javafxboot.view.DataTable;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Slf4j
@FXMLController
public class mainCtrl implements Initializable {

    // 主容器
    @FXML
    public Pane rootPane;

    @FXML
    TableView dataTable;

    @FXML
    private Label baseFileText;

    @FXML
    private Label upgradeFileText;

    private final String configFilePath;

    public mainCtrl(@Value("${configFilePath}") String configFilePath) {
        this.configFilePath = configFilePath;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
        log.info("initialize: {}", location.getPath());
    }

    /**
     * 弹出框按钮单击事件
     *
     * @param actionEvent
     */
    @FXML
    public void onBtnSelectFileClick(ActionEvent actionEvent) {
//        ObservableList<Stage> stage = FXRobotHelper.getStages();
//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/selectFile.fxml")));
//        stage.get(0).setScene(scene);
        Platform.runLater(() -> {
            Stage saveDiary = null; // StageManager.getStage("saveDiary");
            // 每次创建场景前，判断该场景是否被创建过，创建过直接显示场景即可，无需多次创建，但是需要清除上次输入的数据
            if (Objects.isNull(saveDiary)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/selectFile.fxml"));
                    Parent pane = (Parent) fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("新生成差分包");
//                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                    // 存放Scene
                    //StageManager.put("saveDiary", stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                saveDiary.show();
            }
        });
    }

    public void onBtnChooseBaseFileClick(ActionEvent actionEvent) {
        Window window = rootPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        // 文件类型过滤器
        /*FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);*/
        // ...
        File file = fileChooser.showOpenDialog(window);
        String fileName = file == null ? "" : file.getName();
        String fileAbsolutePath = file == null ? "" : file.getAbsolutePath();

        if (file != null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("文件路径：" + fileAbsolutePath);
            baseFileText.setText(fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("/") + 1));
//            alert.show();
        }
    }

    public void onBtnChooseUpgradeFileClick(ActionEvent actionEvent) {
        Window window = rootPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        // 文件类型过滤器
        /*FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel files (*.xls, *.xlsx)", "*.xls", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);*/
        // ...
        File file = fileChooser.showOpenDialog(window);
        String fileName = file == null ? "" : file.getName();
        String fileAbsolutePath = file == null ? "" : file.getAbsolutePath();

        if (file != null) {
            upgradeFileText.setText(fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("/") + 1));
        }
    }

    private void load() {
        String json = readFile(configFilePath);
        List<DataTable> jsonObjectList = JSON.parseArray(json, DataTable.class);
        for (DataTable jsonObject : jsonObjectList) {
            System.out.println(jsonObject);
        }
        ObservableList<DataTable> data = FXCollections.observableArrayList();
//        ObservableList<TableColumn> observableList = dataTable.getColumns();
//        observableList.get(2).setCellValueFactory(new PropertyValueFactory("baseVer"));
//        observableList.get(4).setCellValueFactory(new PropertyValueFactory("upgradeVer"));
//        for(JSONObject jsonObject : jsonObjectList){
//            Field[] fields = jsonObject.getClass().getDeclaredFields();
//            for(Map.Entry<String, Object> entry : jsonObject.getInnerMap().entrySet()){
//                    try {
//
//                        //System.out.println("entity.getvalue:"+entity.getValue());
//                        for(Field field : fields) {
//                            if(field.getName().equals(entry.getKey())) {
//                                field.set(jsonObject, entry.getValue());
//                            }
//                        }
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                data.add(jsonObject);
//            }
//            data.addAll(jsonObjectList);
//        }
        data.addAll(jsonObjectList);
        dataTable.setItems(data);
    }

    private String readFile(String path) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }
            is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String s = null;
            while(true) {
                try {
                    if (!(null != (s = br.readLine()))) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
