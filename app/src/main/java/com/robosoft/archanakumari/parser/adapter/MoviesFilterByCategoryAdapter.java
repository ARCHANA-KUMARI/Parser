package com.robosoft.archanakumari.parser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robosoft.archanakumari.parser.Modal.FilterModelClass;
import com.robosoft.archanakumari.parser.R;

import java.util.ArrayList;

/**
 * Created by archanakumari on 4/2/16.
 */
public class MoviesFilterByCategoryAdapter extends ArrayAdapter<FilterModelClass> {

    static class ViewHolder {
        protected TextView mTextMoviesName;
        protected CheckBox mCheck;
    }

    private Context mContext;
    private ArrayList<FilterModelClass> arrayList;
    public ArrayList<Boolean> chekBoxStatusList = new ArrayList<>();

    public MoviesFilterByCategoryAdapter(Context context, int resource, ArrayList<FilterModelClass> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.arrayList = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout mOneRow = (LinearLayout) convertView;
        ViewHolder viewHolder = null;
        FilterModelClass filterModelClass = null;
        if (null == mOneRow) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mOneRow = (LinearLayout) inflater.inflate(R.layout.childtfilterlistview, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextMoviesName = (TextView) mOneRow.findViewById(R.id.text);
            viewHolder.mCheck = (CheckBox) mOneRow.findViewById(R.id.check);
            filterModelClass = arrayList.get(position);
            viewHolder.mTextMoviesName.setText(filterModelClass.getmCategoryname() + "(" + filterModelClass.getmCount() + ")");
            viewHolder.mCheck.setChecked(filterModelClass.ismSelectedValue());
            viewHolder.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    arrayList.get(position).setmSelectedValue(buttonView.isChecked()); // Set the value of checkbox to maintain its state

                }
            });
            mOneRow.setTag(viewHolder);
            mOneRow.setTag(R.id.text, viewHolder.mTextMoviesName);
            mOneRow.setTag(R.id.check, viewHolder.mCheck);

        } else {
            viewHolder = (ViewHolder) mOneRow.getTag();
        }
        viewHolder.mCheck.setTag(position);
        return mOneRow;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

}
