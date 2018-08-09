package com.test.compl.nerdlauncher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.test.compl.fragment.NerdFragment;

public class NerdLauncherActivity extends AppCompatActivity {

    public static final String TAG = "NerdLauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nerd_launcher);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.framelayout);

        if (fragment == null) {
            fragment = NerdFragment.newInstance();
            manager.beginTransaction()
                    .add(R.id.framelayout, fragment)
                    .commit();
        }

        initDatas();
    }

    private void initDatas() {
        Log.d(TAG, "initDatas: displayWidth " + getResources().getDisplayMetrics().widthPixels);
        Log.d(TAG, "initDatas: displayHeight " + getResources().getDisplayMetrics().heightPixels);
        Log.d(TAG, "initDatas: density " + getResources().getDisplayMetrics().density);
        Log.d(TAG, "initDatas: xDpi " + getResources().getDisplayMetrics().xdpi);
        Log.d(TAG, "initDatas: yDpi " + getResources().getDisplayMetrics().ydpi);
        Log.d(TAG, "initDatas: densityDpi" + getResources().getDisplayMetrics().densityDpi);

        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);

        Log.d(TAG, "initDatas: metrics.height " + display.getHeight());
        Log.d(TAG, "initDatas: metrics.width " + display.getWidth());
        Log.d(TAG, "initDatas: metrics.density " + metrics.density);
    }
}
