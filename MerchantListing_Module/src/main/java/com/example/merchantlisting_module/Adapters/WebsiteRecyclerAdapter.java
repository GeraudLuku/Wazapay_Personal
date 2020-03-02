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


import com.example.merchantlisting_module.Models.Website;
import com.example.merchantlisting_module.R;

import java.util.ArrayList;
import java.util.List;

public class WebsiteRecyclerAdapter extends RecyclerView.Adapter<WebsiteRecyclerAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Website> websiteArrayList;
    private List<Website> filteredwebsiteArrayList;

    public WebsiteRecyclerAdapter(Context context, ArrayList<Website> websiteArrayList) {
        this.context = context;
        this.websiteArrayList = websiteArrayList;
        this.filteredwebsiteArrayList = websiteArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.website_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Website website = filteredwebsiteArrayList.get(position);

        holder.mWebsiteName.setText(website.getWebsite_name());

        //make it a link
//        holder.mWebssiteLink.setMovementMethod(LinkMovementMethod.getInstance());
        holder.mWebssiteLink.setText(website.getWebsite_url());

        holder.mWebsiteDesc.setText(website.getWebsite_description());

    }

    @Override
    public int getItemCount() {
        return filteredwebsiteArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mWebsiteName, mWebssiteLink, mWebsiteDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mWebsiteName = itemView.findViewById(R.id.textView);
            mWebssiteLink = itemView.findViewById(R.id.textView2);
            mWebsiteDesc = itemView.findViewById(R.id.textView3);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredwebsiteArrayList = websiteArrayList;
                } else {
                    List<Website> filteredList = new ArrayList<>();
                    for (Website row : websiteArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getWebsite_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    filteredwebsiteArrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredwebsiteArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredwebsiteArrayList = (ArrayList<Website>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
