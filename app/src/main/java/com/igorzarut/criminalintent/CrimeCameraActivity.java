package com.igorzarut.criminalintent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.Window;
import android.view.WindowManager;

public class CrimeCameraActivity extends SingleFragmentActivity {

    private static final String TAG = "CrimeCameraActivity";

    OrientationEventListener mOrientationEventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        mOrientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.d(TAG, "orientation is: " + orientation);
            }
        };
    }

    @Override
    public Fragment createFragment() {
        return new CrimeCameraFragment();
    }


}
