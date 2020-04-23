package com.sdk.recyclertest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static  final int VIEW_TYPE_BANNER = 1;
    public static  final int VIEW_TYPE_HEAD = 2;
    public static  final int VIEW_TYPE_GAME = 3;
    public static  final int VIEW_TYPE_DOWNLOAD = 4;

    private Context mContext;

    private List<ItemBean> lists = new ArrayList<>();

    public MultiAdapter(List<ItemBean> lists) {
        this.lists = lists;
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == VIEW_TYPE_BANNER){
            viewHolder = new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_banner,parent,false));
        }else if(viewType == VIEW_TYPE_HEAD){
            viewHolder = new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_header,parent,false));
        }else if(viewType == VIEW_TYPE_GAME){
            viewHolder = new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_game,parent,false));
        }else if(viewType == VIEW_TYPE_DOWNLOAD) {
            viewHolder = new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_download, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
