package com.example.safeshopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.smarteist.autoimageslider.SliderLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    private ListView itemListView, counterListView, amountListView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<String> counterList = new ArrayList<>();
    ArrayList<String> amountList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cart_layout, container, false);

        itemListView = (ListView)view.findViewById(R.id.cartItemList);
        counterListView = (ListView)view.findViewById(R.id.cartCounterList);
        amountListView = (ListView)view.findViewById(R.id.cartAmounList);

        itemList.add("Veg Burger");
        itemList.add("Mexican Burger");

        counterList.add("2");
        counterList.add("1");

        amountList.add("300");
        amountList.add("200");

        ImageButton proceedToPay = (ImageButton)view.findViewById(R.id.payButton);

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentSuccessful paymentSuccessful = new PaymentSuccessful();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, paymentSuccessful);
                fragmentTransaction.commit();
            }
        });

        /*
        Bundle itemBundle = this.getArguments();
        itemList = itemBundle.getStringArrayList("key");

        Bundle counterBundle = this.getArguments();
        counterList = counterBundle.getStringArrayList("key");

        Bundle amountBundle = this.getArguments();
        amountList = amountBundle.getStringArrayList("key");
        */
        ArrayAdapter itemArrayAdapter = new ArrayAdapter(getContext(), R.layout.list_color, itemList);
        ArrayAdapter counterArrayAdapter = new ArrayAdapter(getContext(), R.layout.list_color, counterList);
        ArrayAdapter amountArrayAdapter = new ArrayAdapter(getContext(), R.layout.list_color, amountList);

        itemListView.setAdapter(itemArrayAdapter);
        counterListView.setAdapter(counterArrayAdapter);
        amountListView.setAdapter(amountArrayAdapter);

        return view;
    }

}
