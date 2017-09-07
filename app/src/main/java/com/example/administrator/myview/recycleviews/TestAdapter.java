package com.example.administrator.myview.recycleviews;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myview.R;

import java.util.ArrayList;


/**
 * Created by wWX504383 on 2017/9/7.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mData;

    Context context;

    LinearLayoutManager layoutManager;

    MyAdapter myAdapter;

    public TestAdapter(ArrayList<String> data,Context context) {
        this.mData = data;
        this.context = context;

        myAdapter = new MyAdapter(mData);
        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==3){
            // 实例化展示的view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle2, parent, false);
            // 实例化viewholder

            return new Mviewholder2(v);
        }else {

            // 实例化展示的view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);

            return new Mviewholder1(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Mviewholder1){
            // 绑定数据
            ((Mviewholder1)holder).mTv.setText("==="+position);
        }else if (holder instanceof Mviewholder2){
            ((Mviewholder2)holder).recyclerView.setLayoutManager(layoutManager);
            ((Mviewholder2)holder).recyclerView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public static class Mviewholder1 extends RecyclerView.ViewHolder{
        TextView mTv;
        public Mviewholder1(View itemView) {
            super(itemView);

                mTv = (TextView) itemView.findViewById(R.id.item_tv);
            }

    }

    public static class Mviewholder2 extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        public Mviewholder2(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.te2);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
