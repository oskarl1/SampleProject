package io.inthelab.sampleproject.api;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for handling user API calls
 * */

public class AppUserClient implements AppUserInteractor {


    @Override
    public void logIn(String username, String password, final LogInResponseListener logInResponseListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-louisoskar-bea55.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AppService service = retrofit.create(AppService.class);

        service.userLogIn(username, password).enqueue(new Callback<LogInResponseModel>() {
            @Override
            public void onResponse(Call<LogInResponseModel> call, Response<LogInResponseModel> response) {
                if(!response.isSuccessful()){

                    logInResponseListener.onLogInFailed();

                }else{

                    logInResponseListener.onLogInSuccess(response.body());

                }

            }

            @Override
            public void onFailure(Call<LogInResponseModel> call, Throwable t) {
                logInResponseListener.onLogInFailed();
            }
        });
    }



    @Override
    public void fetchUsers(final OnUsersFetchedListener onUsersFetchedListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AppService service = retrofit.create(AppService.class);
        service.fetchUsers().enqueue(new Callback<UserModel.AppUserListResults>() {
            @Override
            public void onResponse(Call<UserModel.AppUserListResults> call, Response<UserModel.AppUserListResults> response) {
                if(!response.isSuccessful()){
                    onUsersFetchedListener.onUserFetchedError();
                }else{


                    onUsersFetchedListener.onUsersFetchSuccess(response.body().usersArrayList);
                }
            }

            @Override
            public void onFailure(Call<UserModel.AppUserListResults> call, Throwable t) {
                onUsersFetchedListener.onUserFetchedError();
            }
        });
    }
}
