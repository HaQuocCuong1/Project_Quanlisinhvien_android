package com.example.quanlysinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
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
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Lophoc;

import java.util.List;

public class Quanlidanhsach extends AppCompatActivity {
    private ListView lvdslophoc;
    private DBManagerLophoc dbManagerLophoc;
    private AdapterLophoc adapterLophoc;
    private List<Lophoc> listlophoc;
    private Button btnluulh, btnhuylh;
    private Button btnsualuulh, btnsuahuylh;
    private EditText edtsuatenlh, edtsuaidlh, edtsuamalh;
    private EditText edtmalop, edttenlop;
    private SearchView svtimllop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachlop);

        dbManagerLophoc = new DBManagerLophoc(this);
        anhxa();
        listlophoc = dbManagerLophoc.getALLlophoc();
        setAdapter();
        showmenuquanlilop();
        svtimllop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterLophoc.filter(newText);
                return false;
            }
        });
    }
    public void anhxa()
    {
        lvdslophoc = (ListView) findViewById(R.id.lv_danhsachlop);
        svtimllop = (SearchView) findViewById(R.id.sv_timlop);
    }
    public void setAdapter()
    {
        if (adapterLophoc == null)
        {
            adapterLophoc = new AdapterLophoc(this,R.layout.list_item_lophoc,listlophoc);
            lvdslophoc.setAdapter(adapterLophoc);

        }else {
            adapterLophoc.notifyDataSetChanged();
            lvdslophoc.setSelection(adapterLophoc.getCount()-1);
        }
    }


    //Hien thi menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themlop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void showmenuquanlilop()
    {
        lvdslophoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(Quanlidanhsach.this, lvdslophoc);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quanlilophoc, popupMenu.getMenu());
                //Sua thong tin lop hoc

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.menu_xoalop:
                                AlertDialog.Builder dialogxoa = new AlertDialog.Builder(Quanlidanhsach.this);
                                dialogxoa.setMessage("Ban co muon xoa khong");
                                final Lophoc lophoc = listlophoc.get(position);
                                dialogxoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int result = dbManagerLophoc.deletelophoc(lophoc.getMalop());
                                        if (result > 0)
                                        {
                                            Toast.makeText(Quanlidanhsach.this, "Xoa thanh cong lop: "+lophoc.getTenlop(), Toast.LENGTH_SHORT).show();
                                            getAlllophoc();
                                        }
                                    }
                                });
                                dialogxoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(Quanlidanhsach.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                AlertDialog dialog1 = dialogxoa.create();
                                dialog1.show();
                                break;
                            case R.id.menu_sualop:

                                final Dialog dialog = new Dialog(Quanlidanhsach.this);
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.setContentView(R.layout.dialog_sua_lophoc);

                                btnsualuulh = (Button) dialog.findViewById(R.id.btn_luusualophoc);
                                btnsuahuylh = (Button) dialog.findViewById(R.id.btn_huysualophoc);
                                edtsuatenlh = (EditText) dialog.findViewById(R.id.edt_suatenlh);
                                edtsuaidlh = (EditText) dialog.findViewById(R.id.edt_suaIDlh);
                                edtsuamalh = (EditText) dialog.findViewById(R.id.edt_suamalh);


                                Lophoc lophoc1 = listlophoc.get(position);
                                edtsuaidlh.setText(String.valueOf(lophoc1.getId()));
                                edtsuamalh.setText(lophoc1.getMalop());
                                edtsuatenlh.setText(lophoc1.getTenlop());
                                edtsuaidlh.setEnabled(false);
                                edtsuamalh.setEnabled(false);
                                //Thao tac xu li sua lop hoc
                                btnsualuulh.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Lophoc lophoc = new Lophoc();
                                        lophoc.setId(edtsuaidlh.getId());
                                        edtmalop = (EditText) dialog.findViewById(R.id.edt_suamalh);
                                        edtsuatenlh = (EditText) dialog.findViewById(R.id.edt_suatenlh);
                                        lophoc.setMalop(String.valueOf(edtmalop.getText().toString()));
                                        lophoc.setTenlop(edtsuatenlh.getText()+"");
                                        int result = dbManagerLophoc.sualophoc(lophoc);
                                        if (result > 0)
                                        {
                                            Toast.makeText(Quanlidanhsach.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                                            getAlllophoc();
                                            dialog.dismiss();
                                        }else {
                                            Toast.makeText(Quanlidanhsach.this, "Sua that bai", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                btnsuahuylh.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuthemlop:
                Dialogthemlop();
                Toast.makeText(this, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
                showmenuquanlilop();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void sualophoc()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_sua_lophoc);
        btnsualuulh = (Button) dialog.findViewById(R.id.btn_luusualophoc);
        btnsuahuylh = (Button) dialog.findViewById(R.id.btn_huysualophoc);
        edtsuatenlh = (EditText) dialog.findViewById(R.id.edt_suatenlh);
        edtsuaidlh = (EditText) dialog.findViewById(R.id.edt_suaIDlh);
        edtsuamalh = (EditText) dialog.findViewById(R.id.edt_suamalh);


        btnsualuulh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lophoc lophoc = new Lophoc();
                lophoc.setId(edtsuaidlh.getId());
                lophoc.setMalop(edtmalop.getText().toString());
                lophoc.setTenlop(edtsuatenlh.getText().toString());
                int result = dbManagerLophoc.sualophoc(lophoc);
                if (result > 0)
                {
                    Toast.makeText(Quanlidanhsach.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    getAlllophoc();
                }else {
                    Toast.makeText(Quanlidanhsach.this, "Sua that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsuahuylh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void nhapdltextlh(){

               /* Lophoc lophoc = listlophoc.get(position);
                edtsuaidlh.setText(String.valueOf(lophoc.getId()));
                edtsuamalh.setText(lophoc.getMalop());
                edtsuatenlh.setText(lophoc.getTenlop());
                edtsuaidlh.setEnabled(false);
                edtsuamalh.setEnabled(false);*/

    }
    public void Dialogthemlop()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_themlophoc);
        btnluulh = (Button) dialog.findViewById(R.id.btn_luuthemlop);
        btnhuylh = (Button) dialog.findViewById(R.id.btn_huythemlop);
        edtmalop = (EditText) dialog.findViewById(R.id.edt_themmallop);
        edttenlop = (EditText) dialog.findViewById(R.id.edt_themtenlop);
        btnluulh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lophoc lophoc = createLophoc();
                if (lophoc != null)
                {
                    dbManagerLophoc.addLophoc(lophoc);
                }
                getAlllophoc();
                setAdapter();
                dialog.dismiss();
            }
        });
        btnhuylh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        lvdslophoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
        dialog.show();
    }
    public Lophoc createLophoc()
    {
        String malop = edtmalop.getText().toString();
        String tenlop = edttenlop.getText().toString();
        Lophoc lh = new Lophoc(malop, tenlop);
        return lh;
    }
    public void getAlllophoc()
    {
        listlophoc.clear();
        listlophoc.addAll(dbManagerLophoc.getALLlophoc());
        adapterLophoc.notifyDataSetChanged();
    }
}