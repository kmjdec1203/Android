package com.example.kakaotalk_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(this, ClickActivity.class);

        EditText email_text = findViewById(R.id.editText_email);
        EditText password_text = findViewById(R.id.editText_password);

        String email = email_text.getText().toString();
        String password = password_text.getText().toString();

        if(email.equals("kmjdec1203@gmail.com") && password.equals("kmcho111")) {
            startActivity(intent);
        }
    }
}