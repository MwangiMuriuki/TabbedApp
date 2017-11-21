package com.example.a001759.tabbedapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddSong extends Fragment implements View.OnClickListener {
    TextInputLayout songTitle, songArtist,songLyrics;
    Button clear, add;

    public FragmentAddSong() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_song, container, false);

        songTitle = v.findViewById(R.id.songTitleET);
        songArtist = v.findViewById(R.id.songArtistET);
        songLyrics = v.findViewById(R.id.songLyricsET);

        clear = v.findViewById(R.id.clearBtn);
        add = v.findViewById(R.id.addBtn);

        clear.setOnClickListener(this);
        add.setOnClickListener(this);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(" ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clearBtn:
                mthdClear();
                break;

            case R.id.addBtn:
                mthdAdd();
                break;
        }

    }

    private void mthdClear() {

        Toast.makeText(getContext(), "Timothy clicked the Clear Button", Toast.LENGTH_LONG).show();

    }

    private void mthdAdd() {

        String Artist = songArtist.getEditText().getText().toString();
        String Title = songTitle.getEditText().getText().toString();
        String Lyrics = songLyrics.getEditText().getText().toString();

        songTitle.setError(null);
        songArtist.setError(null);
        songLyrics.setError(null);

        View focusView = null;
        boolean cancel = false;

        if(TextUtils.isEmpty(Title)){
            songTitle.setError(getString(R.string.enter_title));
            focusView = songTitle;
            cancel = true;
        }
        else if ((TextUtils.isEmpty(Artist))){
                songArtist.setError(getString(R.string.enter_artist_name));
                focusView = songArtist;
                cancel = true;
        }
        else if(TextUtils.isEmpty(Lyrics)){

                    songLyrics.setError(getString(R.string.enter_lyrics));
                    focusView = songLyrics;
                    cancel = true;
        }
        if (cancel) {
            // There was an error; Focus the form field with an error.
            focusView.requestFocus();
        }

        else{

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("Songs");
//

            HashMap<String, String> UserMap = new HashMap<>();
            UserMap.put("title", Title);
            UserMap.put("artist", Artist);
            UserMap.put("lyrics", Lyrics);

            myRef.push().setValue(UserMap);

            Toast.makeText(getContext(), "Song added Successfully!!!", Toast.LENGTH_LONG).show();

        }


    }

}
