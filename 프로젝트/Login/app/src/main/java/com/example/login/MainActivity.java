package com.example.login;

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

    /** Called when the user taps the Send button */
    public void onClick(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText id = (EditText) findViewById(R.id.id);
        EditText password = (EditText) findViewById(R.id.password);
        String getid = id.getText().toString();
        String getpassword = password.getText().toString();

        if (getid.getBytes().length != 0 && getpassword.getBytes().length != 0) {
            startActivity(intent);
        }
    }
}