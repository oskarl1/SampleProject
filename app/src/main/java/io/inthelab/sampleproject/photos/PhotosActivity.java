package io.inthelab.sampleproject.photos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.PhotoClient;
import io.inthelab.sampleproject.api.PhotoFetchingInteractor;
import io.inthelab.sampleproject.api.PhotoModel;

/**
 *  Activity class for displaying photos in a grid
 *
 */
public class PhotosActivity extends AppCompatActivity implements PhotoFetchingInteractor.OnPhotosFetchedListener{

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private PhotosAdapter photosAdapter;
    private PhotoClient photoClient;
    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent intent = new Intent(context, PhotosActivity.class);
        context.startActivity(intent);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        photosAdapter = new PhotosAdapter(getApplicationContext());
        recyclerView.setAdapter(photosAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));

        photoClient = new PhotoClient();
        photoClient.fetchPhotos(this);
    }


    //==============================================================================================
    // OnPhotosFetchedListener Methods
    //==============================================================================================
    @Override
    public void onPhotoFetchSuccess(ArrayList<PhotoModel> photos) {
        photosAdapter.setPhotoModelArrayList(photos);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPhotoFetchError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
