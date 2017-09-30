package cn.liudp.digitaledittextdemo;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    private static MyApplication application;
    private int mActivityCount;
    public long time;

    private SharedPreferences sharedPref;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static MyApplication getApplication() {
        return application;
    }

    public static void setApplication(MyApplication application) {
        MyApplication.application = application;
    }

    public int getActivityCount() {
        return mActivityCount;
    }

    public void setActivityCount(int activityCount) {
        mActivityCount = activityCount;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Stetho.initializeWithDefaults(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivityCount++;
//                LogTrace.d(getClass().getName(), "onActivityStarted", " " + mActivityCount);
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivityCount--;
//                LogTrace.d(getClass().getName(), "onActivityStarted", " " + mActivityCount);

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }
}
