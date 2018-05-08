package com.example.sran.vodickrozzanimanja;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Fragment extends android.support.v4.app.Fragment
{
    public Fragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_viewpager, container, false);
        AppCompatButton pogledajSkole = (AppCompatButton)v.findViewById(R.id.viewpagerButton_id);
        ImageView img = (ImageView)v.findViewById(R.id.viewPagerImage_id);




        img.setBackgroundResource(getArguments().getInt("idSlike"));
        pogledajSkole.setText("Погледај школе");



        return v;
    }
}

