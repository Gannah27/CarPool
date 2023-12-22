package com.example.carpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class history extends AppCompatActivity {

    RecyclerView recyclerview;
    User_history user_db;
    UserDao userDao;
    FirebaseAuth Auth= FirebaseAuth.getInstance();
    FirebaseUser user=Auth.getCurrentUser();
    FirebaseDatabase db_f=FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        user_db=User_history.getDatabase(this);
        Log.d("Cart", "onCreate:we are hereee ");
        userDao=user_db.userDao();
        recyclerview=findViewById(R.id.recycle03);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("SET", "CHECK 1 ");
        final history_adapter adapter = new history_adapter();

        recyclerview.setAdapter(adapter);
        userDao.getAlltrips_cart(user.getUid()).observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(List<User> users) {
                adapter.setMitemArrayList(users);
                Log.d("TAG", "onChanged:we are here ");
            }
        });
    }
}