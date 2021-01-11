package com.example.quanlysinhvien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlysinhvien.Model.Dangki;
import com.example.quanlysinhvien.Model.Diemsv;
import com.example.quanlysinhvien.Model.Lophoc;
import com.example.quanlysinhvien.Model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class DBManagerLophoc extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "QUANLISINHVIEN";
    final static String TABALE_NAME = "lophoc";
    final static String ID = "id";
    final static String MALOP = "malop";
    final static String TENLOP = "tenlop";
    private Context context;

    final static String TABALE_SINHVIEN = "sinhvien";
    final static String IDSV = "id";
    final static String MASV = "masv";
    final static String TENSV = "tenSV";
    final static String NAMSINH = "namsinh";
    final static String DIACHI = "diachi";
    final static String GIOITINH = "gioitinh";
    final static String SDT = "sdt";

    final static String TABALE_DIEM = "diemsv";
    final static String MONHOC = "monhoc";
    final static String LOAIDIEM = "loaidiem";
    final static String DIEMGK = "diemgk";
    final static String DIEMTHI = "diemthi";
    final static String DIEMTONG = "diemtong";
    final static String XEPLOAI = "xeploai";

    final static String TABLE_DANGKI = "dangki";
    final static String TAIKHOAN = "taikhoan";
    final static String MATKHAU = "matkhau";
    final static String NHAPLAIMATKHAU = "nhaplaimatkhau";
    final static String EMAIL = "email";
    public DBManagerLophoc(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery= "CREATE TABLE "+ TABALE_NAME + " ( " +
                ID + " integer, " +
                MALOP + " text, " +
                TENLOP + " text primary key)";
        String sqlQuerysv= "CREATE TABLE "+ TABALE_SINHVIEN + " ( " +
                IDSV + " integer primary key, " +
                MASV + " text, " +
                TENSV + " text, " +
                NAMSINH + " text, " +
                DIACHI + " text, " +
                GIOITINH + " text, " +
                SDT + " text, " +
                TENLOP+ " text references "+TABALE_NAME+"("+TENLOP+"))";
        String sqlQuerydiem = "CREATE TABLE "+ TABALE_DIEM + " ( " +
                TENLOP+ " text references "+TABALE_NAME+"("+TENLOP+")," +
                MONHOC + " text, " +
                LOAIDIEM + " text, " +
                MASV+ " text references "+TABALE_SINHVIEN+"("+MASV+")," +
                TENSV+ " text references "+TABALE_SINHVIEN+"("+TENSV+")," +
                DIEMGK + " text, "+
                DIEMTHI + " text, "+
                DIEMTONG + " text, "+
                XEPLOAI + " text )";
        String sqlQuerydangki= "CREATE TABLE "+ TABLE_DANGKI + " ( " +
                TAIKHOAN + " text primary key, " +
                MATKHAU + " text, " +
                NHAPLAIMATKHAU + " text, " +
                EMAIL + " text )";
        sqLiteDatabase.execSQL(sqlQuery);
        sqLiteDatabase.execSQL(sqlQuerysv);
        sqLiteDatabase.execSQL(sqlQuerydiem);
        sqLiteDatabase.execSQL(sqlQuerydangki);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addLophoc(Lophoc lh)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MALOP, lh.getMalop());
        contentValues.put(TENLOP, lh.getTenlop());
        db.insert(TABALE_NAME, null, contentValues);
        db.close();
    }
    public List<Lophoc> getALLlophoc()
    {
        List<Lophoc> dslophoc = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectlophoc = " SELECT * FROM "+TABALE_NAME;
        Cursor cursor = db.rawQuery(selectlophoc, null);
        if (cursor.moveToFirst())
        {
            do {
                Lophoc lophoc = new Lophoc();
                lophoc.setId(cursor.getInt(0));
                lophoc.setMalop(cursor.getString(1));
                lophoc.setTenlop(cursor.getString(2));
                dslophoc.add(lophoc);
            }while (cursor.moveToNext());
        }
        db.close();
        return dslophoc;
    }
    public ArrayList<String> hienthitenlophoc()
    {
        ArrayList<String> dstenlophoc = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String querytenlop = " SELECT "+TENLOP+" FROM "+TABALE_NAME;
        Cursor cursor = db.rawQuery(querytenlop, null);
        try
        {
            if (cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    dstenlophoc.add(cursor.getString(0));
                }
            }
            db.close();
            return dstenlophoc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return  dstenlophoc;
    }
    public ArrayList<String> gettenlophoc()
    {
        ArrayList<String> dslophoc = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecttenlop = " SELECT "+MALOP+","+TENLOP+" FROM "+TABALE_NAME;
        Cursor cursor = db.rawQuery(selecttenlop, null);
        try
        {
            if (cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                   // dslophoc.add(cursor.getString(0)+"-"+cursor.getString(1));
                    dslophoc.add(cursor.getString(1));
                }
            }
            db.close();
            return dslophoc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return  dslophoc;
    }
    public int sualophoc(Lophoc lophoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MALOP, lophoc.getMalop());
        values.put(TENLOP, lophoc.getTenlop());
        int result = db.update(TABALE_NAME,values,MALOP+"=?",new String[]{lophoc.getMalop()});
        return result;
    }
    public int deletelophoc(String mlophoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABALE_NAME, MALOP+"=?",new String[]{mlophoc});
    }

    //SINH VIEN
    public void addSinhvien(Sinhvien sv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MASV, sv.getMasv());
        contentValues.put(TENSV, sv.getTensv());
        contentValues.put(NAMSINH, sv.getNamsinh());
        contentValues.put(DIACHI, sv.getDiachi());
        contentValues.put(GIOITINH, sv.getGioitinh());
        contentValues.put(SDT, sv.getSodienthoai());
        contentValues.put(TENLOP, sv.getTenlop());
        db.insert(TABALE_SINHVIEN, null, contentValues);
        db.close();
    }
    public List<Sinhvien> getALLsinhvien()
    {
        List<Sinhvien> dssinhvien = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectsinhvien = " SELECT * FROM "+TABALE_SINHVIEN;
        Cursor cursor = db.rawQuery(selectsinhvien, null);
        if (cursor.moveToFirst())
        {
            do {
                Sinhvien sinhvien = new Sinhvien();

                sinhvien.setMasv(cursor.getString(1));
                sinhvien.setTensv(cursor.getString(2));
                sinhvien.setNamsinh(cursor.getString(3));
                sinhvien.setDiachi(cursor.getString(4));
                sinhvien.setGioitinh(cursor.getString(5));
                sinhvien.setSodienthoai(cursor.getString(6));
                sinhvien.setTenlop(cursor.getString(7));
                dssinhvien.add(sinhvien);
            }while (cursor.moveToNext());
        }
        db.close();
        return dssinhvien;
    }
    public int suattsinhvien(Sinhvien sinhvien)
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
        int result = db.update(TABALE_SINHVIEN,values,MASV+"=?",new String[]{sinhvien.getMasv()});
        return result;
    }
    public int deleteSinhvien(String masv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABALE_SINHVIEN, MASV+"=?",new String[]{masv});
    }

    //NHAP DIEM
    public void adddiem(Diemsv dsv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TENLOP, dsv.getTenLop());
        contentValues.put(MONHOC, dsv.getMonhoc());
        contentValues.put(MASV, dsv.getMaSV());
        contentValues.put(TENSV, dsv.getTenSV());
        contentValues.put(DIEMGK, dsv.getDiemGK());
        contentValues.put(DIEMTHI, dsv.getDiemthi());
        contentValues.put(DIEMTONG, dsv.getTongdiem());
        contentValues.put(XEPLOAI, dsv.getXeploai());
        db.insert(TABALE_DIEM, null, contentValues);
        db.close();
    }
    public List<Diemsv> getALLdiem()
    {
        List<Diemsv> dslophoc = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectlophoc = " SELECT * FROM "+TABALE_DIEM;
        Cursor cursor = db.rawQuery(selectlophoc, null);
        if (cursor.moveToFirst())
        {
            do {
                Diemsv diem = new Diemsv();
                diem.setTenLop(cursor.getString(0));
                diem.setMonhoc(cursor.getString(1));
                diem.setMaSV(cursor.getString(3));
                diem.setTenSV(cursor.getString(4));
                diem.setDiemGK(cursor.getDouble(5));
                diem.setDiemthi(cursor.getDouble(6));
                diem.setTongdiem(cursor.getDouble(7));
                diem.setXeploai(cursor.getString(8));
                dslophoc.add(diem);
            }while (cursor.moveToNext());
        }
        db.close();
        return dslophoc;
    }
    public ArrayList<String> getsvxeploaigioi()
    {
        ArrayList<String> dslophoc = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecttenlop = " SELECT "+XEPLOAI+" FROM "+TABALE_DIEM+" WHERE "+XEPLOAI+ " = kem";
        Cursor cursor = db.rawQuery(selecttenlop, null);
        try
        {
            if (cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    dslophoc.add(cursor.getString(0));
                }
            }
            db.close();
            return dslophoc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
        return  dslophoc;
    }
    //DANG KI
    public int addtaikhoan(Dangki dk)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAIKHOAN, dk.getTaiKhoan());
        contentValues.put(MATKHAU, dk.getMatkhau());
        contentValues.put(NHAPLAIMATKHAU, dk.getNhaplaimk());
        contentValues.put(EMAIL, dk.getEmail());
        int reuslt = (int) db.insert(TABLE_DANGKI, null, contentValues);
        db.close();
        return reuslt;
    }
    public List<Dangki> getALLtaikhoan()
    {
        List<Dangki> dslophoc = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectdangki = " SELECT * FROM "+TABLE_DANGKI;
        Cursor cursor = db.rawQuery(selectdangki, null);
        if (cursor.moveToFirst())
        {
            do {
                Dangki dangki = new Dangki();
                dangki.setTaiKhoan(cursor.getString(0));
                dangki.setMatkhau(cursor.getString(1));
                dangki.setNhaplaimk(cursor.getString(3));
               // dangki.setEmail(cursor.getString(4));
                dslophoc.add(dangki);
            }while (cursor.moveToNext());
        }
        db.close();
        return dslophoc;
    }
}
