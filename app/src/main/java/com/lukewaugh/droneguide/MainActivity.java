package com.lukewaugh.droneguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


import com.amazonaws.UserSettings;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobile.user.IdentityManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.mobileconnectors.cognito.Record;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IdentityManager identityManager;

    private Button signInButton, signOutButton;

    public MathClass theMaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtLatEnd = (TextView) findViewById(R.id.latText);
        final TextView txtLonEnd = (TextView) findViewById(R.id.longText);

        Button btnGo = (Button) findViewById(R.id.startBtn);

        btnGo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getOuter(), MapsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("END_LAT", txtLatEnd.getText().toString());
                extras.putString("END_LON", txtLonEnd.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData() {

        TextView latitude, longitude;

        latitude = (TextView) findViewById(R.id.latText);
        longitude = (TextView) findViewById(R.id.longText);

    }

    public double measureDistance() {
        //return theMaths.measureDistance();
        return 0.0f;

    }

    private void setupSignInButtons() {

        signOutButton = (Button) findViewById(R.id.button_signout);
        signOutButton.setOnClickListener(this);
         signInButton = (Button) findViewById(R.id.button_signin);
        signInButton.setOnClickListener(this);
        final boolean isUserSignedIn = identityManager.isUserSignedIn();
        signOutButton.setVisibility(isUserSignedIn ? View.VISIBLE : View.INVISIBLE);
        signInButton.setVisibility(!isUserSignedIn ? View.VISIBLE : View.INVISIBLE);

    }


    @Override
    public void onClick(final View view) {
        if (view == signOutButton) {
            //user already signed in
            identityManager.signOut();
            //show sign in
            signOutButton.setVisibility(View.INVISIBLE);
            signInButton.setVisibility(View.VISIBLE);

            return;
        }
        if (view == signInButton)
        {
            //Start sign in  activity
            startActivity(new Intent(this, SignInActivity.class));
            return;
        }
    }

    private void syncUserSettings() {
        // sync only if user is signed in
        if (AWSMobileClient.defaultMobileClient().getIdentityManager().isUserSignedIn()) {
            final UserSettings userSettings = UserSettings.getInstance();
            userSettings.getDataset().synchronize(new DefaultSyncCallback() {
                @Override
                public void onSuccess(final Dataset dataset, final List<Record> updatedRecords) {
                    super.onSuccess(dataset, updatedRecords);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            });
        }
    }

    public MainActivity getOuter(){
        return MainActivity.this;
    }
}
