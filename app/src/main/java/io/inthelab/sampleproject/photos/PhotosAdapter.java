package io.inthelab.sampleproject.photos;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.PhotoModel;

/**
 * Adapter for recyclerView displaying photos in grid
 * */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private ArrayList<PhotoModel> photoModelArrayList;
    private Context context;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public PhotosAdapter(Context context) {
        this.context = context;
        photoModelArrayList = new ArrayList<>();
    }



    //==============================================================================================
    // Class Methods
    //==============================================================================================
    public void setPhotoModelArrayList(ArrayList<PhotoModel> photoModelArrayList){
        this.photoModelArrayList = photoModelArrayList;
        notifyDataSetChanged();
    }


    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photos, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        PhotoModel photo = photoModelArrayList.get(position);
        Picasso.with(context).load(photo.thumbnailUrl)
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return photoModelArrayList.size();
    }


    //==============================================================================================
    // PhotoViewHolder Class
    //==============================================================================================
    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_imageView)
        ImageView photoImageView;


        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
