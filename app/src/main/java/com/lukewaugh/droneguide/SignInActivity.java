package com.lukewaugh.droneguide;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.amazonaws.mobile.AWSMobileClient;
//import com.amazonaws.user.signin.SignInManager;
import com.amazonaws.mobile.user.IdentityManager;

public class SignInActivity extends Activity{
    private CRUD crud = new CRUD();
    //rivate final static String LOG_TAG = SignInActivity.class.getSimpleName();
    //private SignInManager signInManager;
    /** Permission Request Code (Must be < 256). */
    private static final int GET_ACCOUNTS_PERMISSION_REQUEST_CODE = 93;
    /** The Google OnClick listener, since we must override it to get permissions on Marshmallow and above. */
   // private View.OnClickListener googleOnClickListener;
    /**
     * SignInResultsHandler handles the final result from sign in. Making it static is a best
     * practice since it may outlive the SplashActivity's life span.
     ///

     public class IdentityDemoFragment extends DemoFragmentBase {
     /** Logging tag for this class. */
    //private static final String LOG_TAG = IdentityDemoFragment.class.getSimpleName();

    /** The identity manager used to keep track of the current user account. */
    private IdentityManager identityManager;

    /** This fragment's view. */
    private View mFragmentView;

    /** Text view for showing the user identity. */
    private TextView userIdTextView;

    /** Text view for showing the user name. */
    private TextView userNameTextView;

    /** Image view for showing the user image. */
    private ImageView userImageView;

    public View onCreate(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        mFragmentView = inflater.inflate(R.layout.fragment_demo_identity, container, false);
//        userNameTextView = (TextView) mFragmentView.findViewById(R.id.textView_demoIdentityUserName);
//        userIdTextView = (TextView) mFragmentView.findViewById(R.id.textView_demoIdentityUserID);
//        userImageView = (ImageView)mFragmentView.findViewById(R.id.imageView_demoIdentityUserImage);

        // Obtain a reference to the identity manager.
        identityManager = AWSMobileClient.defaultMobileClient()
                .getIdentityManager();
        fetchUserIdentity();
        return mFragmentView;




    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain a reference to the mobile client. It is created in the Application class,
        // but in case a custom Application class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();

        setContentView(R.layout.activity_login);

        //setupToolbar(savedInstanceState);

        //setupNavigationMenu(savedInstanceState);
    }

 //@Override
    public void onDestroyView() {
       // super.onDestroyView();
    }
    /**
     * Fetches the user identity safely on the background thread.  It may make a network call.
     */
    private void fetchUserIdentity() {
       // Log.d(LOG_TAG, "fetchUserIdentity");
        // Pre-fetched to avoid race condition where fragment is no longer active.
        final String unknownUserIdentityText =
                "Unknown User";

        AWSMobileClient.defaultMobileClient()
                .getIdentityManager()
                .getUserID(new IdentityManager.IdentityHandler() {

                    @Override
                    public void handleIdentityID(String identityId) {

                        // We have successfully retrieved the user's identity. You can use the
                        // user identity value to uniquely identify the user. For demonstration
                        // purposes here, we will display the value in a text view.
                        userIdTextView.setText(identityId);
                    }

                    @Override
                      public void handleError(Exception exception) {

                        // We failed to retrieve the user's identity. Set unknown user identitier
                        // in text view.
                        userIdTextView.setText(unknownUserIdentityText);
                            //POP up error



//                        final Context context = getActivity();
//
//                        if (context != null && isAdded()) {
//                            new AlertDialog.Builder(getActivity())
//                                    .setTitle(R.string.identity_demo_error_dialog_title)
//                                    .setMessage(getString(R.string.identity_demo_error_message_failed_get_identity)
//                                            + exception.getMessage())
//                                    .setNegativeButton(R.string.identity_demo_dialog_dismiss_text, null)
//                                    .create()
//                                    .show();
                        }

                });
    }
}



    //}

     /*
//    private class SignInResultsHandler implements IdentityManager.SignInResultsHandler {
//        /**
//         * Receives the successful sign-in result and starts the main activity.
//         * @param provider the identity provider used for sign-in.
//         */
//        @Override
//        public void onSuccess(final IdentityProvider provider) {
//            Log.d(LOG_TAG, String.format("User sign-in with %s succeeded",
//                    provider.getDisplayName()));
//            // The sign-in manager is no longer needed once signed in.
//            SignInManager.dispose();
//            Toast.makeText(SignInActivity.this, String.format("Sign-in with %s succeeded.",
//                    provider.getDisplayName()), Toast.LENGTH_LONG).show();
//            // Load user name and image.
//            AWSMobileClient.defaultMobileClient()
//                    .getIdentityManager().loadUserInfoAndImage(provider, new Runnable() {
//                @Override
//                public void run() {
//                    Log.d(LOG_TAG, "Launching Main Activity...");
//                    startActivity(new Intent(SignInActivity.this, MainActivity.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                    // finish should always be called on the main thread.
//                    finish();
//                }
//            });
//        }
//        /**
//         * Recieves the sign-in result indicating the user canceled and shows a toast.
//         * @param provider the identity provider with which the user attempted sign-in.
//         */
//        @Override
//        public void onCancel(final IdentityProvider provider) {
//            Log.d(LOG_TAG, String.format("User sign-in with %s canceled.",
//                    provider.getDisplayName()));
//            Toast.makeText(SignInActivity.this, String.format("Sign-in with %s canceled.",
//                    provider.getDisplayName()), Toast.LENGTH_LONG).show();
//        }
//        /**
//         * Receives the sign-in result that an error occurred signing in and shows a toast.
//         * @param provider the identity provider with which the user attempted sign-in.
//         * @param ex the exception that occurred.
//         */
//        @Override
//        public void onError(final IdentityProvider provider, final Exception ex) {
//            Log.e(LOG_TAG, String.format("User Sign-in failed for %s : %s",
//                    provider.getDisplayName(), ex.getMessage()), ex);
//            final AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(SignInActivity.this);
//            errorDialogBuilder.setTitle("Sign-In Error");
//            errorDialogBuilder.setMessage(
//                    String.format("Sign-in with %s failed.\n%s", provider.getDisplayName(), ex.getMessage()));
//            errorDialogBuilder.setNeutralButton("Ok", null);
//            errorDialogBuilder.show();
//        }
//    }
//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        signInManager = SignInManager.getInstance(this);
//        signInManager.setResultsHandler(this, new SignInResultsHandler());
//        // Initialize sign-in buttons.
////        googleOnClickListener =
////                signInManager.initializeSignInButton(GoogleSignInProvider.class, findViewById(R.id.g_login_button));
////        if (googleOnClickListener != null) {
////            // if the onClick listener was null, initializeSignInButton will have removed the view.
////            this.findViewById(R.id.g_login_button).setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(final View view) {
////                    final Activity thisActivity = SignInActivity.this;
////                    if (ContextCompat.checkSelfPermission(thisActivity,
////                            Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
////                        ActivityCompat.requestPermissions(SignInActivity.this,
////                                new String[]{Manifest.permission.GET_ACCOUNTS},
////                                GET_ACCOUNTS_PERMISSION_REQUEST_CODE);
////                        return;
////                    }
////                    // call the Google onClick listener.
////                    googleOnClickListener.onClick(view);
////                }
////            });
//
//        Button homeBtn = (Button) findViewById(R.id.homeBtn);
//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    @Override
//    public void onRequestPermissionsResult(final int requestCode,
//                                           final String permissions[], final int[] grantResults) {
////        if (requestCode == GET_ACCOUNTS_PERMISSION_REQUEST_CODE) {
////            if (grantResults.length > 0
////                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                this.findViewById(R.id.g_login_button).callOnClick();
////            } else {
////                Log.i(LOG_TAG, "Permissions not granted for Google sign-in. :(");
////            }
////        }
//    }
//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        signInManager.handleActivityResult(requestCode, resultCode, data);
//    }

//}
