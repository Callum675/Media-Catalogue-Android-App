package com.example.movieandsongcatalogue;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.movieandsongcatalogue.data.Detail;

public class DetailsViewAdapter
        extends RecyclerView.Adapter<DetailsViewAdapter.DetailsViewHolder> {

    // Names of Extras used for passing data from this Activity in an Intent
    public final static String EXTRA_DETAIL_NAME = "com.example.movieandsongcatalogue.DETAIL_NAME";
    public final static String EXTRA_DETAIL_DESCRIPTION = "com.example.movieandsongcatalogue.DETAIL_DESCRIPTION";


    // member variables for the context the adapter is working in
    private Context context;
    // the data thats going to be displayed
    private List<Detail> details;

    /**
     * Creates a new {@link DetailsViewAdapter}
     * @param context that the adapter is working in
     * @param details data to be displayed
     */
    public DetailsViewAdapter(Context context, List<Detail> details){
        super();
        // initialise the member variables
        this.context = context;
        this.details = details;
    }


    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.data_view_layout, parent, false);
        DetailsViewHolder viewHolder = new DetailsViewHolder(itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        // get the task at position
        Detail detail = this.details.get(position);

        // update the title
        TextView lblDataTitle = holder.detailItemView.findViewById(R.id.lblDataTitle);
        lblDataTitle.setText(detail.getName());
    }

    @Override
    public int getItemCount() {
        return this.details.size();
    }

    /**
     * ViewHolder for the RecyclerView that's going to display Tasks
     */
    class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View detailItemView;
        private DetailsViewAdapter adapter;

        public DetailsViewHolder(View detailItemVew, DetailsViewAdapter adapter) {
            super(detailItemVew);
            this.detailItemView = detailItemVew;
            this.adapter = adapter;
            // add a listener to the button in the taskItemView
            detailItemVew.findViewById(R.id.btnDetails).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get the clicked item's position
            int position = getAdapterPosition();

            // get the task at that position
            Detail detail = details.get(position);

            Log.d("DETAILS_RECYCLER", "user clicked on item " + detail.getName());
        }
    }
}
