package cn.zhuangh7.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MainActivity extends MyActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);
        button = (Button) findViewById(R.id.button_1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DetailActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.act_in,R.anim.act_out);
            }
        });
        slidrInterface.lock();

    }
}
