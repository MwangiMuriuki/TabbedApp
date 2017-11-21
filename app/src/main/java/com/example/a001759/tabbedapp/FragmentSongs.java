package com.example.a001759.tabbedapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSongs extends Fragment {

    public RecyclerView rView;
    public RecyclerView.Adapter adapter;
    DatabaseReference databaseReference;


    public FragmentSongs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.songs_recycler_view, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Songs");

        rView = view.findViewById(R.id.recycler_view);
        rView.setLayoutManager(new LinearLayoutManager(getActivity()));

       return  view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ListItemSongList> myList = new FirebaseRecyclerOptions.Builder<ListItemSongList>()
                .setQuery(databaseReference, ListItemSongList.class).build();

        final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListItemSongList, SongsViewHolder>(myList){

            @Override
            public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_songs, parent, false);

                return new SongsViewHolder(myView);
            }

            @Override
            public void onBindViewHolder(SongsViewHolder holder, int position, ListItemSongList model) {

                holder.setTitle(model.getSongTitle());
                holder.setArtist(model.getSongArtist());
                holder.setLyrics(model.getSongLyrics());

            }
        };

        rView.setAdapter(adapter);

    }

    public static class SongsViewHolder extends RecyclerView.ViewHolder{

        View mview;

        public SongsViewHolder(View itemView) {
            super(itemView);

            mview = itemView;
        }

        public void setTitle(String myTitle){
            TextView songTitleView = mview.findViewById(R.id.cardSongTitle);
            songTitleView.setText(myTitle);
        }

        public  void setArtist(String myArtist){
            TextView songArtistView = mview.findViewById(R.id.cardSongArtist);
            songArtistView.setText(myArtist);

        }

        public void setLyrics(String myLyrics){}
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Song List");
    }

}
