package com.dopay.framework.mvvm.ui.videolisting;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dopay.framework.mvvm.R;
import com.dopay.framework.mvvm.data.model.api.Items;
import com.dopay.framework.mvvm.data.model.api.VideoCategoryType;

import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder>{

    private final LayoutInflater layoutInflater;

    private AppCompatActivity activity;
    private List<VideoCategoryType.VideoType> filterList;
    private VideoListingNavigator navigator;


    public FilterListAdapter(AppCompatActivity activity, List<VideoCategoryType.VideoType> filterList,VideoListingNavigator navigator) {

        this.activity = activity;
        layoutInflater = LayoutInflater.from(this.activity);
        this.filterList = filterList;
        this.navigator = navigator;
    }

    @Override
    public FilterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.filter_list_row, parent, false);

        return new FilterListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilterListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.title.setText(filterList.get(position).getVideotype());
        holder.title.setCompoundDrawablesWithIntrinsicBounds(filterList.get(position).getIcontype(),0,0,0);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.onFilterItemClickListener(filterList.get(position).getVideoId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList != null && filterList.size() > 0
                ? filterList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.video_category);
        }
    }
}
