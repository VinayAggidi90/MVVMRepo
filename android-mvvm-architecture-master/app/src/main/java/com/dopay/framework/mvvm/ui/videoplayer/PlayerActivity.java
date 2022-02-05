package com.dopay.framework.mvvm.ui.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.dopay.framework.mvvm.R;
import com.dopay.framework.mvvm.databinding.PlayerViewBinding;
import com.dopay.framework.mvvm.di.component.ActivityComponent;
import com.dopay.framework.mvvm.ui.base.BaseActivity;
import com.dopay.framework.mvvm.utils.AppConstants;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView ytPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view);
        playvideobyid();
    }

    private void playvideobyid() {
        Intent data = getIntent();
        String videoId = data.getExtras().getString(AppConstants.VIDEO_ID);
        ytPlayer = findViewById(R.id.ytPlayer);
        ytPlayer.initialize(
                AppConstants.API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    // Implement two methods by clicking on red
                    // error bulb inside onInitializationSuccess
                    // method add the video link or the playlist
                    // link that you want to play In here we
                    // also handle the play and pause
                    // functionality
                    @Override
                    public void onInitializationSuccess(
                            YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b)
                    {
                        youTubePlayer.loadVideo(videoId);
                        youTubePlayer.play();

                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult)
                    {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
