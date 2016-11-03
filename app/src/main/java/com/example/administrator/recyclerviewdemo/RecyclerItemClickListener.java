package com.example.administrator.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    private GestureDetector mGestureDetector;

    public interface OnRecyclerItemClickListener {
        void onClickListener(View view, int positon);

        void onLongClickListener(View view, int positon);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnRecyclerItemClickListener mOnRecyclerItemClickListener) {
        this.mOnRecyclerItemClickListener = mOnRecyclerItemClickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
//            处理单击事件
            public boolean onSingleTapUp(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());

//                如果mOnRecyclerItemClickListener不为空并且给定位置有子View存在，所以必须进行非空判断
                if (view !=null && mOnRecyclerItemClickListener != null){
                    mOnRecyclerItemClickListener.onClickListener(view, recyclerView.getChildLayoutPosition(view));
                    return true;
                }
                return false;

            }

            @Override
//            处理长按事件
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view !=null && mOnRecyclerItemClickListener!=null)
                mOnRecyclerItemClickListener.onLongClickListener(view, recyclerView.getChildLayoutPosition(view));
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //把事件交给GestureDetector处理
        if (mGestureDetector.onTouchEvent(e))
            return true;
        else
            return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
