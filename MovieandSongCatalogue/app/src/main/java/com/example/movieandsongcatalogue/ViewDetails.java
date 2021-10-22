package com.example.movieandsongcatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailsRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewDetails extends AppCompatActivity {

    private static final String KEY_DETAIL_NAME = "name";
    private static final String KEY_DETAIL_DESCRIPTION = "description";

    private Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        //  Check the Intent that launched this Activity
        Intent launcher = getIntent();
        // Check if there there details of the Task to be displayed?
        if (launcher.hasExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME)) {
            // There are (or at least a task name) so instantiate this.task with those details
            detail = new Detail();
            detail.setName(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME));
            detail.setDescription(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_DESCRIPTION));
        }
        else if (savedInstanceState != null) {
                // recreate the task
                detail = new Detail();
                detail.setName(savedInstanceState.getString(KEY_DETAIL_NAME));
                detail.setDescription(savedInstanceState.getString(KEY_DETAIL_DESCRIPTION));
            }
        else {
                detail = DetailsRepository.getRepository(getApplicationContext()).getDetail();
            }
            displayDetails(detail);

        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // store all the details of the task
        outState.putString(KEY_DETAIL_NAME, detail.getName());
        outState.putString(KEY_DETAIL_DESCRIPTION, detail.getDescription());

    }

    /**
     * Updates the UI to display details of task
     * @param detail
     */
    private void displayDetails(Detail detail) {
        // display the task name
        TextView tv_viewTaskName = findViewById(R.id.lblMediaTitle);
        tv_viewTaskName.setText(detail.getName());

        // display the task description
        TextView tv_viewTaskDescription = findViewById(R.id.lblMediaDescription);
        tv_viewTaskDescription.setText(detail.getDescription());

        }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}