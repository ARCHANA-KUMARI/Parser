package com.robosoft.archanakumari.parser.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.robosoft.archanakumari.parser.Modal.CountCategory;
import com.robosoft.archanakumari.parser.R;
import com.robosoft.archanakumari.parser.adapter.MoviesFilterByCategoryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterByCategory extends Fragment {

    private ListView mListView;
    private Context mContext;
    private View mOneRow;
    private ArrayList<CountCategory> mList = new ArrayList<>();
    public FilterByCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = container.getContext();
        // Inflate the layout for this fragment
        mOneRow = inflater.inflate(R.layout.fragment_filter_by_category, container, false);
        mListView = (ListView)mOneRow.findViewById(R.id.listfragment);
        return mOneRow;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int countaction = bundle.getInt("Action");
        Log.i("Hello", "Count is in FilterByFragment" + countaction);
        int countComedy = bundle.getInt("Comedy");
        int countDrama = bundle.getInt("Drama");
        int countKids = bundle.getInt("Kids");
        int countSci = bundle.getInt("Sci");
        int countThriller = bundle.getInt("Thriller");
        CountCategory countCategory = new CountCategory(countaction,countComedy,countDrama,countKids,countSci,countThriller);
        mList.add(countCategory);
        MoviesFilterByCategoryAdapter moviesFilterByCategoryAdapter = new MoviesFilterByCategoryAdapter(getActivity(),-1,mList);
        mListView.setAdapter(moviesFilterByCategoryAdapter);
    }
}
