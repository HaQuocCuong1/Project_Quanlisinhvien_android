package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trangchu_quanliSV extends AppCompatActivity {
    private Button btnquanlisv, btnquanlilh, btnquanlidiem, btndangxuat, btnthongke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu_quanli_s_v);
        btnquanlisv = (Button) findViewById(R.id.btn_quanlysv);
        btnquanlilh = (Button) findViewById(R.id.btn_quanlylh);
        btnquanlidiem = (Button) findViewById(R.id.btn_quanlydiem);
        btndangxuat = (Button) findViewById(R.id.btn_dangxuat);
        btnthongke = (Button) findViewById(R.id.btn_thongke);
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnquanlisv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trangchu_quanliSV.this, Quanlisinhvien.class);
                startActivity(intent);
            }
        });
        btnquanlilh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trangchu_quanliSV.this, Quanlidanhsach.class);
                startActivity(intent);
            }
        });
        btnquanlidiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trangchu_quanliSV.this, Quanlidiem.class);
                startActivity(intent);
            }
        });
        btnthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trangchu_quanliSV.this, Thongke.class);
                startActivity(intent);
            }
        });
    }
}