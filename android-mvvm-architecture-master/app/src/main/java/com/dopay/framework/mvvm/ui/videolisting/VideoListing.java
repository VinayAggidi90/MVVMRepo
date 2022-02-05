/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.dopay.framework.mvvm.ui.videolisting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerAdapter;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.dopay.framework.mvvm.BR;
import com.dopay.framework.mvvm.R;
import com.dopay.framework.mvvm.data.model.api.VideoCategoryType;
import com.dopay.framework.mvvm.data.model.api.VideoListResponsePojo;
import com.dopay.framework.mvvm.databinding.SplashScreenBinding;
import com.dopay.framework.mvvm.di.component.ActivityComponent;
import com.dopay.framework.mvvm.ui.base.BaseActivity;
import com.dopay.framework.mvvm.utils.AppConstants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;


/**
 * Created by amitshekhar on 08/07/17.
 */

public class VideoListing extends BaseActivity<SplashScreenBinding, VideoListingViewModel> implements VideoListingNavigator {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_screen;
    }

    private SplashScreenBinding splashScreenBinding;
    private AlertDialog.Builder showFilterDialog;
    private  AlertDialog filter;

    private static List<VideoCategoryType.VideoType> videoTypeList = new ArrayList<>();

    private String nextToken;
    private String prevToken;
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public String getPrevToken() {
        return prevToken;
    }

    public void setPrevToken(String prevToken) {
        this.prevToken = prevToken;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        splashScreenBinding = getViewDataBinding();
        mViewModel.getDataManager().writeAppStringPrefs(AppConstants.CATEGORY_ID,"10");
        setCategoryId("10");
        mViewModel.getVideoListing("",getCategoryId());
        mViewModel.getVideoListResponsePojoMutableLiveData().observe(this,observer);
        writeHandlers();
    }

    private void writeHandlers() {
        splashScreenBinding.prev.setOnClickListener(v -> {

            mViewModel.getVideoListing(getPrevToken(),mViewModel.getDataManager().getAppStringPrefs(AppConstants.CATEGORY_ID));
        });

        splashScreenBinding.next.setOnClickListener(v -> {
            mViewModel.getVideoListing(getNextToken(),mViewModel.getDataManager().getAppStringPrefs(AppConstants.CATEGORY_ID));

        });
        splashScreenBinding.filterIcon.setOnClickListener(v -> {
            showfilterLayout();
        });
    }

    @SuppressLint("RestrictedApi")
    private void showfilterLayout() {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.filter_layout, null);
        ShimmerRecyclerView filterLayout = dialogView.findViewById(R.id.filter_list);
        List<VideoCategoryType.VideoType> videoTypes = dummydataForFilter();
        FilterListAdapter adapter = new FilterListAdapter(VideoListing.this,videoTypes,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        filterLayout.setLayoutManager(layoutManager);
        filterLayout.setAdapter(adapter);
        showFilterDialog = new AlertDialog.Builder(this);
        showFilterDialog.setView(dialogView);
        filter = showFilterDialog.create();
        filter.show();
    }

    private List<VideoCategoryType.VideoType> dummydataForFilter() {
        videoTypeList.clear();
        VideoCategoryType.VideoType music = new VideoCategoryType.VideoType();
        music.setIcontype(R.drawable.next);
        music.setVideotype("Music");
        music.setVideoId(10);
        videoTypeList.add(music);
        VideoCategoryType.VideoType sports = new VideoCategoryType.VideoType();
        sports.setIcontype(R.drawable.next);
        sports.setVideotype("Sports");
        sports.setVideoId(17);
        videoTypeList.add(sports);
        VideoCategoryType.VideoType gaming = new VideoCategoryType.VideoType();
        gaming.setIcontype(R.drawable.next);
        gaming.setVideotype("Gaming");
        gaming.setVideoId(20);
        videoTypeList.add(gaming);
        VideoCategoryType.VideoType animals = new VideoCategoryType.VideoType();
        animals.setIcontype(R.drawable.next);
        animals.setVideotype("Pets & Animals");
        animals.setVideoId(15);
        videoTypeList.add(animals);
        VideoCategoryType.VideoType animation = new VideoCategoryType.VideoType();
        animation.setIcontype(R.drawable.next);
        animation.setVideotype("Film & Animation");
        animation.setVideoId(1);
        videoTypeList.add(animation);
        return videoTypeList;
    }

    Observer<VideoListResponsePojo> observer = videoListResponsePojo -> {
        setUpListView();
        VideoListingAdapter adapter = new VideoListingAdapter(VideoListing.this,videoListResponsePojo.getItems(),this);
        splashScreenBinding.alertsList.setAdapter(adapter);
        if(videoListResponsePojo.getNextPageToken()!=null){
            setNextToken(videoListResponsePojo.getNextPageToken());
            enablenextButton();
        }else{
            disablenextButton();
        }
        if(videoListResponsePojo.getPrevPageToken()!=null){
            setPrevToken(videoListResponsePojo.getPrevPageToken());
            enablePreviousButton();
        }else{
            disablePreviousButton();
        }
    };

   /* Observer<VideoListResponsePojo> observer = new Observer<VideoListResponsePojo>() {
        @Override
        public void onChanged(VideoListResponsePojo videoListResponsePojo) {

        }
    };
*/

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setUpListView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        splashScreenBinding.alertsList.setLayoutManager(layoutManager);
    }

    @Override
    public void startLoading() {
        splashScreenBinding.alertsList.showShimmerAdapter();
    }

    @Override
    public void stopLoading() {
        splashScreenBinding.alertsList.hideShimmerAdapter();

    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT);

    }

    @Override
    public void enablePreviousButton() {
        splashScreenBinding.prev.setVisibility(View.VISIBLE);
    }

    @Override
    public void disablePreviousButton() {
        splashScreenBinding.prev.setVisibility(View.GONE);

    }

    @Override
    public void enablenextButton() {
        splashScreenBinding.next.setVisibility(View.VISIBLE);
    }

    @Override
    public void disablenextButton() {
        splashScreenBinding.next.setVisibility(View.GONE);

    }

    @Override
    public void onFilterItemClickListener(int videocode) {
        filter.dismiss();
        String vc = String.valueOf(videocode);
        mViewModel.getDataManager().writeAppStringPrefs(AppConstants.CATEGORY_ID,vc);
        mViewModel.getVideoListing("",vc);
    }

    @Override
    public void onVideoitemClicklistener(String videoId) {
        Intent showplayeractivity = new Intent(AppConstants.ACTION_LAUNCH_PLAYER);
        showplayeractivity.putExtra(AppConstants.VIDEO_ID,videoId);
        startActivity(showplayeractivity);
    }


}
