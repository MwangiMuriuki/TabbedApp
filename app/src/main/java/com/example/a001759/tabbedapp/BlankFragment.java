package com.example.a001759.tabbedapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    RecyclerView rView;
    RecyclerView.Adapter adapter;


    public BlankFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.songs_recycler_view, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.recycler_view);
        rView.setLayoutManager(linearLayoutManager);

        Query firestoreQuery = FirebaseFirestore.getInstance().collection("Songs");

        FirestoreRecyclerOptions<ListItemSongList> Options = new FirestoreRecyclerOptions.Builder<ListItemSongList>()
                .setQuery(firestoreQuery, ListItemSongList.class)
                .build();

        FirestoreRecyclerAdapter firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<ListItemSongList, FragmentSongs.SongsViewHolder>(Options) {

            @Override
            protected void onBindViewHolder(FragmentSongs.SongsViewHolder holder, int position, ListItemSongList model) {

                holder.setTitle(model.getSongTitle());
                holder.setArtist(model.getSongArtist());
//                holder.anotherLyric.setText(model.getSongLyrics());

            }

            @Override
            public FragmentSongs.SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_songs, parent, false);

                return new FragmentSongs.SongsViewHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());

                String error = String.valueOf(Log.e("error", e.getMessage()));

                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        };

        rView.setAdapter(firestoreRecyclerAdapter);

        return v;
    }
}
