package cn.zhuangh7.contact.datasev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Zhuangh7 on 2016/10/23.
 */
public class mDateBase extends SQLiteOpenHelper {
    public static final String CREATE_CONTACT = "create table Contact("
            +"ID integer primary key autoincrement,"
            +"name text,"
            +"addr text,"
            +"tel_m text,"
            +"tel_s text)";

    private Context mContext;

    public mDateBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT);
        Toast.makeText(mContext,"创建数据库成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(mContext,"升级数据库至版本2成功",Toast.LENGTH_LONG).show();
    }
}
