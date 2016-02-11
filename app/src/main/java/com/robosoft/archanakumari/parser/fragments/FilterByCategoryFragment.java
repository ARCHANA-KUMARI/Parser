package com.robosoft.archanakumari.parser.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.robosoft.archanakumari.parser.Modal.Cummunicator;
import com.robosoft.archanakumari.parser.Modal.FilterModelClass;
import com.robosoft.archanakumari.parser.R;
import com.robosoft.archanakumari.parser.adapter.MoviesFilterByCategoryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterByCategoryFragment extends Fragment {

    private ListView mListView;
    private Context mContext;
    private View mOneRow;
    private Button mOkButton,mCancelButton;
    Cummunicator cummunicator;
    private ArrayList<FilterModelClass> mList = new ArrayList<>();
    public FilterByCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = container.getContext();
        cummunicator = (Cummunicator) mContext;
        // Inflate the layout for this fragment
        mOneRow = inflater.inflate(R.layout.fragment_filter_by_category, container, false);
        mListView = (ListView)mOneRow.findViewById(R.id.listfragment);
        mOkButton = (Button) mOneRow.findViewById(R.id.ok);
        mCancelButton = (Button) mOneRow.findViewById(R.id.cancel);

        return mOneRow;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        mList = (ArrayList<FilterModelClass>) bundle.getSerializable("Name");
        MoviesFilterByCategoryAdapter moviesFilterByCategoryAdapter = new MoviesFilterByCategoryAdapter(getActivity(),-1,mList);
         mListView.setAdapter(moviesFilterByCategoryAdapter);
         mOkButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 cummunicator.toCommunicate(mList);
                 getActivity().getSupportFragmentManager().beginTransaction().remove(FilterByCategoryFragment.this).commit();
             }
         });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(FilterByCategoryFragment.this).commit();
            }
        });
    }

}
