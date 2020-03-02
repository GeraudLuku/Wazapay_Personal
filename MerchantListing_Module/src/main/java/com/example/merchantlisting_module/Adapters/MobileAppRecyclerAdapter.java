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


import com.example.merchantlisting_module.Models.Mobile;
import com.example.merchantlisting_module.R;

import java.util.ArrayList;
import java.util.List;

public class MobileAppRecyclerAdapter extends RecyclerView.Adapter<MobileAppRecyclerAdapter.MyViewHolder> implements Filterable{

    private Context context;
    private ArrayList<Mobile> mobileArrayList;
    private List<Mobile> mobileListFiltered;

    public MobileAppRecyclerAdapter(Context context, ArrayList<Mobile> mobileArrayList) {
        this.context = context;
        this.mobileArrayList = mobileArrayList;
        this.mobileListFiltered = mobileArrayList;
    }

    @NonNull
    @Override
    public MobileAppRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mobile_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileAppRecyclerAdapter.MyViewHolder holder, int position) {

        Mobile mobile = mobileListFiltered.get(position);

        holder.mMobileAppName.setText(mobile.getApp_name());

        //make it a link
//        holder.mWebssiteLink.setMovementMethod(LinkMovementMethod.getInstance());
        holder.mPlayStoreLink.setText(mobile.getPlaystore_link());
        holder.mAppStoreLink.setText(mobile.getAppstore_link());

        holder.mMobileAppDesc.setText(mobile.getDescription());


    }

    @Override
    public int getItemCount() {
        return mobileListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mMobileAppName, mPlayStoreLink, mAppStoreLink, mMobileAppDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mMobileAppName = itemView.findViewById(R.id.textView);
            mPlayStoreLink = itemView.findViewById(R.id.textView2);
            mAppStoreLink = itemView.findViewById(R.id.textView3);
            mMobileAppDesc = itemView.findViewById(R.id.textView4);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mobileListFiltered = mobileArrayList;
                } else {
                    List<Mobile> filteredList = new ArrayList<>();
                    for (Mobile row : mobileArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getApp_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mobileListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mobileListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mobileListFiltered = (ArrayList<Mobile>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
