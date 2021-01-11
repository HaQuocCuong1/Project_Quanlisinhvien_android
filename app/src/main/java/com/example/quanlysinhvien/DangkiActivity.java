package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Dangki;

import java.util.List;

public class DangkiActivity extends AppCompatActivity {
    private Button btndkquaylai, btnluudk;
    EditText edttaikhoan, edtmatkhau, edtnhaplai, edtemail;
    List<Dangki> dangkis;
    DBManagerLophoc dbManagerLophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        Anhxa();
        dbManagerLophoc = new DBManagerLophoc(this);
        btndkquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnluudk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edttaikhoan.getText().toString();
                String matkhau =edtmatkhau.getText().toString();
                String nhaplai = edtnhaplai.getText().toString();
                String email = edtemail.getText().toString();
                if (matkhau.equals(nhaplai))
                {
                    Dangki dangki = new Dangki(taikhoan, matkhau, nhaplai, email);
                    if (dbManagerLophoc.addtaikhoan(dangki) > 0)
                    {
                        finish();
                        Toast.makeText(DangkiActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(DangkiActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(DangkiActivity.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Anhxa()
    {
        btndkquaylai = (Button) findViewById(R.id.btn_dkquaylai);
        btnluudk = (Button) findViewById(R.id.btn_dkDangki);
        edttaikhoan = (EditText) findViewById(R.id.edt_dktaikhoan);
        edtmatkhau = (EditText) findViewById(R.id.edt_dkmatkhau);
        edtnhaplai = (EditText) findViewById(R.id.edt_dknhaplaimk);
        edtemail = (EditText) findViewById(R.id.edt_dkemail);
    }
}