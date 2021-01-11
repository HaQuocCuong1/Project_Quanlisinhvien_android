package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

public class Thongtinsinhvien extends AppCompatActivity {
    TextView tvmassv, tvtensv, tvnamsinh, tvdichi, tvgioitinh, tvsodienthoai, tvtenlop;
    EditText edtid, edtmasv, edttensv, edtnamsv, edtdiachi, edtgioitinh ,edtsdt, edtlophoc;
    Button btnupdata, btnhuy, btnluusv;
    DBManagerLophoc dbManagerLophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinsinhvien);
        anhxa();
        dbManagerLophoc = new DBManagerLophoc(this);
        getAllsinhvien();
        btnupdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Thongtinsinhvien.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.dialog_suatt_sv);

                btnhuy = (Button) dialog.findViewById(R.id.btn_huysuasv);
                btnluusv = (Button) dialog.findViewById(R.id.btn_luusuasv);
                edtid = (EditText) dialog.findViewById(R.id.edt_suaIDSV);
                edtmasv = (EditText) dialog.findViewById(R.id.edt_suamasv);
                edttensv = (EditText) dialog.findViewById(R.id.edt_suatensv);
                edtnamsv = (EditText) dialog.findViewById(R.id.edt_suanamssv);
                edtdiachi = (EditText) dialog.findViewById(R.id.edt_suadiachisv);
                edtgioitinh = (EditText) dialog.findViewById(R.id.edt_suagioitinhsv);
                edtsdt = (EditText) dialog.findViewById(R.id.edt_suasdtsv);
                edtlophoc = (EditText) dialog.findViewById(R.id.edt_sualophocsv);


                String mssv = getIntent().getStringExtra("mssv");
                String tensv = getIntent().getStringExtra("tensv");
                String namsinh = getIntent().getStringExtra("namsinh");
                String diachi = getIntent().getStringExtra("diachi");
                String gioitinh = getIntent().getStringExtra("gioitinh");
                String sdt = getIntent().getStringExtra("sdt");
                String tenlop = getIntent().getStringExtra("tenlop");


                edtmasv.setText(mssv+"");
                edttensv.setText(tensv+"");
                edtnamsv.setText(namsinh+"");
                edtdiachi.setText(diachi+"");
                edtgioitinh.setText(gioitinh+"");
                edtsdt.setText(sdt+"");
                edtlophoc.setText(tenlop+"");
                edtmasv.setEnabled(false);
                btnluusv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtmasv = (EditText) dialog.findViewById(R.id.edt_suamasv);
                        edttensv = (EditText) dialog.findViewById(R.id.edt_suatensv);
                        edtnamsv = (EditText) dialog.findViewById(R.id.edt_suanamssv);
                        edtdiachi = (EditText) dialog.findViewById(R.id.edt_suadiachisv);
                        edtgioitinh = (EditText) dialog.findViewById(R.id.edt_suagioitinhsv);
                        edtsdt = (EditText) dialog.findViewById(R.id.edt_suasdtsv);
                        edtlophoc = (EditText) dialog.findViewById(R.id.edt_sualophocsv);

                        Sinhvien sinhvien = new Sinhvien();
                        sinhvien.setMasv(String.valueOf(edtmasv.getText().toString()));
                        sinhvien.setTensv(String.valueOf(edttensv.getText().toString()));
                        sinhvien.setNamsinh(edtnamsv.getText()+"");
                        sinhvien.setDiachi(edtdiachi.getText()+"");
                        sinhvien.setGioitinh(edtgioitinh.getText()+"");
                        sinhvien.setSodienthoai(edtsdt.getText()+"");
                        sinhvien.setTenlop(edtlophoc.getText()+"");
                        int result = dbManagerLophoc.suattsinhvien(sinhvien);
                        if (result > 0)
                        {
                            Toast.makeText(Thongtinsinhvien.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                            getAllsinhvien();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Thongtinsinhvien.this, "Sua that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
    public void getAllsinhvien()
    {
//        listlophoc.clear();
//        listlophoc.addAll(dbManagerLophoc.getALLlophoc());
//        adapterLophoc.notifyDataSetChanged();
        String mssv = getIntent().getStringExtra("mssv");
        String tensv = getIntent().getStringExtra("tensv");
        String namsinh = getIntent().getStringExtra("namsinh");
        String diachi = getIntent().getStringExtra("diachi");
        String gioitinh = getIntent().getStringExtra("gioitinh");
        String sdt = getIntent().getStringExtra("sdt");
        String tenlop = getIntent().getStringExtra("tenlop");
        tvmassv.setText(mssv+"");
        tvtensv.setText(tensv+"");
        tvnamsinh.setText(namsinh+"");
        tvdichi.setText(diachi+"");
        tvgioitinh.setText(gioitinh+"");
        tvsodienthoai.setText(sdt+"");
        tvtenlop.setText(tenlop+"");
    }
    public void anhxa()
    {
        tvmassv = (TextView) findViewById(R.id.tv_ttmssv);
        tvtensv = (TextView) findViewById(R.id.tv_tttensv);
        tvnamsinh = (TextView) findViewById(R.id.tv_ttnamsinh);
        tvdichi = (TextView) findViewById(R.id.tv_ttdiachi);
        tvgioitinh = (TextView) findViewById(R.id.tv_ttgioitinh);
        tvsodienthoai = (TextView) findViewById(R.id.tv_ttsdt);
        tvtenlop = (TextView) findViewById(R.id.tv_tttenlop);
        btnupdata = (Button) findViewById(R.id.btn_updatasv);
    }
}