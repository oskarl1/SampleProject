package io.inthelab.sampleproject.api;


import java.util.ArrayList;

/**
 * Interface to handle interactions between activity and API calls
 * */
public interface AppUserInteractor {

    interface LogInResponseListener{
        void onLogInSuccess(LogInResponseModel response);
        void onLogInFailed();
    }

    interface OnUsersFetchedListener{
        void onUsersFetchSuccess(ArrayList<UserModel> usersList);
        void onUserFetchedError();
    }

    void logIn(String username, String password, LogInResponseListener logInResponseListener);

    void fetchUsers(OnUsersFetchedListener onUsersFetchedListener);
}
