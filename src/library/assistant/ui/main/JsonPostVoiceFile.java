package library.assistant.ui.main;

import com.jfoenix.controls.JFXTextArea;
import javafx.concurrent.Task;
import library.assistant.utils.UtilAccessToken;
import netscape.javascript.JSException;
import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonPostVoiceFile extends Task {

    String accessToken = "";
    File wavFile;
    public int responseCode = 0;
    public int resultStatus = 0;
    public String text = "";
    JFXTextArea textArea;

    public JsonPostVoiceFile(String accessToken, File wavFile) {
        this.accessToken = accessToken;
        this.wavFile = wavFile;
    }

    public JsonPostVoiceFile(String accessToken, File wavFile, JFXTextArea textArea) {
        this.accessToken = accessToken;
        this.wavFile = wavFile;
        this.textArea = textArea;
    }

    @Override
    protected Object call() throws Exception {
        try {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("file", wavFile);
//            } catch (JSException e){
//                e.printStackTrace();
//                System.out.println(e.getMessage());
//            }
//            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
//            MediaType mediaType = MediaType.parse("application/json");
//            RequestBody requestBody = RequestBody.create(mediaType, wavFile);
//            Request request = new Request.Builder().url("http://deepmine.ir:8890/api/speech2text/").method("POST", requestBody).addHeader("Authorization", "Token {"+accessToken+"}").addHeader("X-CSRFToken","dTESXmXRrcnvU5pM8QXOum3XXAgh2soUPZLpGyKk2we6p8M8C4gIhP0hThm3JraF").build();
//            Response response = okHttpClient.newCall(request).execute();
//            CookieJar cookieJar = okHttpClient.cookieJar();
//            System.out.println(cookieJar);
//            responseCode = response.code();
//            String result = response.body().string();
//            System.out.println(response.toString());
//            System.out.println(result);
//            System.out.println(responseCode);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", wavFile.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), wavFile)).build();
            Request request = new Request.Builder().
                    url("http://deepmine.ir:8890/api/speech2text/").method("POST", body).
                    addHeader("Authorization", "Token "+accessToken).
                    addHeader("X-CSRFToken", "dTESXmXRrcnvU5pM8QXOum3XXAgh2soUPZLpGyKk2we6p8M8C4gIhP0hThm3JraF").
                    addHeader("Cookie", "csrftoken=dTESXmXRrcnvU5pM8QXOum3XXAgh2soUPZLpGyKk2we6p8M8C4gIhP0hThm3JraF").build();
            Response response = client.newCall(request).execute();
            responseCode = response.code();
            String result = response.body().string();
            System.out.println("speech2text: "+result);
            JSONObject jsonObject = new JSONObject(result);
            resultStatus = jsonObject.getInt("status");
            if (resultStatus == 200){
                text = jsonObject.getString("data");
                System.out.println("text of speech: "+text);
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
