package cn.zhuangh7.contact.datasev;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.zhuangh7.contact.contacter;

/**
 * Created by Zhuangh7 on 2016/10/25.
 */
public class dataBase {
    private SQLiteDatabase mDateBase;
    private mDateBase dbHelper;
    private Context context;
    private Boolean isInit = false;
    private int dbSize;
    public dataBase(Context context) {
        this.context = context;
        init();
    }

    public void close(){
        dbHelper.close();
    }

    private void init(){
        dbHelper = new mDateBase(context,"CONTACT.db",null,1);
        mDateBase = dbHelper.getWritableDatabase();
        isInit = true;
        dbSize = 0;
    }

    public ArrayList<contacter> getContacts(int id_b, int id_e){
        ArrayList<contacter> result = new ArrayList<>(id_e-id_b);
        if(isInit){
            try {
                Cursor cursor = mDateBase.rawQuery("select ID,name,addr,tel_m,tel_s from Contact where ID >=? and ID <=?", new String[]{"" + id_b, "" + id_e});
                while (cursor.moveToNext()) {
                    int ID = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String addr = cursor.getString(2);
                    String tel_m = cursor.getString(3);
                    String tel_s = cursor.getString(4);
                    result.add(contacter.newContact(ID, name, addr, tel_m, tel_s));
                }
                cursor.close();
                return result;
            }catch (Exception e){
                Cursor cursor = mDateBase.rawQuery("select ID,name,addr,tel_m,tel_s from Contact where ID >= ?",new String[]{""+id_b});
                while (cursor.moveToNext()) {
                    int ID = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String addr = cursor.getString(2);
                    String tel_m = cursor.getString(3);
                    String tel_s = cursor.getString(4);
                    result.add(contacter.newContact(ID, name, addr, tel_m, tel_s));
                }
                cursor.close();
                return result;
            }
        }
        return null;
    }

    public int newContact(String name,String addr,String tel_m,String tel_s){
        //TODO 返回值 id(if successfully create) -1(if datebase init not complete yet or there is a same object already exist in this db) -2(if attr is illegal)
        if(isInit){
            if (name.equals("")) {
                return -2;
            }
            if(addr==null){
                addr="";
            }
            if(tel_m==null){
                tel_m="";
            }
            if(tel_s==null){
                tel_s="";
            }
            Cursor cursore = mDateBase.rawQuery("select ID from Contact where name = ? and addr = ? and tel_m = ? and tel_s = ?", new String[]{name,addr,tel_m,tel_s});
            if(cursore.getCount()!=0){
                return -1;
            }
            cursore.close();
            mDateBase.execSQL("insert into Contact(name,addr,tel_m,tel_s)values(?,?,?,?)",new Object[]{name,addr,tel_m,tel_s});
            Cursor cursor = mDateBase.rawQuery("select ID from Contact where name = ? and addr = ? and tel_m = ? and tel_s = ?", new String[]{name,addr,tel_m,tel_s});
            while(cursor.moveToNext()){
                int ID = cursor.getInt(0);
                cursor.close();
                return ID;
            }
        }
        return -1;
    }
    public boolean editContact(int id,String name,String addr,String tel_m,String tel_s) {
        //TODO rerturn success if work out
        if(isInit){
            if (name.equals("")) {
                return false;
            }
            if(addr==null){
                addr="";
            }
            if(tel_m==null){
                tel_m="";
            }
            if(tel_s==null){
                tel_s="";
            }
            mDateBase.execSQL("update Contact set name = ?, addr = ?, tel_m = ?,tel_s = ? where ID = ?",new Object[]{name,addr,tel_m,tel_s,id});
            return true;
        }
        return false;

    }

    public boolean deleteContact(int id){
        //TODO 懒得写了
        if(isInit){
            mDateBase.execSQL("delete from Contact where ID = ?", new Object[]{id});
            return true;
        }
        return false;
    }



    public void deleteData(){
        mDateBase.execSQL("drop table if exists Contact");
    }
    //TODO DON'T USE



}
