package com.example.zhanghehe.androidarchitecturelearing.ui;

import android.arch.lifecycle.Lifecycle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhanghehe.androidarchitecturelearing.R;
import com.example.zhanghehe.androidarchitecturelearing.databinding.ListFragmentBinding;
import com.example.zhanghehe.androidarchitecturelearing.model.Product;

public class ProductListFragment extends Fragment {

    public static final String TAG = ProductListFragment.class.getSimpleName();
    private ListFragmentBinding mBinding;
    private ProductAdapter mProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.list_fragment,container,false);

        mProductAdapter=new ProductAdapter(mProductClickCallback);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: 2018/6/3 addProductListViewModel
        
    }

    private final ProductClickCallback mProductClickCallback=new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((MainActivity) getActivity()).show(product);
            }
        }
    };
}
