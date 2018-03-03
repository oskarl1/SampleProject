package io.inthelab.sampleproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.inthelab.sampleproject.animation.AnimationActivity;
import io.inthelab.sampleproject.login.LogInActivity;
import io.inthelab.sampleproject.photos.PhotosActivity;
import io.inthelab.sampleproject.users.UsersListActivity;

public class MainActivity extends AppCompatActivity {


    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //==============================================================================================
    // Button Click Methods
    //==============================================================================================

    public void onLoginClicked(View view) {
        LogInActivity.start(this);
    }

    public void onAnimationClicked(View view) {
        AnimationActivity.start(this);
    }

    public void onPhotosClicked(View view) {
        PhotosActivity.start(this);
    }

    public void onUserListClicked(View view) {
        UsersListActivity.start(this);
    }
}
