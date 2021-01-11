package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysinhvien.Adapter.AdapterSinhvien;
import com.example.quanlysinhvien.Adapter.Nhapdiem_adapter_sv;
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Diemsv;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class Nhapdiemsv extends AppCompatActivity {
    Spinner splophoc, spmonhoc, spdiem;
    Button btnluu;
    ArrayList<String> lophocs;
    TextView tvnhapdiem, tvsstsv, tvtensv;
    EditText edtdiemgk, edtdiemCK;
    List<Sinhvien> sinhviens;
    List<Diemsv> diemsvs;
    String[] loaidiems;
    String[] monhocs;
    ListView lvdanhsachnhapdiem;
    Nhapdiem_adapter_sv nhapdiemadapter;
    DBManagerLophoc dbManagerLophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhapdiemsv);
        loaidiems = getResources().getStringArray(R.array.arrdiem);
        monhocs = getResources().getStringArray(R.array.arrmonhoc);
        splophoc = (Spinner) findViewById(R.id.sp_nhapdiemlop);
        spmonhoc = (Spinner) findViewById(R.id.sp_nhapdiemmon);
        lvdanhsachnhapdiem = (ListView) findViewById(R.id.lv_dsnhapdiem);
        btnluu = (Button) findViewById(R.id.btn_nhapdiemluu);

        lophocs = new ArrayList<>();
        dbManagerLophoc = new DBManagerLophoc(this);
       // lophocs = dbManagerLophoc.gettenlophoc();
        lophocs = dbManagerLophoc.hienthitenlophoc();
        sinhviens = dbManagerLophoc.getALLsinhvien();
        //diemsvs = dbManagerLophoc.getALLdiem();
        //nhapdiemadapter = new Nhapdiem_adapter_sv(this,R.layout.item_table_sv, sinhviens);
        setAdapter();
        ArrayAdapter<String> adapterlophoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lophocs);
        ArrayAdapter<String> adaptermonhoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monhocs);
        splophoc.setAdapter(adapterlophoc);
        spmonhoc.setAdapter(adaptermonhoc);

//        final String[] loaidiem1 = new String[1];
//        spdiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                loaidiem1[0] = loaidiems[position];
//                tvnhapdiem.setText(loaidiem1[0] +"");
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        final String[] monhoc1 = new String[1];
        spmonhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monhoc1[0] = monhocs[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final String[] timlophoc = new String[1];
        splophoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timlophoc[0] = lophocs.get(position);
                nhapdiemadapter.filter(timlophoc[0] +"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvsstsv = (TextView) findViewById(R.id.tv_sttsv);
                tvtensv = (TextView) findViewById(R.id.tv_ndtensv);

                String masv = tvsstsv.getText().toString();
                String tensv = tvtensv.getText().toString();
                edtdiemgk = (EditText) findViewById(R.id.edt_nhapdiemgk);
                edtdiemCK = (EditText) findViewById(R.id.edt_nhapdiemck);
                String diemGK = edtdiemgk.getText().toString();
                String diemCK =edtdiemCK.getText().toString();
                double diemGK1 = Double.parseDouble(diemGK);
                double diemCK1 = Double.parseDouble(diemCK);
                //(diemGK*20%+ diemCK*80%)/100%
                double tongdiem = ((diemGK1*0.2)+(diemCK1*0.8))/1;
                String xeploai;
                if (tongdiem <= 10 && tongdiem > 8.5)
                    xeploai ="Gioi";
                else if (tongdiem <= 8.5 && tongdiem >= 7)
                    xeploai = "Kha";
                else if (tongdiem < 7.0 && tongdiem >= 5.0)
                    xeploai = "trungbinh";
                else
                    xeploai = "Kem";
                Diemsv diemsv = new Diemsv(timlophoc[0], monhoc1[0], masv, tensv, diemGK1, diemCK1, tongdiem, xeploai);
                if (diemsv != null)
                {
                    dbManagerLophoc.adddiem(diemsv);
                    Toast.makeText(Nhapdiemsv.this, "Them diem thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setAdapter()
    {
        if (nhapdiemadapter == null)
        {
            nhapdiemadapter = new Nhapdiem_adapter_sv(this,R.layout.item_table_sv, sinhviens);
            lvdanhsachnhapdiem.setAdapter(nhapdiemadapter);

        }else {
            nhapdiemadapter.notifyDataSetChanged();
            lvdanhsachnhapdiem.setSelection(nhapdiemadapter.getCount()-1);
        }
    }
//    public void getAlllophoc()
//    {
//        sinhviens.clear();
//        sinhviens.addAll(dbManagerLophoc.getALLsinhvien());
//        nhapdiemadapter.notifyDataSetChanged();
//    }
}