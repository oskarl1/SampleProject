package io.inthelab.sampleproject.users;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.UserModel;

/**
 * Adapter class for recyclerView displaying users in a list
 * */
public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserListViewHolder> {


    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private ArrayList<UserModel> usersArrayList;
    private Context context;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public UsersListAdapter(Context context) {
        this.context = context;
        usersArrayList = new ArrayList<>();
    }


    //==============================================================================================
    // Class Methods
    //==============================================================================================
    public void setUsersArrayListt(ArrayList<UserModel> usersArrayList) {
        this.usersArrayList = usersArrayList;
        notifyDataSetChanged();
    }


    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
        UserModel user = usersArrayList.get(position);

        holder.nameTextView.setText(String.format(Locale.getDefault(), "%s %s", user.name.first, user.name.last));
        holder.emailTextView.setText(user.email);
        Picasso.with(context).load(user.picture.thumbnail)
                .transform(new CircleTransform())
                .into(holder.pictureImageView);
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    //==============================================================================================
    // UserListViewHolder Class
    //==============================================================================================
    public static class UserListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView pictureImageView;

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.emailTextView)
        TextView emailTextView;

        public UserListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
