package cn.liudp.digitaledittextdemo.fragments;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import cn.liudp.digitaledittextdemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.liudp.digitaledittextdemo.MyApplication;
import cn.liudp.digitaledittextdemo.util.DigitalEditText;
import static android.content.Context.MODE_PRIVATE;

/**
 * @author dongpoliu on 9/27/2017.
 */

public class SettingIpDialogFragment extends DialogFragment {
    @BindView(R.id.input_ip_edit_text)
    DigitalEditText mSetIpEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View mView = inflater.inflate(R.layout.fragment_setting_dialog, container);
        ButterKnife.bind(this, mView);
        SharedPreferences sharedPreferences = MyApplication.getApplication().getSharedPreferences("hosts", MODE_PRIVATE);
        String HOST = sharedPreferences.getString("host","192.168.1.186");
        System.out.println("Server IP is " + HOST);
        String[] ipArray = HOST.split("\\.");
        for (String item : ipArray)
        {
            System.out.println("Server IP item = " + item);
        }
        mSetIpEdit.setDigitalEditTextValue(ipArray);
        return mView;
    }

    @OnClick({R.id.save_ip_value_button, R.id.cancel_ip_value_button,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_ip_value_button:
                onClickIpSaveButton();
                getDialog().dismiss();
                break;
            case R.id.cancel_ip_value_button:
                getDialog().dismiss();
                break;
        }
    }

    public void onClickIpSaveButton() {
        String[] serverIpArray = mSetIpEdit.getDigitalEditTextValue();
        if (serverIpArray[0].equals("") || serverIpArray[1].equals("") || serverIpArray[2].equals("") || serverIpArray[3].equals("") ||
                serverIpArray[0].equals("0") || serverIpArray[1].equals("0") || serverIpArray[2].equals("0") || serverIpArray[3].equals("0")) {
            Toast toast= Toast.makeText(MyApplication.getApplication(),"IP地址不能为空白或零，请重新设置", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (Integer.parseInt(serverIpArray[0]) > 255 || Integer.parseInt(serverIpArray[1]) > 255
                || Integer.parseInt(serverIpArray[2]) > 255 || Integer.parseInt(serverIpArray[3]) > 255) {
            Toast toast= Toast.makeText(MyApplication.getApplication(),"IP地址段不能大于255，请重新设置", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        String serverIp = serverIpArray[0] + "." + serverIpArray[1] + "." + serverIpArray[2] + "." + serverIpArray[3];
        System.out.println("Server IP is " + serverIp );
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hosts", MODE_PRIVATE);
        //得到SharedPreferences.Editor对象，并保存数据到该对象中
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("host", serverIp);
        //保存key-value对到文件中
        editor.apply();
    }
}
