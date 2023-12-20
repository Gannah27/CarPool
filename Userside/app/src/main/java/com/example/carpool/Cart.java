package com.example.carpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerview;
    UserDb user_db;
    UserDao userDao;
    FirebaseAuth Auth= FirebaseAuth.getInstance();
    FirebaseUser user=Auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        user_db=UserDb.getDatabase(this);
        Log.d("Cart", "onCreate:we are hereee ");
        userDao=user_db.userDao();
        recyclerview=findViewById(R.id.recycle02);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("SET", "CHECK 1 ");
        final User_adapter adapter = new User_adapter();
        recyclerview.setAdapter(adapter);
        userDao.getAlltrips(user.toString()).observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(List<User> users) {
                adapter.setMitemArrayList(users);
                Log.d("TAG", "onChanged:we are here ");
            }
        });
    }
}