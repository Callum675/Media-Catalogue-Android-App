package com.example.movieandsongcatalogue;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieandsongcatalogue.data.Search;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SearchFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Button btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
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
                //get value of search
                Search search = new Search();
                search.setSearch(searchName);

                //build uri
                Uri uri = Uri.parse("https://kgsearch.googleapis.com/v1/entities:search?query=");
                String key = "AIzaSyC_J8waxKsPEFdofdd2FnKBHLY29BMPPcU";
                String limit = "1";
                String tmovie = "Movie";
                String tbook = "Book";
                String tmusic = "MusicRecording";
                Uri.Builder uriBuilder = uri.buildUpon();
                uriBuilder.appendQueryParameter("query", searchName);
                uriBuilder.appendQueryParameter("key", key);
                uriBuilder.appendQueryParameter("limit", limit);
                uriBuilder.appendQueryParameter("types", tmovie);
                uriBuilder.appendQueryParameter("types", tbook);
                uriBuilder.appendQueryParameter("types", tmusic);
                //create final uri
                uri = uriBuilder.build();

                //volley request
                StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
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


                Navigation.findNavController(v).navigate(R.id.action_searchFragment_to_detailsFragment);
            }
        }
    }
}