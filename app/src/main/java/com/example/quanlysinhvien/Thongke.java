package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlysinhvien.Adapter.AdapterSinhvien;
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Diemsv;
import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class Thongke extends AppCompatActivity {
    TextView tvtongsv, tvtonglop, tvxlgioi, tvxlkha, tvxltrungbinh, tvxlkem;
    List<Sinhvien> sinhviens;
    List<Lophoc> lophocs;
    List<Diemsv> diemsvs;
    AdapterSinhvien adapterSinhvien;
    DBManagerLophoc dbManagerLophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        tvtongsv = (TextView) findViewById(R.id.tv_tongsv);
        tvtonglop = (TextView) findViewById(R.id.tv_tonglop);
        tvxlgioi = (TextView) findViewById(R.id.tv_xlgioi);
        tvxlkha = (TextView) findViewById(R.id.tv_xlkha);
        tvxltrungbinh = (TextView) findViewById(R.id.tv_xltrungbinh);
        tvxlkem = (TextView) findViewById(R.id.tv_xlkem);

        dbManagerLophoc = new DBManagerLophoc(this);
        sinhviens = dbManagerLophoc.getALLsinhvien();
        int countsv=0;
        for (Sinhvien sinhvien : sinhviens)
        {
            countsv++;
        }
        tvtongsv.setText(String.valueOf(countsv));

        int countlh=0;
        lophocs = dbManagerLophoc.getALLlophoc();
        for (Lophoc lophoc : lophocs)
        {
            countlh++;
        }
        tvtonglop.setText(String.valueOf(countlh));

        int countgioi=0;
        int countkha=0;
        int counttrungbinh=0;
        int countkem=0;
        diemsvs = dbManagerLophoc.getALLdiem();
        int i=0;
        for (Diemsv diemsv : diemsvs)
        {
            if (diemsv.getXeploai().equals("Gioi"))
                countgioi++;
            if (diemsv.getXeploai().equals("Kha"))
                countkha++;
            if (diemsv.getXeploai().equals("trungbinh"))
                counttrungbinh++;
            if (diemsv.getXeploai().equals("Kem"))
                countkem++;
        }
        tvxlgioi.setText(String.valueOf(countgioi));
        tvxlkha.setText(String.valueOf(countkha));
        tvxltrungbinh.setText(String.valueOf(counttrungbinh));
        tvxlkem.setText(String.valueOf(countkem));
    }
}