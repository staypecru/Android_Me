package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;


public class BodyPartFragment extends Fragment {
    private static final String LOG_TAG = BodyPartFragment.class.getName();
    public static final String LIST_INDEX = "list_index";
    public static final String IMAGE_ID_LIST = "image_Ids";

    private int mListIndex;
    private List<Integer> mImageIds;

    public void setListIndex(int ListIndex) {
        this.mListIndex = ListIndex;
    }

    public void setImageIds(List<Integer> ImageIds) {
        this.mImageIds = ImageIds;
    }

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = rootView.findViewById(R.id.body_part_image_view);

        if (mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));
        } else {
            Log.v(LOG_TAG, "mImageIds is empty");
        }

        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mListIndex < mImageIds.size() - 1) {
                    mListIndex++;
                } else {
                    Log.v(LOG_TAG, "List size = " + mImageIds.size());
                    mListIndex = 0;
                }
                imageView.setImageResource(mImageIds.get(mListIndex));
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(LIST_INDEX, mListIndex);
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
    }
}
