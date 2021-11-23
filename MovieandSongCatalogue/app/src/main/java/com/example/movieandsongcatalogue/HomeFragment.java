package com.example.movieandsongcatalogue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandsongcatalogue.data.Detail;
import com.example.movieandsongcatalogue.data.DetailDAO;
import com.example.movieandsongcatalogue.data.DetailDatabase;
import com.example.movieandsongcatalogue.data.DetailsRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //getting database
        DetailDatabase db = DetailDatabase.getDatabase(getContext());
        //getting DAO
        DetailDAO detailDAO = db.detailDAO();
        // get data from database
        List<Detail> details = detailDAO.getAll();

        // get the RecyclerView on the UI
        RecyclerView recyclerView = v.findViewById(R.id.rv_DetailsView);
        // create a new Adapter for the Media
        RecyclerView.Adapter adapter = new DetailsViewAdapter(getContext(), details);
        // set the recycler view's adapter
        recyclerView.setAdapter(adapter);
        // setup the layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;

    }
}