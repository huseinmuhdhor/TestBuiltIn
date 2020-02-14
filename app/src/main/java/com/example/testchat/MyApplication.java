package com.example.testchat;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.qiscus.sdk.Qiscus;
import com.qiscus.sdk.data.model.QiscusDeleteCommentConfig;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Qiscus.setup(this, "sdksample");

        Qiscus.getChatConfig()
                .enableDebugMode(true)
                .setEnableAddLocation(false)
                .setDeleteCommentConfig(new QiscusDeleteCommentConfig()
                        .setEnableDeleteComment(true)
                        .setEnableHardDelete(true));
    }
}
