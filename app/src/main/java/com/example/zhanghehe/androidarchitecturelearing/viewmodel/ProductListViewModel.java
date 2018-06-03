package com.example.zhanghehe.androidarchitecturelearing.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.zhanghehe.androidarchitecturelearing.BasicApp;
import com.example.zhanghehe.androidarchitecturelearing.db.entity.ProductEntity;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {
    private final MediatorLiveData<Object> mObservableProducts;

    public ProductListViewModel(@NonNull Application application) {
        super(application);

        mObservableProducts=new MediatorLiveData<>();
        mObservableProducts.setValue(null);

        LiveData<List<ProductEntity>> products= ((BasicApp) application).getRepository()
                .getProducts();

        mObservableProducts.addSource(products,mObservableProducts::setValue);
    }


}
