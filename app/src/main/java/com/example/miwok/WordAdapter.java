package com.example.miwok;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int color = 0;
    Context mContext;

    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.color = color;
        mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokWord);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.defaultWord);
        // Get the version number from the current Word object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_view);

        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.linearLayout);

        int intColor = ContextCompat.getColor(getContext(), color);
        linearLayout.setBackgroundColor(intColor);

        if (currentWord.hasImage()){
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getmResourceId());

        }
        else{
            iconView.setVisibility(View.GONE);


        }

//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "Audio played", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
