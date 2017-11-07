package com.example.a001759.tabbedapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


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

        Toast.makeText(getContext(), "Clear Button clicked", Toast.LENGTH_LONG).show();

    }

    private void mthdAdd() {

        Toast.makeText(getContext(), "Add button clicked", Toast.LENGTH_LONG).show();

    }


}
