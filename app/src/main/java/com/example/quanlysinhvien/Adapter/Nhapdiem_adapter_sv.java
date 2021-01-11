package com.example.quanlysinhvien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Model.Sinhvien;
import com.example.quanlysinhvien.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Nhapdiem_adapter_sv extends ArrayAdapter<Sinhvien> {
    private Context context;
    private int resource;
    private List<Sinhvien> arrdssinhvien;
    private ArrayList<Sinhvien> arrayList;

    public Nhapdiem_adapter_sv(@NonNull Context context, int resource, @NonNull List<Sinhvien> arrdssinhvien) {
        super(context, resource, arrdssinhvien);
        this.context = context;
        this.resource = resource;
        this.arrdssinhvien = arrdssinhvien;

        this.arrayList = new ArrayList<Sinhvien>();
        this.arrayList.addAll(arrdssinhvien);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table_sv, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvstt = convertView.findViewById(R.id.tv_sttsv);
            viewHolder.tvtensv = convertView.findViewById(R.id.tv_ndtensv);
            //viewHolder.edtnhapdiem = convertView.findViewById(R.id.edt_nhapdiem);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sinhvien sinhvien = arrdssinhvien.get(position);
        viewHolder.tvstt.setText(sinhvien.getMasv());
        viewHolder.tvtensv.setText(sinhvien.getTensv());
        return convertView;
    }
    public class ViewHolder
    {
        TextView tvstt;
        TextView tvtensv;
        EditText edtnhapdiem;
    }
    public void filter(String charText)
    {
        //dua chuoi nhap vao thanh chu thuong
        Boolean kt = false;
        charText = charText.toLowerCase(Locale.getDefault());
        arrdssinhvien.clear();
        //neu khong co ki tu tim thi cap nhat lai listview nhu ban dau
        if(charText.length() == 0)
        {
            arrdssinhvien.addAll(arrayList);
        }
        else{
            for (Sinhvien sv : arrayList)
            {
                //tim xem co ton tai chuoi minh con tim khong
                if (sv.getTenlop().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    arrdssinhvien.add(sv);
                    kt = true;
                }

            }
            if (kt == false)
            {
                Toast.makeText(context, "Không tìm thấy", Toast.LENGTH_SHORT).show();
            }
        }
        notifyDataSetChanged();
    }
}
