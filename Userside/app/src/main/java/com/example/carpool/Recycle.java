package com.example.carpool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Recycle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recycle extends Fragment implements  Myadapter.ItemClicklistener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    Item item;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private ArrayList<Item> itemsArraylist;
    private RecyclerView recyclerview;
    public Recycle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recyle.
     */
    // TODO: Rename and change types and number of parameters
    public static Recycle newInstance(String param1, String param2,String param3,String param4) {
        Recycle fragment = new Recycle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recyle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataIntialize();
        recyclerview=view.findViewById(R.id.recycle01);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(false);
        Myadapter adapter = new Myadapter(getContext(),itemsArraylist,this::onItemClick);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void dataIntialize() {
        itemsArraylist = new ArrayList<>();
        itemsArraylist.add(new Item("3:30","4:40","Gate 3","Nasr city"));
        itemsArraylist.add(new Item("3:00","4:00","Gate 4","Masr gdeda"));
        itemsArraylist.add(new Item("2:30","4:50","Gate 4","New cairo"));
        itemsArraylist.add(new Item("1:30","2:40","Gate 3","zayed"));
        itemsArraylist.add(new Item("8:30","3:40","any","where"));
        itemsArraylist.add(new Item("9:30","4:40","zayed","Holly"));
        itemsArraylist.add(new Item("10:30","5:40","University","Nasr city"));
        itemsArraylist.add(new Item("11:30","6:40","University","Nasr city"));
    }

    @Override
    public void onItemClick(Item item) {
        Fragment costFragment = confirmationPayment.newInstance(item.pickupT,item.dropoffT,item.pickupP,item.dropoffP);
        FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView01,new confirmationPayment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}