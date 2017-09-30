package cn.liudp.digitaledittextdemo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.liudp.digitaledittextdemo.fragments.SettingIpDialogFragment;

/**
 * updated by rubinliu@hotmail.com
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_ip_config)
    Button mIvIpConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_ip_config})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ip_config:
                showEditIpDialog(view);
                break;
        }
    }

    public void showEditIpDialog(View view)
    {
        SettingIpDialogFragment editIpDialog = new SettingIpDialogFragment();
        editIpDialog.show(this.getFragmentManager(), "EditIpDialogFragment");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
