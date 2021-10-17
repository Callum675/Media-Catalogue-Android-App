package com.example.movieandsongcatalogue.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class DetailsRepository {

    private static DetailsRepository INSTANCE;

    private Context context;

    public static DetailsRepository getRepository(Context context) {
        if (INSTANCE == null) {
            synchronized (DetailsRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new DetailsRepository();
                INSTANCE.context = context;
            }
        }
        return INSTANCE;
    }

    public List<Details> getDetails(int number){
        List<Details> details = new ArrayList<>(number);
        for (int i = 0; i < number; i++){
            Details d = getDetails();
            details.add(d);
        }
        return details;
    }

    public Details getDetails(){
        Details d = new Details();

        // set the name
        d.setName("Movie");

        // set the description
        d.setDescription("aaaaaaaaaaa");

        return d;
    }
}
