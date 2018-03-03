package io.inthelab.sampleproject.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;

/**
 * Activity class where user can drag an image around on the screen as well as animate view
 * */
public class AnimationActivity extends AppCompatActivity implements View.OnTouchListener {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    @BindView(R.id.image)
    ImageView image;

    private float dX, dY;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {

        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);

    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        image.setOnTouchListener(this);
    }

    //==============================================================================================
    // Button Click Methods
    //==============================================================================================


    public void onFadeClicked(View view) {
        fadeLogoOut();
    }


    //==============================================================================================
    // Animation Methods
    //==============================================================================================

    private void fadeLogoIn() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(1500);
        image.setAnimation(alphaAnimation);
        image.animate();
        image.setVisibility(View.VISIBLE);
    }

    private void fadeLogoOut() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeLogoIn();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.setAnimation(alphaAnimation);
        image.animate();
        image.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }
}
