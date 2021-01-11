package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Dangki;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btndangkki;
    private Button btndangnhap;
    private EditText edttaikhoan;
    private EditText edtmatkhau;
    private CheckBox cbluu;
    SharedPreferences sharedPreferences;
    DBManagerLophoc dbManagerLophoc;
    List<Dangki> dangkis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        dbManagerLophoc = new DBManagerLophoc(this);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edttaikhoan.setText(sharedPreferences.getString("taikhoan",""));
        edtmatkhau.setText(sharedPreferences.getString("matkhau",""));
        cbluu.setChecked(sharedPreferences.getBoolean("checked", false));
        btndangkki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangkiActivity.class);
                startActivityForResult(intent,9999);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edttaikhoan.getText().toString();
                String matkhau = edtmatkhau.getText().toString();
                dangkis = dbManagerLophoc.getALLtaikhoan();
                boolean kt =false;
                for (Dangki dk : dangkis)
                {
                    if (taikhoan.equals(dk.getTaiKhoan()) && matkhau.equals(dk.getMatkhau()))
                    {
                        Intent intent = new Intent(MainActivity.this, Trangchu_quanliSV.class);
                        startActivity(intent);
                        if (cbluu.isChecked())
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan", taikhoan);
                            editor.putString("matkhau", matkhau);
                            editor.putBoolean("checked",true);
                            editor.commit();
                        }
                        else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.commit();
                        }
                        kt = true;
                    }

                }
                if (kt ==false)
                {
                    Toast.makeText(MainActivity.this, "Kiem tra lai tai khoan va mat khau", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void Anhxa()
    {
        btndangkki = (Button) findViewById(R.id.btn_dangki);
        btndangnhap = (Button) findViewById(R.id.btn_dangnhap);
        edttaikhoan = (EditText) findViewById(R.id.edt_username);
        edtmatkhau = (EditText) findViewById(R.id.edt_password);
        cbluu = (CheckBox) findViewById(R.id.cb_luuthongtin);
    }
}