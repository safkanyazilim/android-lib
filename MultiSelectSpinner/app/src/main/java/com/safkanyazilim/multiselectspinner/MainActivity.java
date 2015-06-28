package com.safkanyazilim.multiselectspinner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private MultiSelectSpinner multiSelectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] array = { "one", "two", "three" };
        this.multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.multi_spinner);
        multiSelectSpinner.setItems(array);
    }


}
