package io.inthelab.sampleproject.api;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Class for making Photo fetching API calls
 * */
public class PhotoClient implements PhotoFetchingInteractor {


    @Override
    public void fetchPhotos(final OnPhotosFetchedListener onPhotosFetchedListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AppService service = retrofit.create(AppService.class);

        service.fetchPhotos().enqueue(new Callback<ArrayList<PhotoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PhotoModel>> call, Response<ArrayList<PhotoModel>> response) {
                if (!response.isSuccessful()) {
                    onPhotosFetchedListener.onPhotoFetchError();
                } else {
                    onPhotosFetchedListener.onPhotoFetchSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PhotoModel>> call, Throwable t) {
                onPhotosFetchedListener.onPhotoFetchError();
            }
        });
    }
}
