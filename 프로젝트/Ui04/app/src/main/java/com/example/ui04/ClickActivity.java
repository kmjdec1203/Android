package com.example.ui04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClickActivity extends AppCompatActivity {

    EditText idEditText;
    EditText pwEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);


        //Toast.makeText(this, , Toast.LENGTH_SHORT).show();


        String extra = getIntent().getStringExtra("MAIN_TEXT");
    }

    public void getText(View view) {
        String id = idEditText.getText().toString();
        String pw = pwEditText.getText().toString();
    }

    public void onClickShowAlert(View view) {
        Context context;
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                .setTitle("클릭했습니다.")
                .setMessage("어떻게 하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "확인 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

        builder.show();
    }
}