package cn.zhuangh7.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.zhuangh7.contact.datasev.dataBase;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/22.HUAWEI_SB
 */

public class UpdateActivity extends MyActivity{
    private boolean update = false;
    private dataBase db;
    private contacter c;
    private int position;
    private EditText name;
    private EditText addr;
    private EditText tel_1;
    private EditText tel_2;
    private Button button;
    private TextView title;
    private Button delete;
    private boolean ifDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);
        initDataBase();
        getDataFromIntent();//必须先获取数据
        initViews();
    }

    private void initViews(){
        button = (Button)findViewById(R.id.update_accept);
        delete = (Button)findViewById(R.id.update_delete);
        name = (EditText)findViewById(R.id.update_name);
        addr = (EditText)findViewById(R.id.update_addr);
        tel_1 = (EditText)findViewById(R.id.update_tel_1);
        tel_2 = (EditText)findViewById(R.id.update_tel_2);
        title = (TextView)findViewById(R.id.update_title);
        if(update){
            title.setText(R.string.update_title_update);
            name.setText(c.getName());
            addr.setText(c.getAddr());
            tel_1.setText(c.getTel_m());
            tel_2.setText(c.getTel_s());
        }else{
            title.setText(R.string.update_title_new);
            name.setHint("联系人姓名");
            name.setText(null);
            addr.setText(null);
            addr.setHint("联系人地址");
            tel_1.setHint("主要联系电话");
            tel_2.setHint("备选联系电话");
            delete.setVisibility(View.GONE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Onclick();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletethis();
            }
        });
    }
    private void deletethis(){
        boolean result = db.deleteContact(db.getIdByName(c.getName()));
        if(result){
            Toast.makeText(this,"Delete Successful",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"不可预知的错误发生了",Toast.LENGTH_LONG).show();
        }
        ifDelete = true;
        finish();
        overridePendingTransition(0, 0);
    }

    private void initDataBase(){
        db = new dataBase(this);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        boolean ifUpdate = intent.getBooleanExtra("ifUpdate",false);
        if(ifUpdate) {
            c = (contacter) intent.getSerializableExtra("Contacter");
            position = intent.getIntExtra("position", -1);
            update = true;
        }else{
            c = contacter.newContact("","","","");//设置C为null这样view判断的时候好判断，在view那里再新建吧(后来发现并不用,update就可以了
            position = -1;
            //delete.setVisibility(View.GONE);
        }

    }

    private void getDataFromViews(){
        c.setName(name.getText().toString());
        c.setAddr(addr.getText().toString());
        c.setTel_m(tel_1.getText().toString());
        c.setTel_s(tel_2.getText().toString());
    }


    private void Onclick(){
        //TODO
        getDataFromViews();
        int id = db.newContact(c.getName(),c.getAddr(),c.getTel_m(),c.getTel_s());
        if(id==-2){
            Toast.makeText(this,"参数设置有误，请检查姓名是否完整",Toast.LENGTH_LONG).show();
        }else if(id == -1 && update){
            //TODO means update
            id = db.getIdByName(c.getName());
            if(db.editContact(id,c.getName(),c.getAddr(),c.getTel_m(),c.getTel_s())){
                Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Something error when update datebase",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"新建联系人成功"+id+update,Toast.LENGTH_SHORT).show();
        }

        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void finish() {
        //TODO resultCode 2:update 3:new
        db.close();
        //close datebase

        Intent i = new Intent();
        if(update){
            if(!ifDelete) {
                i.putExtra("position", position);
                i.putExtra("Contacter", c);
                setResult(2, i);
            }else{
                i.putExtra("position",position);
                setResult(4,i);
            }
        }else{
            i.putExtra("Contacter",c);
            setResult(3,i);
        }
        super.finish();
    }
}
