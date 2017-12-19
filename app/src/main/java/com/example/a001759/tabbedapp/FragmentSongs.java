package com.example.a001759.tabbedapp;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSongs extends Fragment {

    public RecyclerView rView;
    public RecyclerView.Adapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    public FragmentSongs() {
        // Required empty public constructor
    }

    public static class SongsViewHolder extends RecyclerView.ViewHolder{
        View mview;

        TextView anotherTitle;
        TextView anotherArtist;
        TextView anotherLyric;

        public SongsViewHolder(View itemView) {
            super(itemView);

            mview = itemView;
            anotherTitle = mview.findViewById(R.id.cardSongTitle);
            anotherArtist = mview.findViewById(R.id.cardSongArtist);
        }

        public void setTitle(String myTitle){

            anotherTitle.setText(myTitle);

            Log.d(TAG, "Title: title" + myTitle);
        }

        public  void setArtist(String myArtist){

            anotherArtist.setText(myArtist);

        }

        public void setLyrics(String myLyrics){}
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.songs_recycler_view, container, false);


//        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("songs");
//
//        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//
//        rView = view.findViewById(R.id.recycler_view);
//        rView.setLayoutManager(linearLayoutManager);
//
//        FirebaseRecyclerOptions<ListItemSongList> myList = new FirebaseRecyclerOptions.Builder<ListItemSongList>()
//                .setQuery(firebaseDatabase, ListItemSongList.class).build();
//
//        final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListItemSongList, SongsViewHolder>(myList){
//
//            public static final String TAG = "tag";
//
//            @Override
//            public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_songs, parent, false);
//
//                return new SongsViewHolder(myView);
//            }
//
//            @Override
//            public void onBindViewHolder(SongsViewHolder holder, int position, ListItemSongList model) {
//
//                holder.anotherTitle.setText(model.getSongTitle());
//                holder.setArtist(model.getSongArtist());
//                holder.setLyrics(model.getSongLyrics());
//
//            }
//        };
//
//        rView.setAdapter(firebaseRecyclerAdapter);

        CollectionReference firestoreQuery = FirebaseFirestore.getInstance().collection("Songs");

        FirestoreRecyclerOptions<ListItemSongList> Options = new FirestoreRecyclerOptions.Builder<ListItemSongList>()
                .setQuery(firestoreQuery, ListItemSongList.class)
                .build();

        FirestoreRecyclerAdapter firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<ListItemSongList, SongsViewHolder>(Options) {

            @Override
            public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_songs, parent, false);

                return new SongsViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(SongsViewHolder holder, int position, ListItemSongList model) {

                holder.setTitle(model.getSongTitle());
                holder.setArtist(model.getSongArtist());
//                holder.anotherLyric.setText(model.getSongLyrics());


            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());

                String error = String.valueOf(Log.e("error", e.getMessage()));

                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        };


       return  view;
    }

    @Override
    public void onStart() {
        super.onStart();

//        FirebaseRecyclerOptions<ListItemSongList> myList = new FirebaseRecyclerOptions.Builder<ListItemSongList>()
//                .setQuery(databaseReference, ListItemSongList.class).build();
//
//        final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListItemSongList, SongsViewHolder>(myList){
//
//            public static final String TAG = "tag";
//
//            @Override
//            public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_songs, parent, false);
//
//                return new SongsViewHolder(myView);
//            }
//
//            @Override
//            public void onBindViewHolder(SongsViewHolder holder, int position, ListItemSongList model) {
//
//                holder.setTitle(model.getSongTitle());
//                holder.setArtist(model.getSongArtist());
//                holder.setLyrics(model.getSongLyrics());
//
//                Log.d(TAG, "Title: title" + model.songTitle);
//
//
//            }
//        };
//
//        rView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Song List");
    }

}
