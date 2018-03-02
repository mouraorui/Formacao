package com.example.android.sunshinev2;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASTAG = " #SunshineApp";

    private String mForecast;
    private TextView mWeatherDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);

        Intent intentThatStartedThisActivity = getIntent();


        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
                mForecast = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mWeatherDisplay.setText(mForecast);
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_share){
            shareText(mForecast);
            return true;
        }

        if (id == R.id.action_settings){
            Intent starSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(starSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareText(String text){
        String mimeType = "text/plain";


        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType )
                .setText(text + FORECAST_SHARE_HASTAG)
                .startChooser();
    }
}
