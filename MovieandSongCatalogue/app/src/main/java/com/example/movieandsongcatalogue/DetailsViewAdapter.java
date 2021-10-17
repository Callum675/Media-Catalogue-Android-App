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

import com.example.movieandsongcatalogue.data.Details;

public class DetailsViewAdapter extends RecyclerView.Adapter<DetailsViewAdapter.DetailsViewHolder> {

    //member variables
    private Context context;
    //data
    private List<Details> details;

    public DetailsViewAdapter(Context context, List<Details> details){
        super();
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View detailsView = LayoutInflater.from(context).inflate(R.layout.data_view_layout, parent, false );
        DetailsViewHolder viewHolder = new DetailsViewHolder(detailsView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        //get item at position
        Details details = this.details.get(position);

        //update Title
        TextView lblDataTitle = holder.detailsView.findViewById(R.id.lblDataTitle);
        lblDataTitle.setText(details.getName());
    }

    @Override
    public int getItemCount() {
        return this.details.size();
    }

    //View holder for Recycler view
     class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View detailsView;
        private DetailsViewAdapter adapter;

        public DetailsViewHolder(View detailsView, DetailsViewAdapter adapter){
            super (detailsView);
            this.detailsView = detailsView;
            this.adapter = adapter;
            this.detailsView.findViewById(R.id.btnDetails).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get the clicked item's position
            int position = getAdapterPosition();

            // get the task at that position
            Details detail = details.get(position);

            //do things
            Log.d("TASK_RECYCLER", "user clicked on item " + detail.getName());

        }
    }
}
