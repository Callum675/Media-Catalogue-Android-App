package com.example.movieandsongcatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailDAO;
import com.example.movieandsongcatalogue.data.DetailDatabase;
import com.example.movieandsongcatalogue.data.DetailsRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewDetails extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "View Details";

    private static final String KEY_DETAIL_NAME = "name";
    private static final String KEY_DETAIL_DESCRIPTION = "description";
    private static final String KEY_DETAIL_LINK = "link";
    private static final String KEY_DETAIL_NOTE = "note";

    private static final String EXTRA_DETAIL_NOTENAME = "com.example.movieandsongcatalogue.DETAIL_NAME";

    private Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        //btnDelete
        Button btnDelete = findViewById(R.id.btnDeleteNote);
        btnDelete.setOnClickListener(this);
        //btnEdit
        Button btnEdit = findViewById(R.id.btnEditNote);
        btnEdit.setOnClickListener(this);

        //Check the Intent that launched this Activity
        Intent launcher = getIntent();
        // Check if there there details of the Task to be displayed?
        if (launcher.hasExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME)) {
            // There are (or at least a task name) so instantiate this.task with those details
            detail = new Detail();
            detail.setName(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME));
            detail.setDescription(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_DESCRIPTION));
            detail.setLink(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_LINK));
            detail.setNote(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_NOTE));
        }
        else if (savedInstanceState != null) {
                // recreate the task
                detail = new Detail();
                detail.setName(savedInstanceState.getString(KEY_DETAIL_NAME));
                detail.setDescription(savedInstanceState.getString(KEY_DETAIL_DESCRIPTION));
                detail.setLink(savedInstanceState.getString(KEY_DETAIL_LINK));
                detail.setNote(savedInstanceState.getString(KEY_DETAIL_NOTE));
            }
        else {
                detail = DetailsRepository.getRepository(getApplicationContext()).getDetail();
            }
            displayDetails(detail);

        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // store all the details
        outState.putString(KEY_DETAIL_NAME, detail.getName());
        outState.putString(KEY_DETAIL_DESCRIPTION, detail.getDescription());
        outState.putString(KEY_DETAIL_LINK, detail.getLink());
        outState.putString(KEY_DETAIL_NOTE, detail.getNote());
    }

    /**
     * Updates the UI to display details of task
     * @param detail
     */
    private void displayDetails(Detail detail) {
        // display the name
        TextView name = findViewById(R.id.lblMediaTitle);
        name.setText(detail.getName());

        // display the description
        TextView description = findViewById(R.id.lblMediaDescription);
        description.setText(detail.getDescription());

        // display the Link
        TextView link = findViewById(R.id.lblMediaLink);
        link.setText(detail.getLink());

        TextView note = findViewById(R.id.lblMediaNote);
        note.setText(detail.getNote());
        }


    @Override
    protected void onStart() { super.onStart(); }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() { super.onDestroy(); }

    @Override
    protected void onPause() { super.onPause(); }

    @Override
    protected void onResume() { super.onResume();}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDeleteNote) {
            //log for testing
            Log.d(TAG, "onClick: btnDelete was pressed");

            //getting database
            DetailDatabase db = DetailDatabase.getDatabase(getApplicationContext());
            //getting DAO
            DetailDAO detailDAO = db.detailDAO();

            //creating executor
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //delete entry in detail database using DAO
                    Log.d(TAG, String.valueOf(detail)); //logging the value of detail for testing
                    detailDAO.deleteByName(detail.getName());
                }
            });

            //informing user
            Toast.makeText(getApplicationContext(), detail.getName() + " was deleted", Toast.LENGTH_LONG).show();

            //start new main activity
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(refresh);//Start the same Activity
            finish(); //finish current Activity.

        }else if (v.getId() == R.id.btnEditNote) {
            //navigate to editNote activity
            Intent i = new Intent(getApplicationContext(), EditNote.class);
            i.putExtra(EXTRA_DETAIL_NOTENAME, detail.getName());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //end current activity
            finish();
            startActivity(i);
        }
    }
}