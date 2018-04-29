package com.ncsu.pmishra.whatscookin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity{
    //TextView textStatus;
    LoginButton login_button;
    Button registerNowButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //initializeControls();
        //loginWithFB();

        registerNowButton = findViewById(R.id.register_now_button);
        registerNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startRegistrationActivity();
            }
        });

    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
       // textStatus = (TextView) findViewById(R.id.textStatus);
        login_button = (LoginButton) findViewById(R.id.login_button);
    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               // textStatus.setText("Login Successfully\n"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //textStatus.setText("Login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
               // textStatus.setText("login Error: "+error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startRegistrationActivity(){
            Intent intent = new Intent(this, RegisterUserActivity.class);
            startActivity(intent);
    }

}
