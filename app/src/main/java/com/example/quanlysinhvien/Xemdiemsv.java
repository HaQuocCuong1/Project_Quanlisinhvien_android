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

import com.example.quanlysinhvien.Adapter.Nhapdiem_adapter_sv;
import com.example.quanlysinhvien.Adapter.Xemdiem_adapter_sv;
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Diemsv;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class Xemdiemsv extends AppCompatActivity {
    Spinner splophoc, spmonhoc, spdiem;
    Button btnxem;
    ArrayList<String> lophocs;
    TextView tvnhapdiem, tvsstsv, tvtensv;
    EditText edtdiem;
    List<Diemsv> diemsvs;
    String[] loaidiems;
    String[] monhocs;
    ListView lvdanhsachxemdiem;
    Xemdiem_adapter_sv xemdiemadapter;
    DBManagerLophoc dbManagerLophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdiemsv);
        loaidiems = getResources().getStringArray(R.array.arrdiem);
        monhocs = getResources().getStringArray(R.array.arrmonhoc);
        splophoc = (Spinner) findViewById(R.id.sp_nhapdiemlop);
        spmonhoc = (Spinner) findViewById(R.id.sp_nhapdiemmon);
        lvdanhsachxemdiem = (ListView) findViewById(R.id.lv_dsxemdiem);

        lophocs = new ArrayList<>();
        dbManagerLophoc = new DBManagerLophoc(this);
        // lophocs = dbManagerLophoc.gettenlophoc();
        lophocs = dbManagerLophoc.hienthitenlophoc();
        diemsvs = dbManagerLophoc.getALLdiem();

//        xemdiemadapter = new Xemdiem_adapter_sv(this,R.layout.list_item_xemdiem, diemsvs);
//        lvdanhsachxemdiem.setAdapter(xemdiemadapter);
        setAdapter();
        ArrayAdapter<String> adapterlophoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lophocs);
        ArrayAdapter<String> adaptermonhoc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monhocs);
        splophoc.setAdapter(adapterlophoc);
        spmonhoc.setAdapter(adaptermonhoc);

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
                btnxem = (Button) findViewById(R.id.btn_xemdiem);
//                btnxem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                        xemdiemadapter.filter(timlophoc[0] +"");
            //        }
           //     });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        btnxem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvnhapdiem.setText(loaidiem1[0] +"");
//            }
//        });
    }
    public void setAdapter()
    {
        if (xemdiemadapter == null)
        {
            xemdiemadapter = new Xemdiem_adapter_sv(this,R.layout.list_item_xemdiem, diemsvs);
            lvdanhsachxemdiem.setAdapter(xemdiemadapter);

        }else {
            xemdiemadapter.notifyDataSetChanged();
            lvdanhsachxemdiem.setSelection(xemdiemadapter.getCount()-1);
        }
    }
    public void getAlllophoc()
    {
        diemsvs.clear();
        diemsvs.addAll(dbManagerLophoc.getALLdiem());
        xemdiemadapter.notifyDataSetChanged();
    }
}