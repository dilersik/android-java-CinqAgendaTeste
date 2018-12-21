package com.dilerdesenvolv.cinqagendateste.activity.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dilerdesenvolv.cinqagendateste.R;
import com.dilerdesenvolv.cinqagendateste.activity.main.MainPresenter;
import com.dilerdesenvolv.cinqagendateste.adapter.GalleryAdapter;
import com.dilerdesenvolv.cinqagendateste.adapter.UserAdapter;
import com.dilerdesenvolv.cinqagendateste.domain.model.Gallery;
import com.dilerdesenvolv.cinqagendateste.domain.model.User;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements GalleryView {

    private GalleryPresenter mPresenter;
    private ProgressBar mProgress;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mPresenter = new GalleryPresenter(this);
        mProgress = findViewById(R.id.progress);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvGallery);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRecyclerView.invalidate();
        mPresenter.listAlbuns();
    }

    @Override
    public void updateView(List<Gallery> albuns) {
        mAdapter = new GalleryAdapter(this, mPresenter, albuns);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateView() {
        mRecyclerView.invalidate();
        mPresenter.listAlbuns();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
