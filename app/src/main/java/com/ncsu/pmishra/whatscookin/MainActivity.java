package com.ncsu.pmishra.whatscookin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   private Button loginButtonNormal;
   private EditText editTextEmail;
   private EditText editTextPassword;

   private FirebaseAuth firebaseAuth;
   private ProgressBar progressBar;

   private LoginButton login_button;
   private Button registerNowButton;
   private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //initializeControls();
        //loginWithFB();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
        }

        loginButtonNormal = (Button) findViewById(R.id.login_button_normal);
        editTextEmail = (EditText) findViewById(R.id.email_text);
        editTextPassword = (EditText) findViewById(R.id.password_text);
        progressBar = new ProgressBar(this);

        registerNowButton = findViewById(R.id.register_now_button);
        registerNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startRegistrationActivity();
            }
        });

        loginButtonNormal.setOnClickListener(this);

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

    private void userLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {

        if (v == loginButtonNormal)
        {
            userLogin();
        }

    }
}
