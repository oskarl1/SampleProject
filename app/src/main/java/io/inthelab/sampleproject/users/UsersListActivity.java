package io.inthelab.sampleproject.users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.AppUserClient;
import io.inthelab.sampleproject.api.AppUserInteractor;
import io.inthelab.sampleproject.api.UserModel;

/**
 * Activity class for displaying list of users
 */

public class UsersListActivity extends AppCompatActivity implements AppUserInteractor.OnUsersFetchedListener {


    //==============================================================================================
    // Class Properties
    //==============================================================================================

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private UsersListAdapter usersListAdapter;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent intent = new Intent(context, UsersListActivity.class);
        context.startActivity(intent);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        usersListAdapter = new UsersListAdapter(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(usersListAdapter);


        new AppUserClient().fetchUsers(this);
    }


    //==============================================================================================
    // OnUsersFetchedListener Methods
    //==============================================================================================

    @Override
    public void onUsersFetchSuccess(ArrayList<UserModel> usersList) {
        progressBar.setVisibility(View.GONE);
        usersListAdapter.setUsersArrayListt(usersList);

    }

    @Override
    public void onUserFetchedError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
