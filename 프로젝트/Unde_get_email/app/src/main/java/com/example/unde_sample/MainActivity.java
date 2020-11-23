package com.example.unde_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.CallableStatement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.Bye_text);

        Retrofit client = new RetrofitClient().getInstance();
        RetroFitInterface retroFitInterface = client.create(RetroFitInterface.class);

        Call<UserData> call = retroFitInterface.getUser();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData result = response.body();
                textView.setText(result.getData().getEmail());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                makeFailToast();
            }
        });
    }
    void makeFailToast() {
        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
    }
}