package library.assistant.ui.login;

import javafx.concurrent.Task;
import library.assistant.utils.UtilAccessToken;
import netscape.javascript.JSException;
import okhttp3.*;
import org.json.JSONObject;

import java.net.CookieStore;

public class JsonPostLogin extends Task<Integer> {

    public static final MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
    final String SITE_ADDR = "deepmine.ir:8890/";
    String username = "";
    String password = "";
    int responseCode = 0;
    String accessTokenLogin = "";
    String refreshToken = "";
    int userId = 0;

    public JsonPostLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected Integer call() throws Exception {
        try {
            System.out.println("Hello Ali");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", username);
                jsonObject.put("password", password);
            } catch (JSException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println(jsonObject);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            CookieStore cookieStore;
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
            Request request = new Request.Builder()
                    .url("http://deepmine.ir:8890/api/token/")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            CookieJar cookieJar = client.cookieJar();
            System.out.println(cookieJar);
            System.out.println(response);
            responseCode = response.code();
            System.out.println(responseCode);
            String result = response.body().string();
            JSONObject resultJsonObj = new JSONObject(result);
            UtilAccessToken.accessToken = resultJsonObj.getString("access_token");
            System.out.println(result);
            System.out.println(UtilAccessToken.accessToken);
//            OkHttpClient okHttpClient = new OkHttpClient();
//            RequestBody requestBody = RequestBody.create(jsonMediaType, jsonObject.toString());
//            Request request = new Request.Builder().url(SITE_ADDR+"api/forget_get_phone/").post(requestBody).build();
//            Response response = null;
//            String loginResult = "";
//            JSONObject jsonObjectLogin = null;
//            try {
//                response = okHttpClient.newCall(request).execute();
//                responseCode = response.code();
//                System.out.println("ResponseCode "+responseCode);
//                loginResult = response.body().string();
//                System.out.println("ResponseResult "+loginResult);
//                jsonObjectLogin = new JSONObject(loginResult);
//                if (responseCode == 200){
//                    accessTokenLogin = jsonObjectLogin.getString("access_token");
//                    System.out.println("AccessTokenLogin "+accessTokenLogin);
//                    refreshToken = jsonObjectLogin.getString("refresh_token");
//                    System.out.println("RefreshTokenLogin "+refreshToken);
//                    userId = jsonObjectLogin.getInt("user_id");
//                    System.out.println("UserIdLogin "+userId);
//                }
//            } catch (IOException | NullPointerException e){
//                e.printStackTrace();
//            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return responseCode;
    }
}
