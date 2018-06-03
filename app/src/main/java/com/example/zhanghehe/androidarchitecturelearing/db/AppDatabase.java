package com.example.zhanghehe.androidarchitecturelearing.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.zhanghehe.androidarchitecturelearing.AppExecutors;
import com.example.zhanghehe.androidarchitecturelearing.db.converter.DateConverter;
import com.example.zhanghehe.androidarchitecturelearing.db.dao.CommentDao;
import com.example.zhanghehe.androidarchitecturelearing.db.dao.ProductDao;
import com.example.zhanghehe.androidarchitecturelearing.db.entity.CommentEntity;
import com.example.zhanghehe.androidarchitecturelearing.db.entity.ProductEntity;

import java.util.List;

@Database(entities = {ProductEntity.class, CommentEntity.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase{

    private static final String DATABASE_NAME = "basic-sample-db";
    private static  AppDatabase sInstance;

    public abstract ProductDao productDao();

    public abstract CommentDao commentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated=new MutableLiveData<>();

    public static AppDatabase getInstance(Context context, AppExecutors executors){
        if (sInstance==null) {
            synchronized (AppDatabase.class){
                if (sInstance==null) {
                    sInstance=buildDatabase(context.getApplicationContext(),executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context applicationContext, final AppExecutors executors) {
        return Room.databaseBuilder(applicationContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.getmDiskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                addDelay();
                                AppDatabase database = AppDatabase.getInstance(applicationContext, executors);
                                List<ProductEntity> products = DataGenerator.generateProducts();
                                List<CommentEntity> comments = DataGenerator.generateCommentsForProducts(products);

                                insertData(database, products, comments);
                                database.setDatabaseCreated();

                            }
                        });
                    }
                }).build();

    }

    private void updateDatabaseCreated(Context context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database, final List<ProductEntity> products,
                                   final List<CommentEntity> comments){
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.productDao().insertAll(products);
                database.commentDao().insertAll(comments);
            }
        });
    }

    private static void addDelay(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<Boolean> getDatabaseCreated(){
        return mIsDatabaseCreated;
    }


}














