package com.example.zhanghehe.androidarchitecturelearing.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhanghehe.androidarchitecturelearing.R;
import com.example.zhanghehe.androidarchitecturelearing.model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null) {
            ProductListFragment fragment=new ProductListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment,ProductListFragment.TAG).commit();
        }
    }


    public void show(Product product) {
// TODO: 2018/6/3 start ProductFragment
    }
}
