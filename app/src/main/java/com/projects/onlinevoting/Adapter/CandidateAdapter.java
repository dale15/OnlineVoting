package com.projects.onlinevoting.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projects.onlinevoting.Models.CandidateModel;
import com.projects.onlinevoting.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CandidateModel.CandInfoModel> mData;
    private boolean is_ready;

    public CandidateAdapter(Context context, ArrayList<CandidateModel.CandInfoModel> mData, boolean is_ready) {
        this.context = context;
        this.mData = mData;
        this.is_ready = is_ready;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_voting_list, parent, false);
        holder = new ItemViewHolder(mView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;

        itemHolder.cand_name.setText(mData.get(position).getCandidate_name());


        itemHolder.btn_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View v) {
            super(v);
        }
    }

    private class ItemViewHolder extends ViewHolder {

        TextView cand_name;
        CircleImageView cand_image;
        Button btn_vote;

        ItemViewHolder(View v) {
            super(v);

            cand_name = (TextView) v.findViewById(R.id.tv_cand_name);
            cand_image = (CircleImageView) v.findViewById(R.id.candidate_img);
            btn_vote = (Button) v.findViewById(R.id.btn_vote);

        }
    }

}
