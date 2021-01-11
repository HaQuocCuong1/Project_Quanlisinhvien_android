package com.example.quanlysinhvien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quanlysinhvien.Adapter.AdapterLophoc;
import com.example.quanlysinhvien.Adapter.AdapterSinhvien;
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Database.DBManagerSinhvien;
import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class Quanlisinhvien extends AppCompatActivity {
    ListView lvdanhsachsv;
    List<Sinhvien> listsinhvien;
    DBManagerLophoc dbManagerSinhvien;
    AdapterSinhvien adapterSinhvien;
    SearchView svtheoten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlisinhvien);

        svtheoten = (SearchView) findViewById(R.id.sv_timtensv);
        dbManagerSinhvien = new DBManagerLophoc(this);
        anhxa();
        listsinhvien = dbManagerSinhvien.getALLsinhvien();
        setAdapter();
        showmenuquanlisinhvien();
        svtheoten.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSinhvien.filter(newText);
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themlop, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuthemlop:
                Intent intent = new Intent(Quanlisinhvien.this, Them_sinhvien.class);
                startActivityForResult(intent, 9999);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void anhxa()
    {
        lvdanhsachsv = (ListView) findViewById(R.id.lv_dssinhvien);
    }
    public void setAdapter()
    {
        if (adapterSinhvien == null)
        {
            adapterSinhvien = new AdapterSinhvien(this,R.layout.list_item_sinhvien, listsinhvien);
            lvdanhsachsv.setAdapter(adapterSinhvien);

        }else {
            adapterSinhvien.notifyDataSetChanged();
            lvdanhsachsv.setSelection(adapterSinhvien.getCount()-1);
        }
    }
    public void getAlllophoc()
    {
        listsinhvien.clear();
        listsinhvien.addAll(dbManagerSinhvien.getALLsinhvien());
        adapterSinhvien.notifyDataSetChanged();
    }
    public void showmenuquanlisinhvien()
    {
        lvdanhsachsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(Quanlisinhvien.this, lvdanhsachsv);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quanlisinhvien, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.menu_xoasv:
                                AlertDialog.Builder dialogxoa = new AlertDialog.Builder(Quanlisinhvien.this);
                                dialogxoa.setMessage("Ban co muon xoa khong");
                                final Sinhvien sinhvien = listsinhvien.get(position);
                                dialogxoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int result = dbManagerSinhvien.deleteSinhvien(sinhvien.getMasv());
                                        if (result > 0)
                                        {
                                            Toast.makeText(Quanlisinhvien.this, "Xoa thanh cong lop: "+sinhvien.getTenlop(), Toast.LENGTH_SHORT).show();
                                            getAlllophoc();
                                        }
                                    }
                                });
                                dialogxoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(Quanlisinhvien.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                AlertDialog dialog1 = dialogxoa.create();
                                dialog1.show();
                                break;
                            case R.id.menu_xttsv:
                                Intent intent = new Intent(Quanlisinhvien.this, Thongtinsinhvien.class);
                                Sinhvien sinhvien1 = listsinhvien.get(position);
                                String mssv = sinhvien1.getMasv();
                                String tensv = sinhvien1.getTensv();
                                String namsinh = sinhvien1.getNamsinh();
                                String diachi = sinhvien1.getDiachi();
                                String gioitinh = sinhvien1.getGioitinh();
                                String sdt = sinhvien1.getSodienthoai();
                                String tenlop = sinhvien1.getTenlop();
                                intent.putExtra("mssv", mssv);
                                intent.putExtra("tensv", tensv);
                                intent.putExtra("namsinh", namsinh);
                                intent.putExtra("diachi", diachi);
                                intent.putExtra("gioitinh", gioitinh);
                                intent.putExtra("sdt", sdt);
                                intent.putExtra("tenlop", tenlop);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9999 && resultCode == RESULT_OK)
        {
            String masv = data.getStringExtra("msv");
            String tensv = data.getStringExtra("tsv");
            String namsinh = data.getStringExtra("ns");
            String diachi = data.getStringExtra("dc");
            String gioitinh = data.getStringExtra("gt");
            String sdt = data.getStringExtra("sdt");
            String malop = data.getStringExtra("mlh");
            boolean kt=false;
            for (Sinhvien sv : listsinhvien)
            {
                if (masv.equalsIgnoreCase(sv.getMasv()))
                {
                    Toast.makeText(this, "Trùng mã sinh viên", Toast.LENGTH_SHORT).show();
                    kt=true;
                }
            }
            if (kt == false)
            {
                Sinhvien sinhvien = new Sinhvien(masv, tensv, namsinh, diachi, gioitinh, sdt,malop);
                if (sinhvien != null)
                {
                    dbManagerSinhvien.addSinhvien(sinhvien);
                }
                getAlllophoc();
                setAdapter();
            }
        }
    }
}