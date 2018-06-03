package com.example.zhanghehe.androidarchitecturelearing.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhanghehe.androidarchitecturelearing.R;
import com.example.zhanghehe.androidarchitecturelearing.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final ProductClickCallback mProductClickCallback;
    List<? extends Product> mProductList;

    public ProductAdapter(ProductClickCallback clickCallback){
        mProductClickCallback=clickCallback;
    }

    public void setProductList(final List<? extends Product> productList){
        if (mProductList==null) {
            mProductList=productList;
            notifyItemRangeInserted(0,productList.size());
        }else {
            DiffUtil.DiffResult result=DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getId()==
                            productList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Product newProduct=productList.get(newItemPosition);
                    Product oldProduct=mProductList.get(oldItemPosition);

                    return newProduct.getId() == oldProduct.getId() &&
                            Objects.equals(newProduct.getDescription(), oldProduct.getDescription()) &&
                            Objects.equals(newProduct.getName(), oldProduct.getName()) &&
                            newProduct.getPrice() == oldProduct.getPrice();
                }
            });

        }
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item, parent, false);
        binding.setCallback(mProductClickCallback);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.setProduct(mProductList.get(position));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mProductList==null?0:mProductList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder{
        final ProductItemBinding binding;

        public ProductViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
