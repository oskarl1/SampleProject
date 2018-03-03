package io.inthelab.sampleproject.api;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * Data model for Users
 * */
public class UserModel {

    public String email;
    public UserNameModel name;
    public UserPictureModel picture;



    // Used for getting list of users from results using GSON
    public class AppUserListResults{

        @SerializedName("results")
        public ArrayList<UserModel> usersArrayList;
    }
}
