package com.example.unde_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView tv_lastname;
    TextView tv_firstname;
    TextView tv_email;
    TextView tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_lastname = findViewById(R.id.last_name);
        tv_firstname = findViewById(R.id.first_name);
        tv_email = findViewById(R.id.email);
        tv_id = findViewById(R.id.id);

        Retrofit client = new RetrofitClient().getInstance();
        RetroFitInterface retroFitInterface = client.create(RetroFitInterface.class);

//        Call<UserData> call = retroFitInterface.getUser();
//        call.enqueue(new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                UserData result = response.body();
//                Log.d("qwerty", "data: " + result.getData().getFirst_name());
//                tv_firstname.setText(result.getData().getFirst_name());
//                tv_lastname.setText(result.getData().getLast_name());
//                tv_email.setText(result.getData().getEmail());
//                tv_id.setText(result.getData().getId() +"");
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
//                makeFailToast();
//            }
//        }); //id: 367 Call<CreateResponse> call = retroFitInterface.createUser("undefined", "programer");

//        Call<CreateResponse> call = retroFitInterface.updateUser("defined", "chicken");
//        call.enqueue(new Callback<CreateResponse>() {
//            @Override
//            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
//                CreateResponse createResponse = response.body();
//                Log.d("qwerty", "data: " + response.body().getId());
//                tv_firstname.setText(createResponse.getJob());
//                tv_id.setText(createResponse.getName());
//            }
//
//            @Override
//            public void onFailure(Call<CreateResponse> call, Throwable t) {
//                makeFailToast();
//            }
//        });
//    }

        Call<Void> call = retroFitInterface.deleteUser();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("qwerty", "code" + response.code());
                tv_email.setText(response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                makeFailToast();
            }
        });
    }

    void makeFailToast() {
        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
    }
}