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
    }
    private void initView(){
        button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this,DetailActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.act_in,R.anim.act_out);
                db.newContact(""+id,"地址"+id,"18340861800","15355298095");
                id++;
                data=db.getContacts(-1,-1);
                adatper.updatedata(data);
                adatper.notifyDataSetChanged();
            }
        });
        slidrInterface.lock();
        recyclerView = (RecyclerView)findViewById(R.id.main_recycelView);
        adatper = new mainAdatper(data,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adatper);
    }
}
