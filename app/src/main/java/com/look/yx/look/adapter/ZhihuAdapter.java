package com.look.yx.look.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.look.yx.look.R;
import com.look.yx.look.activity.ZhihuDescribeActivity;
import com.look.yx.look.bean.zhihu.ZhihuDailyItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2016/11/2.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ZhihuViewHolder> {

    private String mImageUrl;
    private Context mContext;
    private List<ZhihuDailyItem> zhihuDailyItemList = new ArrayList<>();

    public ZhihuAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZhihuViewHolder zhihuViewHolder = new ZhihuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.zhihu_layout_item, parent, false));
        return zhihuViewHolder;
    }

    @Override
    public void onBindViewHolder(final ZhihuViewHolder holder, final int position) {
        holder.itemText.setText(zhihuDailyItemList.get(position).getTitle());
        holder.itemImageId.setImageURI(zhihuDailyItemList.get(position).getImages()[0]);
        holder.itemImageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDescribeActivity(holder,zhihuDailyItemList.get(position));
            }
        });
        holder.zhihuItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDescribeActivity(holder,zhihuDailyItemList.get(position));
            }
        });
    }

    private void goDescribeActivity(ZhihuViewHolder holder,ZhihuDailyItem zhihuDailyItem){

        Intent intent = new Intent(mContext, ZhihuDescribeActivity.class);
        intent.putExtra("id", zhihuDailyItem.getId());
        intent.putExtra("title", zhihuDailyItem.getTitle());
        intent.putExtra("image",mImageUrl);
        mContext.startActivity(intent);

    }

    public void addItems(ArrayList<ZhihuDailyItem> list) {
        zhihuDailyItemList.addAll(list);
        //TODO  效率不高
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return zhihuDailyItemList.size();
    }

    static class ZhihuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image_id)
        SimpleDraweeView itemImageId;
        @BindView(R.id.item_text_id)
        TextView itemText;
        @BindView(R.id.zhihu_item_layout)
        LinearLayout zhihuItemLayout;


        ZhihuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
