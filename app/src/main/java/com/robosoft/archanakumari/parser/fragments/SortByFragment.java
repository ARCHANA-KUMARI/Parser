package com.robosoft.archanakumari.parser.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.robosoft.archanakumari.parser.Modal.SortModelClass;
import com.robosoft.archanakumari.parser.Modal.SortingCommunicator;
import com.robosoft.archanakumari.parser.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortByFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private View mOneRow;
    private ArrayList<SortModelClass> checkedstatusList = new ArrayList<>();
    private CheckBox mCheckTitle, mCheckArtist, mCheckDate;
    private Context mContext;
    SortingCommunicator sortingCommunicator;

    public SortByFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();
        sortingCommunicator = (SortingCommunicator) mContext;

        // Inflate the layout for this fragment
        mOneRow = inflater.inflate(R.layout.fragment_sort_by, container, false);
        mCheckTitle = (CheckBox) mOneRow.findViewById(R.id.checktitle);
        mCheckArtist = (CheckBox) mOneRow.findViewById(R.id.checkartist);
        mCheckDate = (CheckBox) mOneRow.findViewById(R.id.checkreleasedate);

        mCheckTitle.setOnCheckedChangeListener(this);
        mCheckArtist.setOnCheckedChangeListener(this);
        mCheckDate.setOnCheckedChangeListener(this);
        return mOneRow;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (mCheckTitle.isChecked() || mCheckArtist.isChecked() || mCheckDate.isChecked()) {

            SortModelClass sortModelClassTitle = new SortModelClass();
            sortModelClassTitle.setSortrtitle(mCheckTitle.isChecked());
            checkedstatusList.add(sortModelClassTitle);

            SortModelClass sortModelClassArtist = new SortModelClass();
            sortModelClassArtist.setSortArtist(mCheckArtist.isChecked());
            checkedstatusList.add(sortModelClassArtist);

            SortModelClass sortModelClassDate = new SortModelClass();
            sortModelClassTitle.setSortDate(mCheckDate.isChecked());
            checkedstatusList.add(sortModelClassDate);

            sortingCommunicator.toCommunicateSortedList(checkedstatusList);
            getActivity().getSupportFragmentManager().beginTransaction().remove(SortByFragment.this).commit();
        }

    }
}
