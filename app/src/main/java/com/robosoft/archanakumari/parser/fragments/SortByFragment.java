package com.robosoft.archanakumari.parser.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.robosoft.archanakumari.parser.MainActivity;
import com.robosoft.archanakumari.parser.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortByFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

   private View mOneRow;

    private CheckBox mCheckTitle,mCheckArtist,mCheckDate;
    private Context mContext;
    public SortByFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();

        // Inflate the layout for this fragment
        mOneRow =  inflater.inflate(R.layout.fragment_sort_by, container, false);
        mCheckTitle = (CheckBox) mOneRow.findViewById(R.id.checktitle);
        mCheckArtist = (CheckBox)mOneRow.findViewById(R.id.checkartist);
        mCheckDate = (CheckBox)mOneRow.findViewById(R.id.checkreleasedate);

        mCheckTitle.setOnCheckedChangeListener(this);
        mCheckArtist.setOnCheckedChangeListener(this);
        mCheckDate.setOnCheckedChangeListener(this);
        return  mOneRow;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Intent intent = new Intent(mContext,MainActivity.class);
        if(mCheckTitle.isChecked()||mCheckArtist.isChecked()||mCheckDate.isChecked()){
           intent.putExtra("Title",mCheckTitle.isChecked());
           intent.putExtra("Artist",mCheckArtist.isChecked());
           intent.putExtra("Date",mCheckDate.isChecked());
            startActivity(intent);
        }

    }
}
