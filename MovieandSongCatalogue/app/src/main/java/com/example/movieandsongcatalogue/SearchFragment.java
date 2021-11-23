package com.example.movieandsongcatalogue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailDAO;
import com.example.movieandsongcatalogue.data.DetailDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SearchFragment";

    //global vars
    String saveName;
    String saveDescription;
    String saveLink;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //btn search
        Button btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        //btn save
        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSearch) {
            String searchName = String.valueOf(((TextView)getView().findViewById(R.id.etSearch)).getText());
            if (searchName.matches("")) {
                Toast.makeText(getActivity().getApplicationContext(), "Your Search is Empty", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //build uri
                Uri uri = Uri.parse("https://kgsearch.googleapis.com/v1/entities:search?");
                String key = "AIzaSyC_J8waxKsPEFdofdd2FnKBHLY29BMPPcU";
                String limit = "1";
                String movie = "Movie";
                String book = "Book";
                String tv = "TVSeries";
                String music = "MusicRecording";
                String AMusic = "MusicAlbum";
                Uri.Builder uriBuilder = uri.buildUpon();
                uriBuilder.appendQueryParameter("query", searchName);
                uriBuilder.appendQueryParameter("key", key);
                uriBuilder.appendQueryParameter("limit", limit);
                uriBuilder.appendQueryParameter("types", movie);
                uriBuilder.appendQueryParameter("types", book);
                uriBuilder.appendQueryParameter("types", music);
                uriBuilder.appendQueryParameter("types",AMusic);
                uriBuilder.appendQueryParameter("types", tv);
                //create final uri
                uri = uriBuilder.build();

                //volley request
                StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        //process data
                        //interested in result>name, detailedDescription>articleBody, url
                        try{
                            JSONObject rootObject = new JSONObject(response);
                            JSONArray listarray = rootObject.getJSONArray("itemListElement");
                            for(int i=0, j = listarray.length(); i < j; i++){
                                JSONObject listIndex = listarray.getJSONObject(i);
                                JSONObject resultObj = listIndex.getJSONObject("result");
                                String name = resultObj.getString("name");
                                JSONObject descriptionObj = resultObj.getJSONObject("detailedDescription");
                                String description = descriptionObj.getString("articleBody");
                                String link = descriptionObj.getString("url");

                                //logs for testing
                                Log.d(TAG, name);
                                Log.d(TAG, description);
                                Log.d(TAG, link);

                                //giving values to globals
                                saveName = name;
                                saveDescription = description;
                                saveLink = link;

                                //displaying data
                                TextView lblResult = (TextView) getView().findViewById(R.id.lblResult);
                                lblResult.setText(getString(R.string.search_name) + " " + name);

                                TextView lblDescription = (TextView) getView().findViewById(R.id.lblResultDescription);
                                lblDescription.setText(getString(R.string.search_description) + " " + description);

                                TextView lbllink = (TextView) getView().findViewById(R.id.lblLink);
                                lbllink.setText(getString(R.string.search_link) + " " + link);
                            }
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.search_download_error), Toast.LENGTH_LONG);
                        Log.e(TAG, error.getLocalizedMessage());
                    }
                });
                //request queue
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(request);
            }
        }
        else if (v.getId() == R.id.btnSave) {
            //log for texting
            Log.d(TAG, "onClick: btnSave was pressed");
            //getting database
            DetailDatabase db = DetailDatabase.getDatabase(getContext());
            //getting DAO
            DetailDAO detailDAO = db.detailDAO();

            //giving values to object
            Detail detail = new Detail();
            detail.setName(saveName);
            detail.setDescription(saveDescription);
            detail.setLink(saveLink);
            //log for testing
            Log.d(TAG, String.valueOf(detail));


            //storing data
            //checking if data exists

            if (detailDAO.getAll().contains(saveName)) {
                //informing user
                Toast.makeText(getActivity().getApplicationContext(), saveName + "is already saved", Toast.LENGTH_LONG).show();
                return;
            }
            else if (saveName == null){
                Toast.makeText(getActivity().getApplicationContext(), "please search before saving", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                //insert data
                detailDAO.insert(detail);

                //informing user
                Toast.makeText(getActivity().getApplicationContext(), saveName + " was saved", Toast.LENGTH_LONG).show();
           }
        }
    }
}