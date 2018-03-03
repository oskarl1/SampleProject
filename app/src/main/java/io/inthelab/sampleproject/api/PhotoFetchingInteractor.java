package io.inthelab.sampleproject.api;


import java.util.ArrayList;
/**
 *  Interface to handle interactions between activity and API calls
 * */
public interface PhotoFetchingInteractor {

    interface OnPhotosFetchedListener {
        void onPhotoFetchSuccess(ArrayList<PhotoModel> photos);
        void onPhotoFetchError();
    }

    void fetchPhotos(OnPhotosFetchedListener onPhotosFetchedListener);
}
