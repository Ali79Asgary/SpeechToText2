package library.assistant.ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.jndi.toolkit.url.Uri;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.ui.main.JsonPostVoiceFile;
import library.assistant.ui.settings.Preferences;
import library.assistant.util.LibraryAssistantUtil;
import library.assistant.utils.UtilAccessToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController implements Initializable {

    private final static Logger LOGGER = LogManager.getLogger(LoginController.class.getName());
    boolean isStripOpen = false;

    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label lblGoToSignUp;
    @FXML
    private Label lblErrorSignUp;

    Preferences preference;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preference = Preferences.getPreferences();
        try {
            Font fontIRANSans = Font.loadFont(new FileInputStream(new File("src\\resources\\IRANSans.TTF")), 20);
            username.setFont(fontIRANSans);
            password.setFont(fontIRANSans);
            btnLogin.setFont(fontIRANSans);
            btnCancel.setFont(fontIRANSans);
            lblGoToSignUp.setFont(fontIRANSans);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("hello");
        try {
            String uname = StringUtils.trimToEmpty(username.getText());
            String pword = StringUtils.trimToEmpty(password.getText());

            if (username.getText().equals("") || password.getText().equals("")){
                lblErrorSignUp.setText("لطفا تمام فیلدها را تکمیل نمایید!");
            } else {
                try {
                    JsonPostLogin jsonPostLogin = new JsonPostLogin(uname, pword);
                    jsonPostLogin.setOnSucceeded((succeededEvent) ->{
                        if (jsonPostLogin.responseCode == 200){
                            System.out.println("On Succeeded");
                            closeStage();
                            loadStrip(isStripOpen);
//                            loadMain();
                            LOGGER.log(Level.INFO, "User successfully logged in {}", uname);
                        } else {
                            System.out.println("Login failed!");
                            lblErrorSignUp.setText("اطلاعات وارد شده صحیح نمیباشد!");
                        }
                    });
                    jsonPostLogin.setOnFailed((failedEvent) -> {
                        System.out.println("On Failed");
                        LOGGER.log(Level.ERROR, "User successfully logged in {}", uname);
                    });
                    ExecutorService executorService = Executors.newFixedThreadPool(1);
                    executorService.execute(jsonPostLogin);
                    executorService.shutdown();
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }


//                    try {
//                        File file = new File("record.wav");
//                        JsonPostVoiceFile jsonPostVoiceFile = new JsonPostVoiceFile(UtilAccessToken.accessToken, file);
//                        jsonPostVoiceFile.setOnSucceeded((succeededEvent) -> {
//                            if (jsonPostVoiceFile.responseCode == 200){
//                                System.out.println("The file sent successfully!");
//                            } else {
//                                System.out.println("The file not sent!");
//                            }
//                        });
//                        jsonPostVoiceFile.setOnFailed((failedEvent) -> {
//                            System.out.println("OnFailed!");
//                        });
//                        ExecutorService executorService = Executors.newFixedThreadPool(1);
//                        executorService.execute(jsonPostVoiceFile);
//                        executorService.shutdown();
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }



        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void goToSignUp(MouseEvent mouseEvent) {
        openLink(URI.create("https://google.com/"));
    }

    public static boolean openLink(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try {
                desktop.browse(uri);
                return true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openLink(URL url){
        try {
            return openLink(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
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
            LOGGER.log(Level.ERROR, "{}", ex);
        }
    }

    public void loadStrip(boolean isStripOpen) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/assistant/ui/strip/strip.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            if (!isStripOpen){
                stage.setTitle("Strip");
                stage.setScene(new Scene(parent));
                stage.setAlwaysOnTop(true);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                stage.setX(610);
                stage.setY(0.0);
                LibraryAssistantUtil.setStageIcon(stage);
                this.isStripOpen = true;
            } else {
                stage.close();
                this.isStripOpen = false;
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
