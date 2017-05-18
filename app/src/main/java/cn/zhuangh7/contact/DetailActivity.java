package cn.zhuangh7.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/18.HUAWEI_SB
 */

public class DetailActivity extends MyActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detialactivity);
        button = (Button)findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DetailActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.act_in,R.anim.act_out);
            }
        });
    }
}
