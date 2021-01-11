package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class Them_sinhvien extends AppCompatActivity {
    EditText edtmasv, edttensv, edtnamsinh, edtdiachi, edtsdt;
    RadioButton rbnam, rbnu;
    Spinner slophoc;
    Button btnluu, btnthoat;
    ArrayList<String> lophocs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinhvien);
        anhxa();

        lophocs = new ArrayList<>();
        DBManagerLophoc dbManagerLophoc = new DBManagerLophoc(this);
        lophocs = dbManagerLophoc.hienthitenlophoc();
        ArrayAdapter<String> adaptertenlophoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lophocs);
        slophoc.setAdapter(adaptertenlophoc);

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String[] lophoc1 = new String[1];
        slophoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lophoc1[0] = lophocs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String masv = edtmasv.getText().toString();
                String tensv = edttensv.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                String diachi = edtdiachi.getText().toString();
                String sdt = edtsdt.getText().toString();
                String gioitinh = null;
                if (rbnam.isChecked())
                {
                    gioitinh = "Nam";
                }
                if (rbnu.isChecked())
                {
                    gioitinh = "Nữ";
                }
                intent.putExtra("msv", masv);
                intent.putExtra("tsv", tensv);
                intent.putExtra("ns", namsinh);
                intent.putExtra("dc", diachi);
                intent.putExtra("gt", gioitinh);
                intent.putExtra("sdt", sdt);
                intent.putExtra("mlh", lophoc1[0]);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    public void anhxa()
    {
        edtmasv = (EditText) findViewById(R.id.edt_masv);
        edttensv = (EditText) findViewById(R.id.edt_tensv);
        edtnamsinh = (EditText) findViewById(R.id.edt_nssv);
        edtdiachi = (EditText) findViewById(R.id.edt_diachisv);
        rbnam = (RadioButton) findViewById(R.id.rb_nam);
        rbnu = (RadioButton) findViewById(R.id.rb_nu);
        slophoc = (Spinner) findViewById(R.id.spinner_lophoc);
        btnluu = (Button) findViewById(R.id.btn_luu);
        btnthoat = (Button) findViewById(R.id.btn_thoat);
        edtsdt = (EditText) findViewById(R.id.edt_sodienthoai);
    }
}