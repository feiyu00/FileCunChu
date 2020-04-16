package com.example.filecunchu;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnSharedPreferences = findViewById(R.id.btn_sharedpreferences);
        mBtnSharedPreferences.setOnClickListener(this);
//        请求权限后面是权限名字，最后面是code
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()){
            case R.id.btn_sharedpreferences:
                intent = new Intent(MainActivity.this,SharedPreferencesActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
