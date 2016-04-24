package com.coderchowki.featureapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderchowki.featureapps.fragments.FragmentDocList;
import com.coderchowki.featureapps.utils.DocUtils;

import java.util.ArrayList;


/**
 * Created by Matt Boutell on 12/1/2015.
 */
public class DocListAdapter extends RecyclerView.Adapter<DocListAdapter.ViewHolder> {

    private ArrayList<Doc> mDocs;
    private FragmentDocList.CallBack mCallback;


    public DocListAdapter(Context context, FragmentDocList.CallBack callback) {
        mDocs = DocUtils.loadDocs(context);
        mCallback = callback;
    }

    @Override
    public DocListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_doc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocListAdapter.ViewHolder holder, int position) {
        final Doc doc = mDocs.get(position);
        holder.mTitleTextView.setText(doc.getTitle());
        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onDocSelected(doc);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDocs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.document_title_text_view);
        }
    }
}
