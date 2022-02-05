package com.dopay.framework.mvvm.ui.videolisting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dopay.framework.mvvm.R;
import com.dopay.framework.mvvm.data.model.api.Items;
import com.dopay.framework.mvvm.data.model.api.VideoListResponsePojo;


public class VideoListingAdapter extends RecyclerView.Adapter<VideoListingAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;

    private AppCompatActivity activity;
    private Items[] videolist;
    private VideoListingNavigator navigator;


    public VideoListingAdapter(AppCompatActivity activity, Items[] videolist,VideoListingNavigator navigator ) {

        this.activity = activity;
        layoutInflater = LayoutInflater.from(this.activity);
        this.videolist = videolist;
        this.navigator = navigator;
    }

    @Override
    public VideoListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.video_list_row, parent, false);

        return new VideoListingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoListingAdapter.ViewHolder holder, final int position) {

        String thumbnailUrl = videolist[position].getSnippet().getThumbnailsIcon().getHigh().getUrl();
        Glide
                .with(activity)
                .load(thumbnailUrl)
                .centerCrop()
                .placeholder(R.drawable.youtube_placeholder)
                .into(holder.thumbnail);

        holder.title.setText(videolist[position].getSnippet().getTitle());
        holder.description.setText(videolist[position].getSnippet().getDescription());
        holder.layout.setOnClickListener(v -> {
            navigator.onVideoitemClicklistener(videolist[position].getId());
        });

    }

    @Override
    public int getItemCount() {
        return videolist != null && videolist.length > 0
                ? videolist.length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,description;
        private AppCompatImageView thumbnail;
        private CardView layout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            layout = itemView.findViewById(R.id.alerts_parent_card);

        }
    }

}
