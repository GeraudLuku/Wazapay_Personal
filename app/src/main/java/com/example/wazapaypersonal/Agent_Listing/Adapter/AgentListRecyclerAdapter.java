package com.example.wazapaypersonal.Agent_Listing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wazapaypersonal.Agent_Listing.Model.Agent;
import com.example.wazapaypersonal.R;

import java.util.ArrayList;
import java.util.List;

public class AgentListRecyclerAdapter extends RecyclerView.Adapter<AgentListRecyclerAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Agent> agentArrayList;
    private List<Agent> agentListFiltered;

    public AgentListRecyclerAdapter(Context context, ArrayList<Agent> agentArrayList) {
        this.context = context;
        this.agentArrayList = agentArrayList;
        this.agentListFiltered = agentArrayList;
    }

    @NonNull
    @Override
    public AgentListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgentListRecyclerAdapter.MyViewHolder holder, int position) {

        Agent agent = agentListFiltered.get(position);

        holder.mAgentName.setText(agent.getName());
        holder.mMaxTransAmt.setText(agent.getMax_trans_amount());

        //get location name from lat and long
        holder.mAgentLocation.setText("Buea, Cameroon");

    }

    @Override
    public int getItemCount() {
        return agentListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mAgentName, mAgentLocation, mMaxTransAmt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mAgentName = itemView.findViewById(R.id.textView);
            mAgentLocation = itemView.findViewById(R.id.textView2);
            mMaxTransAmt = itemView.findViewById(R.id.textView4);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    agentListFiltered = agentArrayList;
                } else {
                    List<Agent> filteredList = new ArrayList<>();
                    for (Agent row : agentArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    agentListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = agentListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                agentListFiltered = (ArrayList<Agent>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
