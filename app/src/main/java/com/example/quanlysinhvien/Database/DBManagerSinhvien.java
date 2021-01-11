package com.example.quanlysinhvien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class DBManagerSinhvien extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "QUANLISINHVIEN";
    final static String TABALE_NAME = "sinhvien";
    final static String ID = "id";
    final static String MASV = "masv";
    final static String TENSV = "tenSV";
    final static String NAMSINH = "namsinh";
    final static String DIACHI = "diachi";
    final static String GIOITINH = "gioitinh";
    final static String SDT = "sdt";
    final static String TENLOP = "tenlop";
    private Context context;
    public DBManagerSinhvien(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery= "CREATE TABLE "+ TABALE_NAME + " ( " +
                ID + " integer primary key, " +
                MASV + " text, " +
                TENSV + " text, " +
                NAMSINH + " text, " +
                DIACHI + " text, " +
                GIOITINH + " text, " +
                SDT + " text, " +
                TENLOP + " text)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addSinhvien(Sinhvien sv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MASV, sv.getMasv());
        contentValues.put(TENSV, sv.getTensv());
        contentValues.put(NAMSINH, sv.getNamsinh());
        contentValues.put(DIACHI, sv.getDiachi());
        contentValues.put(GIOITINH, sv.getGioitinh());
        contentValues.put(SDT, sv.getGioitinh());
        contentValues.put(TENLOP, sv.getTenlop());
        db.insert(TABALE_NAME, null, contentValues);
        db.close();
    }
    public List<Sinhvien> getadddklfd()
    {
        List<Sinhvien> dssinhvien = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectsinhvien = " SELECT * FROM "+TABALE_NAME;
        Cursor cursor = db.rawQuery(selectsinhvien, null);
        if (cursor.moveToFirst())
        {
            do {
                Sinhvien sinhvien = new Sinhvien();
                sinhvien.setMasv(cursor.getString(0));
                sinhvien.setTensv(cursor.getString(1));
                sinhvien.setNamsinh(cursor.getString(2));
                sinhvien.setDiachi(cursor.getString(3));
                sinhvien.setGioitinh(cursor.getString(4));
                sinhvien.setSodienthoai(cursor.getString(5));
                sinhvien.setTenlop(cursor.getString(6));
                dssinhvien.add(sinhvien);
            }while (cursor.moveToNext());
        }
        db.close();
        return dssinhvien;
    }
    public int suasinhvien(Sinhvien sinhvien)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MASV, sinhvien.getMasv());
        values.put(TENSV, sinhvien.getTensv());
        values.put(NAMSINH, sinhvien.getNamsinh());
        values.put(DIACHI, sinhvien.getDiachi());
        values.put(GIOITINH, sinhvien.getGioitinh());
        values.put(SDT, sinhvien.getSodienthoai());
        values.put(TENLOP, sinhvien.getTenlop());
        int result = db.update(TABALE_NAME,values,MASV+"=?",new String[]{sinhvien.getMasv()});
        return result;
    }
    public int deleteSinhvien(String masv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABALE_NAME, MASV+"=?",new String[]{masv});
    }
}
