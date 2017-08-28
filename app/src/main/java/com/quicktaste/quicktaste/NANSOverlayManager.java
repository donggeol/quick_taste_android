package com.quicktaste.quicktaste;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by K. Park on 2017-08-29.
 */

public class NANSOverlayManager extends Service {
    private View mView;
    private WindowManager mManager;

    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.nans_overlay_manager, null);

        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,

                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,

                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,

                PixelFormat.TRANSLUCENT);

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
