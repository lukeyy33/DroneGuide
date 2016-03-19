package com.lukewaugh.droneguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public MathClass theMaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView txtLatEnd = (TextView) findViewById(R.id.latText);
        final TextView txtLonEnd = (TextView) findViewById(R.id.longText);

        Button btnLogin = (Button) findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getOuter(), LoginActivity.class);
                startActivity(intent);

            }
        });


        Button btnGo = (Button) findViewById(R.id.startBtn);
        btnGo.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getOuter(), MapsActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("END_LAT",txtLatEnd.getText().toString());
                        extras.putString("END_LON",txtLonEnd.getText().toString());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                }
        );

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

    public MainActivity getOuter(){
        return MainActivity.this;
    }
}
