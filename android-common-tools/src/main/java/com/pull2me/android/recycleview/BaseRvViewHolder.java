package com.pull2me.android.recycleview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @Author: DuRunzhe{ durunzhe666@gmail.com }
 * @Time: 2017/6/28 8:50
 **/

public abstract class BaseRvViewHolder extends RecyclerView.ViewHolder {
    protected final SparseArray<View> views = new SparseArray<>();

    public BaseRvViewHolder(View itemView) {
        super(itemView);
        findViews();
        itemView.setTag(this);
    }

    /**
     * 需要通过resId查找View控件
     */
    protected abstract void findViews();

    public <Q extends View> Q getView(int resId) {
        View view = views.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            views.put(resId, view);
        }
        return (Q) view;
    }
}
