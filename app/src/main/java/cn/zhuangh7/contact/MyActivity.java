package cn.zhuangh7.contact;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.r0adkll.slidr.model.SlidrPosition;

import cn.zhuangh7.contact.datasev.dataBase;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/18.HUAWEI_SB
 */

public class MyActivity extends Activity {

    private SlidrConfig mSlidrConfig;
    private SlidrConfig.Builder mConfigBuilder;
    private dataBase DB;
    public SlidrInterface slidrInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int primary = getResources().getColor(R.color.colorAccent,null);
        int secondary = getResources().getColor(R.color.colorAccent,null);
        mConfigBuilder = new SlidrConfig.Builder().primaryColor(primary)
                .secondaryColor(secondary)
                .scrimColor(Color.BLACK)
                .position(SlidrPosition.LEFT)
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(5f)
                .distanceThreshold(.35f);
        mSlidrConfig = mConfigBuilder.build();
        slidrInterface=Slidr.attach(this, mSlidrConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DB = new dataBase(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        DB.close();
    }
}
