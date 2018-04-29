package com.ncsu.pmishra.whatscookin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class RegisterUserActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private ProgressBar spinner;
    private FirebaseAuth firebaseAuth;

    ArrayList<String> preferenceList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        android.support.v7.widget.Toolbar registerToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(registerToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton = (Button) findViewById(R.id.register_now_button);
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        editTextPassword = (EditText) findViewById(R.id.registerPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
    }

    public void selectPreference(View view){

        boolean isChecked = ((CheckBox) view).isChecked();

        switch (view.getId())
        {
            case R.id.vegCheckbox:
                if (isChecked)
                {
                    preferenceList.add("Vegetarian");
                }
                else
                {
                    preferenceList.remove("Vegetarian");
                }
                break;
            case R.id.nonvegCheckbox:
                if (isChecked)
                {
                    preferenceList.add("Non-Vegetarian");
                }
                else
                {
                    preferenceList.remove("Non-Vegetarian");
                }
                break;
            case R.id.veganCheckbox:
                if (isChecked)
                {
                    preferenceList.add("Vegan");
                }
                else
                {
                    preferenceList.remove("Vegan");
                }
                break;
        }
    }

    public void onButtonClick(View view)
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

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
        if(TextUtils.isEmpty(confirmPassword))
        {
            Toast.makeText(this, "Please re-enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.equals(password, confirmPassword))
        {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

       spinner.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       spinner.setVisibility(View.GONE);
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegisterUserActivity.this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterUserActivity.this, "Cannot register user!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
