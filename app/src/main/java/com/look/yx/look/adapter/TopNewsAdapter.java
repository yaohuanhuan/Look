package com.look.yx.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.look.yx.look.R;
import com.look.yx.look.bean.news.NewsBean;
import com.look.yx.look.bean.zhihu.ZhihuDailyItem;
import com.look.yx.look.util.DensityUtil;
import com.look.yx.look.util.DribbbleTarget;
import com.look.yx.look.widget.BadgedFourThreeImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yx on 2017/1/6.
 */

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder> {


    private ArrayList<NewsBean> topNewitems = new ArrayList<>();
    private Context mContext;
    float width;
    int widthPx;
    int heighPx;
    public TopNewsAdapter(Context mContext) {
        this.mContext = mContext;
        width = mContext.getResources().getDimension(R.dimen.image_width);
        widthPx = DensityUtil.dip2px(mContext, width);
        heighPx = widthPx * 3 / 4;
    }

    @Override
    public TopNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TopNewsViewHolder topNewsViewHolder = new TopNewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.topnews_layout_item, parent, false));
        return topNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(TopNewsViewHolder holder, int position) {
        final NewsBean newsBeanItem = topNewitems.get(holder.getAdapterPosition());
        holder.textView.setText(newsBeanItem.getTitle());
        holder.sourceTextview.setText(newsBeanItem.getSource());
        Glide.with(mContext)
                .load(newsBeanItem.getImgsrc())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop().override(widthPx, heighPx)
                .into(new DribbbleTarget(holder.imageView, false));
    }

    @Override
    public int getItemCount() {
        return topNewitems.size();
    }

    public void addItems(ArrayList<NewsBean> list) {
        topNewitems.addAll(list);
        //TODO  效率不高
        notifyDataSetChanged();
    }

    static class TopNewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image_id)
        BadgedFourThreeImageView imageView;
        @BindView(R.id.item_text_id)
        TextView textView;
        @BindView(R.id.item_text_source_id)
        TextView sourceTextview;
        @BindView(R.id.zhihu_item_layout)
        LinearLayout linearLayout;

        public TopNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
