package com.example.zhanghehe.androidarchitecturelearing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.zhanghehe.androidarchitecturelearing.db.AppDatabase;
import com.example.zhanghehe.androidarchitecturelearing.db.entity.ProductEntity;

import java.util.List;

public class DataRepository {
    private static DataRepository sInstance;
    private final AppDatabase mDatabase;
    private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

    private DataRepository(AppDatabase databse) {
        mDatabase=databse;
        mObservableProducts=new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),
                productEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue()!=null) {
                        mObservableProducts.postValue(productEntities);
                    }
                });
    }

    public static DataRepository getInstance(AppDatabase databse) {

        if(sInstance==null){
            synchronized (DataRepository.class){
                if (sInstance==null) {
                    sInstance=new DataRepository(databse);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }
}
