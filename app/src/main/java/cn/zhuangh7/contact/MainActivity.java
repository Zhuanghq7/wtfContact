package cn.zhuangh7.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.List;

import cn.zhuangh7.contact.datasev.dataBase;
import cn.zhuangh7.contact.datasev.mDateBase;

public class MainActivity extends MyActivity {

    private Button button;
    private RecyclerView recyclerView;
    private List<contacter> data;
    private dataBase db;
    private mainAdatper adatper;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        initData();
        initView();
    }

    private void initData(){
        db=new dataBase(this);
        data = db.getContacts(-1,-1);
        db.close();
    }
    private void initView(){
        button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ifUpdate",false);
                intent.setClass(MainActivity.this,UpdateActivity.class);
                startActivityForResult(intent,3);

            }
        });
        slidrInterface.lock();
        recyclerView = (RecyclerView)findViewById(R.id.main_recycelView);
        adatper = new mainAdatper(data,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adatper);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            //detail without delete
            if(data.getBooleanExtra("ifChange",false))
            {
                contacter tempC = (contacter) data.getSerializableExtra("Contacter");
                int position = data.getIntExtra("position",-1);
                if(position!=-1){
                    this.data.set(position,tempC);
                    adatper.updatedata(this.data);
                    adatper.notifyItemChanged(position);
                    //TODO update the specific data
                }
            }
        }
        if(requestCode==1&&resultCode==2){
            //detail and delete
            int pos = data.getIntExtra("position",-1);
            this.data.remove(pos);
            adatper.updatedata(this.data);
            adatper.notifyDataSetChanged();

        }
        if(requestCode==3&&resultCode==3){
            //add new

            contacter temp = (contacter)data.getSerializableExtra("Contacter");
            this.data.add(temp);
            adatper.updatedata(this.data);
            adatper.notifyDataSetChanged();
        }
    }
}
