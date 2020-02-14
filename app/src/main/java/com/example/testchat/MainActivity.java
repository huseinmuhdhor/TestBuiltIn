package com.example.testchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qiscus.sdk.Qiscus;
import com.qiscus.sdk.chat.core.util.QiscusErrorLogger;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton = findViewById(R.id.bt_login);
    }

    public void loginOrLogout(View view) {
        if (Qiscus.hasSetupUser()) {
            Qiscus.clearUser();
            mLoginButton.setText("Login");
        } else {
            Qiscus.setUser("l@mail.com", "12345678")
                    .withUsername("Lazarus")
                    .save()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(qiscusAccount -> {
                        Log.i("MainActivity", "Login with account: " + qiscusAccount);
                        mLoginButton.setText("Logout");
                    }, throwable -> {
                        Log.e("cccc", "loginOrLogout: " + throwable);
                    });
        }
    }

    public void openChat(View view) {
        Qiscus.buildChatWith("m@mail.com")
                .build(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(intent -> {
                    startActivity(intent);
                }, throwable -> {
                    Log.e("cccc", "openChat: " + throwable);
                });
    }
}
