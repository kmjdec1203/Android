package com.example.ui04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_order : {
                setUpToast("Order");
                break;
            }
            case R.id.menu_status : {
                setUpToast("Status");
                break;
            }
            case R.id.menu_favorites : {
                setUpToast("Favorites");
                break;
            }
            case R.id.menu_contact : {
                Intent intent = new Intent(this, ClickActivity.class);
                startActivity(intent);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    private void setUpToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}