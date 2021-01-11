package com.example.quanlysinhvien;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Adapter.AdapterLophoc;
import com.example.quanlysinhvien.Database.DBManagerLophoc;
import com.example.quanlysinhvien.Model.Lophoc;

import java.util.List;

public class FragmentDanhSachLop extends Fragment {
    private ListView lv_dslop;
    private DBManagerLophoc dbManagerLophoc;
    private List<Lophoc> listlophoc;
    private AdapterLophoc adapterLophoc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view;
        view = inflater.inflate(R.layout.activity_danhsachlop,container,false);
        lv_dslop = (ListView) view.findViewById(R.id.lv_danhsachlop);

        return view;
    }

  /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        lv_dslop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getM
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }*/

}
