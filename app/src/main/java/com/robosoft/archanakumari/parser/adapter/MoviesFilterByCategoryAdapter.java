package com.robosoft.archanakumari.parser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robosoft.archanakumari.parser.MainActivity;
import com.robosoft.archanakumari.parser.R;

/**
 * Created by archanakumari on 4/2/16.
 */
public class MoviesFilterByCategoryAdapter extends ArrayAdapter implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {


    MainActivity mainActivity = new MainActivity();

    private View mOneRow;
    private TextView mTextmoviesAction, mTextmoviesComedy, mTextmoviesDrama, mTextmoviesKids, mTextmoivesSci, mTextmoviesThriller;
    private CheckBox mCheckAction, mCheckComedy, mCheckDrama, mCheckKids, mCheckSci, mCheckThriller;
    private Button mButtonOK, mButtonCancel;
    private Context mContext;

    public MoviesFilterByCategoryAdapter(Context context, int resource) {
        super(context, resource);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout mOneRow = (RelativeLayout) convertView;
        if (null == mOneRow) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mOneRow = (RelativeLayout) inflater.inflate(R.layout.filterbycategorylist, null);
        }
        mTextmoviesAction = (TextView) mOneRow.findViewById(R.id.action);
        mTextmoviesComedy = (TextView) mOneRow.findViewById(R.id.comedy);
        mTextmoviesDrama = (TextView) mOneRow.findViewById(R.id.drama);
        mTextmoviesKids = (TextView) mOneRow.findViewById(R.id.kids);
        mTextmoivesSci = (TextView) mOneRow.findViewById(R.id.sci);
        mTextmoviesThriller = (TextView) mOneRow.findViewById(R.id.thriller);

        mCheckAction = (CheckBox) mOneRow.findViewById(R.id.checkaction);
        mCheckComedy = (CheckBox) mOneRow.findViewById(R.id.checkcomedy);
        mCheckDrama = (CheckBox) mOneRow.findViewById(R.id.checkdrama);
        mCheckKids = (CheckBox) mOneRow.findViewById(R.id.checkkids);
        mCheckSci = (CheckBox) mOneRow.findViewById(R.id.checksci);
        mCheckThriller = (CheckBox) mOneRow.findViewById(R.id.checkthriller);

        mButtonOK = (Button) mOneRow.findViewById(R.id.ok);
        mButtonCancel = (Button) mOneRow.findViewById(R.id.cancel);

        mTextmoviesAction.setText(mainActivity.moviesAction + "(" + mainActivity.countAction + ")");
        mTextmoviesComedy.setText(mainActivity.moviesComedy + "(" + mainActivity.countComedy + ")");
        mTextmoviesDrama.setText(mainActivity.moviesDrama + "(" + mainActivity.countDrama + ")");
        mTextmoviesKids.setText(mainActivity.moviesKids + "(" + mainActivity.countKids + ")");
        mTextmoivesSci.setText(mainActivity.moviesScFi + "(" + mainActivity.countScFI + ")");
        mTextmoviesThriller.setText(mainActivity.moviesThriller + "(" + mainActivity.countThriller + ")");


        mCheckAction.setChecked(false);
        mCheckComedy.setChecked(false);
        mCheckDrama.setChecked(false);
        mCheckKids.setChecked(false);
        mCheckSci.setChecked(false);
        mCheckThriller.setChecked(false);

        mCheckAction.setOnCheckedChangeListener(this);
        mCheckComedy.setOnCheckedChangeListener(this);
        mCheckDrama.setOnCheckedChangeListener(this);
        mCheckKids.setOnCheckedChangeListener(this);
        mCheckSci.setOnCheckedChangeListener(this);
        mCheckThriller.setOnCheckedChangeListener(this);

        mButtonOK.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);

        return mOneRow;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(mContext, MainActivity.class);
        if (mCheckAction.isChecked()) {
            intent.putExtra("Action", mCheckAction.isChecked());
        }
        if (mCheckComedy.isChecked()) {
            intent.putExtra("Comedy", mCheckComedy.isChecked());
        }
        if (mCheckDrama.isChecked()) {
            intent.putExtra("Drama", mCheckDrama.isChecked());
        }
        if (mCheckKids.isChecked()) {
            intent.putExtra("Kids", mCheckKids.isChecked());
        }
        if (mCheckSci.isChecked()) {
            intent.putExtra("Sci-Fi", mCheckSci.isChecked());
        }
        if (mCheckThriller.isChecked()) {
            intent.putExtra("Thriller", mCheckThriller.isChecked());
        }
        mContext.startActivity(intent);

    }
}
