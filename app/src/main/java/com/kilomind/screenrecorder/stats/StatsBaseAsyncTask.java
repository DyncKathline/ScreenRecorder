package com.kilomind.screenrecorder.stats;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.Log;

import com.kilomind.screenrecorder.NativeCommands;
import com.kilomind.screenrecorder.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class StatsBaseAsyncTask extends AsyncTask<Void, Void, Void> {

    protected Map<String, String> params = new HashMap<String, String>();

    public StatsBaseAsyncTask(Context context) {
        params.put("package_name", context.getPackageName());
        params.put("app_version", String.valueOf(Utils.getAppVersion(context)));
        params.put("device_id", Secure.getString(context.getContentResolver(), Secure.ANDROID_ID));
        params.put("su_version", NativeCommands.getInstance().getSuVersion());
    }

    @Override
    protected void onPreExecute() {
        params.put("build_device", Build.DEVICE);
        params.put("build_board", Build.BOARD);
        params.put("build_hardware", Build.HARDWARE);
        params.put("build_model", Build.MODEL);
        params.put("build_id", Build.ID);
        params.put("build_version_sdk_int", String.valueOf(Build.VERSION.SDK_INT));
        params.put("build_version_release", Build.VERSION.RELEASE);
        params.put("is_x86", formatBoolean(Utils.isX86()));
    }

    protected String formatBoolean(boolean value) {
        return value ? "1" : "0";
    }

    protected abstract String getUrl();

    protected abstract String getTag();

    @Override
    protected Void doInBackground(Void... voids) {

        String url = getUrl();

        for (String key: params.keySet()) {
            String vale = params.get(key);
            if (vale == null) continue;

            try {
                url += key.toLowerCase() + '=' + URLEncoder.encode(vale, "UTF-8") + "&";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF-8 should always be supported", e);
            }
        }

        url += "request_id=" + Utils.md5(url + "SaltLakeCity");

        String resultString = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();  //创建url的请求
        Response response = null;
        try {
            response = client.newCall(request).execute();  //execute() : 同步, enqueue() : 异步
            resultString = response.body().string();  //获取数据
            Log.w(getTag(), "Response: " + resultString);
        } catch (IOException e) {
            Log.w(getTag(), "HTTP GET execution error", e);
            e.printStackTrace();
        }

        return null;
    }

}
