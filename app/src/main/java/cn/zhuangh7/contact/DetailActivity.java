package cn.zhuangh7.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/18.HUAWEI_SB
 */

public class DetailActivity extends MyActivity {

    private TextView name;
    private TextView addr;
    private TextView tel_1;
    private TextView tel_2;
    private Button edit;
    private contacter c;
    private boolean ifChange = false;
    private int position = -1;
    private boolean ifDelete = false;

    @Override
    public void finish() {
        if(!ifDelete) {
            Intent i = new Intent();
            i.putExtra("Contacter", c);
            i.putExtra("position", position);
            i.putExtra("ifChange", ifChange);
            setResult(1, i);
            super.finish();
        }else{
            Intent i = new Intent();
            i.putExtra("position",position);
            setResult(2,i);
            super.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detialactivity);
        getDataFromIntent();
        initViews();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        c = (contacter) intent.getSerializableExtra("Contacter");
        position = intent.getIntExtra("position", -1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==2){
            contacter temp = (contacter)data.getSerializableExtra("Contacter");
            int pos = data.getIntExtra("position",-1);
            this.c = temp;
            updateViews();
            ifChange=true;
        }
        if(requestCode==2&&resultCode==4){
            //this item has been deleted
            this.ifDelete=true;
            finish();
            overridePendingTransition(0, 0);
        }
    }

    private void updateViews(){
        name.setText(c.getName());
        addr.setText(c.getAddr());
        tel_1.setText(c.getTel_m());
        tel_2.setText(c.getTel_s());
    }

    private void initViews(){
        name = (TextView)findViewById(R.id.detail_name);
        addr = (TextView)findViewById(R.id.detail_addr);
        tel_1 = (TextView)findViewById(R.id.detail_tel_1);
        tel_2 = (TextView)findViewById(R.id.detail_tel_2);
        name.setText(c.getName());
        addr.setText(c.getAddr());
        tel_1.setText(c.getTel_m());
        tel_2.setText(c.getTel_s());

        edit = (Button)findViewById(R.id.detail_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailActivity.this, UpdateActivity.class);
                intent.putExtra("ifUpdate",true);
                intent.putExtra("Contacter",c);
                intent.putExtra("position",position);
                startActivityForResult(intent,2);
            }
        });
    }


}
