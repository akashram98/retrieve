package com.example.retrieve;
import java.util.Timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.TimerTask;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    Button Home;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    public static final int RECORD_AUDIO = 0;
    User user;
    String i1;
    Integer i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=new User();
        listView = (ListView) findViewById(R.id.listView);
        Home = findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAct4();
            }
        });
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("pulse/Data");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.user_info, R.id.userInfo, list);
        FirebaseApp.initializeApp(this);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.add("Date                Time         Value");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    user = ds.getValue(User.class);
                    list.add(user.getdate()+"   "+user.gettime()+"   "+user.getvalue());
                    i1=user.getvalue();
                    if(i1.compareTo("100")>0)
                        i=1;
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

            new Timer().schedule( new TimerTask() {

                @Override
                public void run() {
                    if(i==1) {
                        Intent i1 = new Intent();
                        i1.setClassName("com.example.android.logindemo", "com.example.android.logindemo.SecondActivity");
                        i1.putExtra("us", i);
                        startActivity(i1);
                    }
                }
            },12000);

    }
    public void openNewAct4(){
        Intent i = new Intent();
        i.setClassName("com.example.android.logindemo","com.example.android.logindemo.HomeActivity");
        startActivity(i);
    }

}
