package com.example.quanlysinhvien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Model.Diemsv;
import com.example.quanlysinhvien.Model.Sinhvien;
import com.example.quanlysinhvien.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Xemdiem_adapter_sv extends ArrayAdapter<Diemsv> {
    private Context context;
    private int resource;
    private List<Diemsv> arrdiem;
    private ArrayList<Diemsv> arrayList;

    public Xemdiem_adapter_sv(@NonNull Context context, int resource, @NonNull List<Diemsv> arrdiem) {
        super(context, resource, arrdiem);
        this.context = context;
        this.resource = resource;
        this.arrdiem = arrdiem;

        this.arrayList = new ArrayList<Diemsv>();
        this.arrayList.addAll(arrdiem);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_xemdiem, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvstt = convertView.findViewById(R.id.tv_sttsv);
            viewHolder.tvtensv = convertView.findViewById(R.id.tv_ndtensv);
            viewHolder.tvdiemgk = convertView.findViewById(R.id.tv_diemgk);
            viewHolder.tvdiemck = convertView.findViewById(R.id.tv_diemck);
            viewHolder.tvtongdiem = convertView.findViewById(R.id.tv_tongdiem);
            viewHolder.tvxeploai = convertView.findViewById(R.id.tv_xeploai);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Diemsv diemsv = arrdiem.get(position);
        viewHolder.tvstt.setText(diemsv.getMaSV());
        viewHolder.tvtensv.setText(diemsv.getTenSV());
        viewHolder.tvdiemgk.setText(diemsv.getDiemGK()+"");
        viewHolder.tvdiemck.setText(diemsv.getDiemthi()+"");
        viewHolder.tvtongdiem.setText(diemsv.getTongdiem()+"");
        viewHolder.tvxeploai.setText(diemsv.getXeploai()+"");
        return convertView;
    }
    public class ViewHolder
    {
        TextView tvstt;
        TextView tvtensv;
        TextView tvdiemgk;
        TextView tvdiemck;
        TextView tvtongdiem;
        TextView tvxeploai;
    }
    public void filter(String charText)
    {
        //dua chuoi nhap vao thanh chu thuong
        Boolean kt = false;
        charText = charText.toLowerCase(Locale.getDefault());
        arrdiem.clear();
        //neu khong co ki tu tim thi cap nhat lai listview nhu ban dau
        if(charText.length() == 0)
        {
            arrdiem.addAll(arrayList);
        }
        else{
            for (Diemsv sv : arrayList)
            {
                //tim xem co ton tai chuoi minh con tim khong
                if (sv.getTenLop().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    arrdiem.add(sv);
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
