package com.gssirohi.app.utility;

import android.content.Context;
import android.graphics.Point;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by gssirohi on 28/8/16.
 */
public class CommonUtils {

    public static String getDeviceId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static String convertStreamToString(InputStream inputStream) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));

            String streamLine;
            try {
                while ((streamLine = reader.readLine()) != null) {
                    stringBuilder.append(streamLine + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static boolean isRfsBuild() {
        return true;
    }


    private static int screenWidth = 0;

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }
}
