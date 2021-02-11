package library.assistant.ui.login;

import javafx.concurrent.Task;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.Cookie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonCSRFToken extends Task {
    public static void main(String[] args) {
        try {
            JsonCSRFToken jsonCSRFToken = new JsonCSRFToken();
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(jsonCSRFToken);
            executorService.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected Object call() throws Exception {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://deepmine.ir:8890/")
                    .method("GET", null)
                    .addHeader("Cookie","csrftoken=dTESXmXRrcnvU5pM8QXOum3XXAgh2soUPZLpGyKk2we6p8M8C4gIhP0hThm3JraF")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(client.cookieJar().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
