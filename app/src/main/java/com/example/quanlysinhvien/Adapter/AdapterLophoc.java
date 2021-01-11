package com.example.quanlysinhvien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterLophoc extends ArrayAdapter<Lophoc> {
    private Context context;
    private int resource;
    private List<Lophoc> arrdslophoc;
    private ArrayList<Lophoc> arrayList;
    public AdapterLophoc(@NonNull Context context, int resource, @NonNull List<Lophoc> arrdslophoc) {
        super(context, resource, arrdslophoc);
        this.context = context;
        this.resource = resource;
        this.arrdslophoc = arrdslophoc;

        this.arrayList = new ArrayList<Lophoc>();
        this.arrayList.addAll(arrdslophoc);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewholder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_lophoc, parent, false);
            viewholder = new viewHolder();
            viewholder.tvmalop = convertView.findViewById(R.id.tv_malop);
            viewholder.tvtenlop = convertView.findViewById(R.id.tv_tenlop);
            convertView.setTag(viewholder);
        }
        else{
            viewholder = (viewHolder) convertView.getTag();
        }
        Lophoc lophoc = arrdslophoc.get(position);
        viewholder.tvmalop.setText(lophoc.getMalop());
        viewholder.tvtenlop.setText(lophoc.getTenlop());
        return convertView;
    }

    public class viewHolder
    {
        TextView tvmalop;
        TextView tvtenlop;
    }
    public void filter(String charText)
    {
        //dua chuoi nhap vao thanh chu thuong
        Boolean kt = false;
        charText = charText.toLowerCase(Locale.getDefault());
        arrdslophoc.clear();
        //neu khong co ki tu tim thi cap nhat lai listview nhu ban dau
        if(charText.length() == 0)
        {
            arrdslophoc.addAll(arrayList);
        }
        else{
            for (Lophoc lp : arrayList)
            {
                //tim xem co ton tai chuoi minh con tim khong
                if (lp.getTenlop().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    arrdslophoc.add(lp);
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
