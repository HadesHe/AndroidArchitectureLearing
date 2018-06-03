package com.example.zhanghehe.androidarchitecturelearing;

import android.app.Application;

import com.example.zhanghehe.androidarchitecturelearing.db.AppDatabase;

public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors=new AppExecutors();
    }

    public AppDatabase getDatabse(){
        return AppDatabase.getInstance(this, mAppExecutors);
    }


    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabse());
    }


}
