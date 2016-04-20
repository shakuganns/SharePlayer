package me.imirai.shareplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

/**
 * Created by Shakugan on 16/4/20.
 */
public class SharedPreUtil {
    // 用户名key
    public final static String KEY_NAME = "shareplayer.imirai.me";

//    public final static String KEY_LEVEL = "debug";


    private static SharedPreUtil s_SharedPreUtil;

    private SharedPreferences msp;

    public SharedPreUtil(Context context) {
        msp = context.getSharedPreferences("SharedPreUtil",
                Context.MODE_APPEND);
    }

    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context) {
        if (s_SharedPreUtil == null) {
            s_SharedPreUtil = new SharedPreUtil(context);
        }
    }

    /**
     * 获取唯一的instance
     *
     * @return instance
     */
    public static synchronized SharedPreUtil getInstance() {
        return s_SharedPreUtil;
    }

    public synchronized VideoRecord getVideoRecord() {

        //获取序列化的数据
        String str = msp.getString(SharedPreUtil.KEY_NAME, "");

        try {
            Object obj = SerializableUtil.str2Obj(str);
            if (obj != null) {
                VideoRecord.setInstance((VideoRecord)obj);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return VideoRecord.getInstance();
    }

    public synchronized void putVideoRecord(VideoRecord videoRecord) {

        SharedPreferences.Editor editor = msp.edit();

        String str = "";
        try {
            str = SerializableUtil.obj2Str(videoRecord);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(KEY_NAME, str);
        editor.apply();
    }

}
