package com.example.merchantlisting_module.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.merchantlisting_module.Models.SalesPoint;
import com.example.merchantlisting_module.R;

import java.util.ArrayList;
import java.util.List;

public class PointOfSalesRecyclerAdapter extends RecyclerView.Adapter<PointOfSalesRecyclerAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<SalesPoint> salesPointArrayList;
    private List<SalesPoint> salesPointListFiltered;

    public PointOfSalesRecyclerAdapter(Context context, ArrayList<SalesPoint> salesPointArrayList) {
        this.context = context;
        this.salesPointArrayList = salesPointArrayList;
        this.salesPointListFiltered = salesPointArrayList;
    }

    @NonNull
    @Override
    public PointOfSalesRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_point_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalesPoint salesPoint = salesPointListFiltered.get(position);

        holder.mStoreName.setText(salesPoint.getName());

        //make it a link
//        holder.mWebssiteLink.setMovementMethod(LinkMovementMethod.getInstance());
        holder.mStoreLocation.setText(salesPoint.getAddresse());
        holder.mStoreItems.setText(salesPoint.getItems());

        holder.mStoreDescription.setText(salesPoint.getDescription());
    }

    @Override
    public int getItemCount() {
        return salesPointListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mStoreName, mStoreLocation, mStoreItems, mStoreDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mStoreName = itemView.findViewById(R.id.textView);
            mStoreLocation = itemView.findViewById(R.id.textView2);
            mStoreItems = itemView.findViewById(R.id.textView3);
            mStoreDescription = itemView.findViewById(R.id.textView4);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    salesPointListFiltered = salesPointArrayList;
                } else {
                    List<SalesPoint> filteredList = new ArrayList<>();
                    for (SalesPoint row : salesPointArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    salesPointListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = salesPointListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                salesPointListFiltered = (ArrayList<SalesPoint>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
