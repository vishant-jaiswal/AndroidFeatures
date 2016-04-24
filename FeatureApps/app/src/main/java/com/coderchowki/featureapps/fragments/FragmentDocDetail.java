package com.coderchowki.featureapps.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderchowki.featureapps.Doc;
import com.coderchowki.featureapps.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDocDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDocDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DOC = "doc";

    // TODO: Rename and change types of parameters
    private Doc mDoc;


    public FragmentDocDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param doc Parameter 1.
     * @return A new instance of fragment FragmentDocDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDocDetail newInstance(Doc doc) {
        FragmentDocDetail fragment = new FragmentDocDetail();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DOC, doc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDoc = getArguments().getParcelable(ARG_DOC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doc_detail, container, false);
        TextView title = (TextView) view.findViewById(R.id.fragment_doc_detail_title);
        title.setText(mDoc.getTitle());
        TextView body = (TextView) view.findViewById(R.id.fragment_doc_detail_body);
        body.setText(mDoc.getText());
        return view;
    }

}
