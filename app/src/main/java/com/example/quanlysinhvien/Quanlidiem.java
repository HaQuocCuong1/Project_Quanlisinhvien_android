package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quanlidiem extends AppCompatActivity {
    Button btnnhapdiem, btnxemdiem, btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlidiem);
        btnnhapdiem = (Button) findViewById(R.id.btn_nhapdiem);
        btnxemdiem = (Button) findViewById(R.id.btn_xemdiem);
        btnthoat = (Button) findViewById(R.id.btn_thoat);

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnnhapdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quanlidiem.this, Nhapdiemsv.class);
                startActivity(intent);
            }
        });
        btnxemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quanlidiem.this, Xemdiemsv.class);
                startActivity(intent);
            }
        });
    }
}