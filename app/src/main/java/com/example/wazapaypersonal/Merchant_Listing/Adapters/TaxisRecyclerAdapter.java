package com.example.wazapaypersonal.Merchant_Listing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wazapaypersonal.Merchant_Listing.Models.Taxi;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;
import java.util.List;

public class TaxisRecyclerAdapter extends RecyclerView.Adapter<TaxisRecyclerAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Taxi> taxiArrayList;
    private List<Taxi> taxiListFiltered;

    public TaxisRecyclerAdapter(Context context, ArrayList<Taxi> taxiArrayList) {
        this.context = context;
        this.taxiArrayList = taxiArrayList;
        this.taxiListFiltered = taxiArrayList;
    }

    @NonNull
    @Override
    public TaxisRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.taxi_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Taxi taxi = taxiListFiltered.get(position);

        holder.mTaxiName.setText(taxi.getName());

        //make it a link
//        holder.mWebssiteLink.setMovementMethod(LinkMovementMethod.getInstance());
        holder.mTaxiPhone.setText(taxi.getPhonenumber());
        holder.mTaxiLocation.setText(taxi.getLocation());

    }

    @Override
    public int getItemCount() {
        return taxiListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTaxiName, mTaxiPhone, mTaxiLocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTaxiName = itemView.findViewById(R.id.textView);
            mTaxiPhone = itemView.findViewById(R.id.textView2);
            mTaxiLocation = itemView.findViewById(R.id.textView3);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    taxiListFiltered = taxiArrayList;
                } else {
                    List<Taxi> filteredList = new ArrayList<>();
                    for (Taxi row : taxiArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    taxiListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = taxiListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                taxiListFiltered = (ArrayList<Taxi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

