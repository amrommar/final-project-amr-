package com.example.login2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login2.ADD_LEAGUE;
import com.example.login2.R;
import com.example.login2.adapter_league;
import com.example.login2.leagues;
import com.example.login2.myviewholder;
import com.example.login2.profileActivity;
import com.example.login2.ui.home.acadm;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class LeagueFragment extends Fragment {
    private View leagueView;
    private adapter_league adapter;
    private RecyclerView myleaguelist;
    private DatabaseReference leagueref;
    FirebaseRecyclerOptions<leagues> options;
    SharedPreferences sharedPreferences;
    String userType;
    FirebaseRecyclerAdapter<leagues, myviewholder> Radapter;
    EditText input;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        leagueView = inflater.inflate(R.layout.league_fragment, container, false);
        myleaguelist = (RecyclerView) leagueView.findViewById(R.id.league_list);
        myleaguelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        leagueref = FirebaseDatabase.getInstance().getReference().child("league");

        sharedPreferences = getActivity().getSharedPreferences("myPref", MODE_PRIVATE);
        userType = sharedPreferences.getString("userType",null);
        String email = sharedPreferences.getString("emaail",null);

        Query queries;
        if(userType.equals("Client")) {
            queries =  leagueref;
        }else{
            queries = leagueref.orderByChild("email").equalTo(email);
        }

        options = new FirebaseRecyclerOptions.Builder<leagues>()
                .setQuery(queries, leagues.class)
                .build();
        adapter = new adapter_league(options);
        myleaguelist.setAdapter(adapter);
        queries.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adapter.startListening();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "there is problem", Toast.LENGTH_SHORT).show();
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
       /* input = leagueView.findViewById(R.id.SC);
        Loaddata("");

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null)
                {
                    Loaddata(s.toString());
                }else {
                    Loaddata("");


                }

            }
        });

*/


        return leagueView;
    }
  /*  private void Loaddata(String data) {

        Query query = leagueref.orderByChild("location").startAt(data).endAt(data+"\uf8ff");
        options=new FirebaseRecyclerOptions.Builder<leagues>().setQuery(query,leagues.class).build();
        Radapter= new FirebaseRecyclerAdapter<leagues, myviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull leagues model) {
                holder.Rleaguename.setText(model.getLeague_name());
                holder.Rlocation.setText(model.getLocation());
                Picasso.get().load(model.getImage()).into(holder.Rimage);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cc = getRef(i).getKey();
                        //Toast.makeText( getContext(), "kff", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getActivity(), profileActivity.class);
                        intent.putExtra("visite_league_details",cc);
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout,parent,false);
                return new myviewholder(v);
            }
        };
        Radapter.startListening();
        myleaguelist.setAdapter(Radapter);
    }
*/
    ///////////////////////////////////////////////////////////////////////






    ///////////////////////////////////////////////////////////////////////////////////////////////


    //enable options menu in this fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    //inflate the menue
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        //to hide items from menu items
        userType = sharedPreferences.getString("userType", null);
        MenuItem addLeagueItem = menu.findItem(R.id.addLeague);
        if (userType.equals("Client") ) {
            addLeagueItem.setVisible(false);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    // handle item clicks on menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addLeague:
                Intent intent = new Intent(getActivity(), ADD_LEAGUE.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}