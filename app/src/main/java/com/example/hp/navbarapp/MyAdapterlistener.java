package com.example.hp.navbarapp;

import android.view.View;

/**
 * Created by hp on 15/04/2018.
 */

interface MyAdapterlistener {
    void editButtonOnClick(View view, int adapterPosition);

    void deleteButtonOnClick(View view, int adapterPosition);
}
