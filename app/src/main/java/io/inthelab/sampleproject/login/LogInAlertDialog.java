package io.inthelab.sampleproject.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import io.inthelab.sampleproject.R;
import io.inthelab.sampleproject.api.LogInResponseModel;

/**
 * Display message to user
 * */
public class LogInAlertDialog extends DialogFragment {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    public final static String TAG = "login_alert_dialog";
    public final static String MESSAGE = "message";


    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void show(LogInResponseModel responseModel, FragmentManager fragmentManager) {
        Bundle args = new Bundle();
        args.putString(MESSAGE, responseModel.message.toUpperCase());

        LogInAlertDialog logInAlertDialog = new LogInAlertDialog();
        logInAlertDialog.setArguments(args);
        logInAlertDialog.setCancelable(false);
        logInAlertDialog.show(fragmentManager, TAG);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        Bundle args = getArguments();
        String message = args.getString(MESSAGE);

        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().finish();
            }
        });


        return builder.create();
    }
}
