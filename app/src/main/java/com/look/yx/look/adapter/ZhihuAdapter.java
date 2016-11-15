package com.look.yx.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.look.yx.look.R;
import com.look.yx.look.bean.zhihu.ZhihuDailyItem;
import com.look.yx.look.widget.BadgedFourThreeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * Created by yx on 2016/11/2.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ZhihuViewHolder> {

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
    public void onBindViewHolder(ZhihuViewHolder holder, int position) {
        holder.itemText.setText(zhihuDailyItemList.get(position).getTitle());
    }

    public void addItems(ArrayList<ZhihuDailyItem> list) {
        zhihuDailyItemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return zhihuDailyItemList.size();
    }

    static class ZhihuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image_id)
        BadgedFourThreeImageView itemImageId;
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
