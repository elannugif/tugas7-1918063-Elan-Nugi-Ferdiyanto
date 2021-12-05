package com.example.a1918063_tugas7_elannugiferdiyanto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kaoskaki";
    private static final String tb_kaoskaki = "tb_kaoskaki";
    private static final String getTb_kaoskaki_id = "id";
    private static final String getTb_kaoskaki_nama = "nama";
    private static final String getTb_kaoskaki_harga = "harga";
    private static final String CREATE_TABLE_KAOSKAKI = "CREATE TABLE " + tb_kaoskaki +"("
            + getTb_kaoskaki_id + " INTEGER PRIMARY KEY ,"
            + getTb_kaoskaki_nama + " TEXT ,"
            + getTb_kaoskaki_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAOSKAKI);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void Createkaoskaki(kaoskaki data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(getTb_kaoskaki_id, data.get_id());
        values.put(getTb_kaoskaki_nama, data.get_nama_produk());
        values.put(getTb_kaoskaki_harga, data.get_harga());
        db.insert(tb_kaoskaki, null, values);
        db.close();
    }
    public List<kaoskaki> Readkaoskaki() {
        List<kaoskaki> listMhs = new ArrayList<kaoskaki>();
        String selectQuery = "SELECT * FROM " + tb_kaoskaki;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                kaoskaki data = new kaoskaki();
                data.set_id(cursor.getString(0));
                data.set_nama_produk(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int Updatekaoskaki (kaoskaki data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(getTb_kaoskaki_nama, data.get_nama_produk());
        values.put(getTb_kaoskaki_harga, data.get_harga());
        return db.update(tb_kaoskaki, values, getTb_kaoskaki_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void Deletekaoskaki(kaoskaki data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_kaoskaki,getTb_kaoskaki_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
