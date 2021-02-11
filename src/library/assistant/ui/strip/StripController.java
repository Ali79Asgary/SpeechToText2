package library.assistant.ui.strip;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.ui.main.JsonPostVoiceFile;
import library.assistant.ui.main.MainRecorder;
import library.assistant.ui.main.Stopwatch;
import library.assistant.ui.main.toolbar.PrepareText;
import library.assistant.util.LibraryAssistantUtil;
import library.assistant.utils.UtilAccessToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StripController implements Initializable {

    File audioFile;
    boolean isRecordPressed = true;
    MainRecorder mainRecorder;
    Stopwatch stopwatch;
    int filesCount;
    String text = "";

    @FXML
    public JFXButton btnRecordStrip;

    @FXML
    private JFXButton btnCloseStrip;

    @FXML
    private JFXButton btnMagnifyStrip;

    @FXML
    private Label lblTimerStrip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRecordStrip.setShape(new Circle(100));
        btnCloseStrip.setShape(new Circle(100));
        btnMagnifyStrip.setShape(new Circle(100));

        isRecordPressed = true;
        stopwatch = new Stopwatch(lblTimerStrip);
        stopwatch.starter();

        try {
            Font fontIRANSans = Font.loadFont(new FileInputStream(new File("D:\\IntelliJ\\Mr.Rasekh_JavaFX\\Library-Assistant\\src\\resources\\IRANSans.TTF")), 12);
            lblTimerStrip.setFont(fontIRANSans);
            btnRecordStrip.setFont(fontIRANSans);
            btnMagnifyStrip.setFont(fontIRANSans);
            btnCloseStrip.setFont(fontIRANSans);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void closeStrip(ActionEvent event) {
        try {
            close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void magnifyStrip(ActionEvent event) {
        try {
            loadMain();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void startRecordingStrip(ActionEvent event) {
        try {
            filesCount = new File("./AudioFiles").listFiles().length;
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(filesCount);
        int fileNumber = filesCount - 1;
        if (isRecordPressed){
            btnRecordStrip.setText("پایان ظبط");
            System.out.println("شروع ظبط");
            stopwatch.play();
            mainRecorder = new MainRecorder(true);
            Thread thread = new Thread(mainRecorder);
            thread.start();
            isRecordPressed = false;
        } else {
            btnRecordStrip.setText("شروع ظبط");
            System.out.println("پایان ظبط");

            mainRecorder.finish();
            mainRecorder.cancel();
            audioFile = new File("./AudioFiles/RecordAudio"+fileNumber+".wav");
            stopwatch.reset(lblTimerStrip);

            JsonPostVoiceFile jsonPostVoiceFile = new JsonPostVoiceFile(UtilAccessToken.accessToken, audioFile);
            jsonPostVoiceFile.setOnSucceeded((succeededEvent) ->{
                if (jsonPostVoiceFile.responseCode == 200){
                    if (jsonPostVoiceFile.resultStatus == 200){
                        text = text.concat(" ").concat(jsonPostVoiceFile.text);
                        char[] textCharArray = PrepareText.divideText(text);
                        for (int i = 0 ; i < text.length() ; i++){
                            PrepareText.persianTextPressKey(textCharArray[i]);
                        }
                    }
                }
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(jsonPostVoiceFile);
            executorService.shutdown();
            isRecordPressed = true;
        }
    }

    public void close() {
        try {
            Stage stage = (Stage) btnCloseStrip.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/assistant/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("دیپ ماین");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
