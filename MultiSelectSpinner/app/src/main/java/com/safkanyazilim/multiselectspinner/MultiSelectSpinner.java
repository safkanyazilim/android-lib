package com.safkanyazilim.multiselectspinner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dr. Y. Safkan on 28.6.2015.
 */
public class MultiSelectSpinner extends Spinner implements DialogInterface.OnMultiChoiceClickListener {

    private String[] items = null;
    private boolean[] selected = null;
    private ArrayAdapter<String> arrayAdapter;


    private void init(Context context) {
        this.arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
        super.setAdapter(this.arrayAdapter);
    }

    public MultiSelectSpinner(Context context) {
        super(context);

        this.init(context);
    }

    public MultiSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.init(context);
    }



    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selected != null && which < selected.length) {
            selected[which] = isChecked;

            arrayAdapter.clear();
            arrayAdapter.add(this.buildSelectedItemString());
        } else {
            if (selected == null) {
                throw new IllegalStateException("MultiSelectSpinner clicked without being initialized.");
            } else {
                throw new IllegalStateException("MultiSelectSpinner clicked item out of bounds.");
            }
        }
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(this.items, this.selected, this);
        builder.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new UnsupportedOperationException("Setting SpinnerAdapter is not supported on MultiSelectSpinner.");
    }

    public void setItems(String[] items) {
        this.items = items;

        this.selected = new boolean[items.length];
        arrayAdapter.clear();
        arrayAdapter.add(items[0]);
        Arrays.fill(this.selected, false);
    }

    public void setSelectedItems(String[] selectedItems) {
        Arrays.sort(selectedItems);

        for (int i = 0; i < this.selected.length; i++) {
            this.selected[i] =  Arrays.binarySearch(selectedItems, this.items[i]) >= 0;
        }

        this.arrayAdapter.clear();
        this.arrayAdapter.add(this.buildSelectedItemString());
    }

    public List<String> getSelectedStrings() {
        List<String> selectedStrings = new ArrayList<String>();
        for (int i = 0; i < this.items.length; ++i) {
            if (this.selected[i]) {
                selectedStrings.add(this.items[i]);
            }
        }
        return selectedStrings;
    }

    private String buildSelectedItemString() {
        return TextUtils.join(", ", this.getSelectedStrings());
    }

}
