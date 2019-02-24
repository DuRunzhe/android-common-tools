package com.pull2me.android.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: DuRunzhe{ durunzhe666@gmail.com }
 * @Time: 2017/6/28 8:49
 **/
public abstract class BaseRvAdapter<Q, P extends BaseRvViewHolder> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<Q> dataSet;
    private Class<P> holdClass;
    private int itemResId;
    private LayoutInflater mLayoutInflater;
    private Constructor<P> mHolderConstructor;

    public BaseRvAdapter(Context context, int itemResId, List<Q> dataSet, Class<P> holdClass) {
        this.mContext = context;
        this.holdClass = holdClass;
        if (this.dataSet == null) {
            this.dataSet = new ArrayList<>();
        }
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        this.itemResId = itemResId;
        mLayoutInflater = LayoutInflater.from(context);

    }

    /**
     * 刷新是适配器
     *
     * @param dataSet 数据集合
     */
    public void refreshAdapter(List<Q> dataSet) {
        if (dataSet == null) {
            dataSet = new ArrayList<>();
        }
        this.dataSet.clear();
        this.dataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(itemResId, parent, false);
        BaseRvViewHolder viewHolder;
        try {
            if (mHolderConstructor == null) {
                mHolderConstructor = holdClass.getConstructor(View.class);
            }
            viewHolder = mHolderConstructor.newInstance(itemView);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseRvViewHolder myViewHolder = (BaseRvViewHolder) holder;
        Q itemData = dataSet.get(position);
        initializeItemViews(itemData, myViewHolder, position);
    }

    protected abstract void initializeItemViews(Q itemData, BaseRvViewHolder viewHolder, int position);

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
