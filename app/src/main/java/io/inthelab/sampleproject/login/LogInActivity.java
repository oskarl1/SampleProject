package io.inthelab.sampleproject.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.AppUserClient;
import io.inthelab.sampleproject.api.AppUserInteractor;
import io.inthelab.sampleproject.api.LogInResponseModel;

/**
 * Activity class for users to log in
 * */

public class LogInActivity extends AppCompatActivity implements AppUserInteractor.LogInResponseListener {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    @BindView(R.id.usernameEditText)
    EditText usernameEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.logInButton)
    Button logInButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private AppUserClient appUserClient;
    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent intent = new Intent(context, LogInActivity.class);
        context.startActivity(intent);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        appUserClient = new AppUserClient();
        /*
        * User the following to log in successfully:
        * username: TestUser
        * password: password
        * */
    }


    //==============================================================================================
    // Button Click Methods
    //==============================================================================================
    public void onLoginClicked(View view) {
        disableViews();
        final String username = usernameEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {

            appUserClient.logIn(username, password, this);


        } else {

            enableViews();
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_LONG).show();
        }

    }

    //==============================================================================================
    // View Methods
    //==============================================================================================

    private void disableViews() {
        progressBar.setVisibility(View.VISIBLE);
        usernameEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        logInButton.setEnabled(false);
    }

    private void enableViews() {
        progressBar.setVisibility(View.GONE);
        usernameEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
        logInButton.setEnabled(true);
    }


    //==============================================================================================
    // LogInResponseListener Methods
    //==============================================================================================
    @Override
    public void onLogInSuccess(LogInResponseModel response) {
        // Show dialog that displays response info
        if(response.code == 200){
            progressBar.setVisibility(View.GONE);
            LogInAlertDialog.show(response, getSupportFragmentManager());
        }else{
            enableViews();
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onLogInFailed() {
        enableViews();
        // Handle errors here
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
