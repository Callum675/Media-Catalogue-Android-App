package com.example.movieandsongcatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailDAO;
import com.example.movieandsongcatalogue.data.DetailDatabase;

public class EditNote extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "EditNote";



    private Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //btnSaveNote
        Button btnSave = findViewById(R.id.btnSaveNote);
        btnSave.setOnClickListener(this);

        //Check the Intent that launched this Activity
        Intent launcher = getIntent();
        // Check if there there details of the Task to be displayed?
        if (launcher.hasExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME)) {
            // There are (or at least a task name) so instantiate this.task with those details
            detail = new Detail();
            detail.setName(launcher.getStringExtra(DetailsViewAdapter.EXTRA_DETAIL_NAME));
    } }

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
    protected void onResume() { super.onResume(); }

    @Override
    public void onClick(View v) {
        String note = String.valueOf(((TextView)findViewById(R.id.etEditNote)).getText());
        if (v.getId() == R.id.btnSaveNote){
            //log for testing
            Log.d(TAG, "onClick: btnSaveNote was pressed");
            if(note.matches("")) {
                Toast.makeText(getApplicationContext(), "Please ensure that not is not empty", Toast.LENGTH_SHORT).show();
                return;
            }else{
                //getting database
                DetailDatabase db = DetailDatabase.getDatabase(getApplicationContext());
                //getting DAO
                DetailDAO detailDAO = db.detailDAO();
                //update database
                detailDAO.updateNote(note, detail.getName());
                //inform user
                Toast.makeText(getApplicationContext(), "Note has been updated", Toast.LENGTH_SHORT).show();
                //navigate back to view details
                finish();
            }
        }
    }
}