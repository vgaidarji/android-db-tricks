package com.donvigo.androiddbtricks;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by vgaidarji on 8/19/15.
 */
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                        Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}