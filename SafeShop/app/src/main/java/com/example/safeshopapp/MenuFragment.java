package com.example.safeshopapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private TextView item1Text, item2Text, item3Text, item4Text;
    private int counter1, counter2, counter3, counter4;
    private int amount1 = 100;
    private int amount2 = 150;
    private int amount3 = 150;
    private int amount4 = 200;
    Button minusButton1, minusButton2, minusButton3, minusButton4, plusButton1, plusButton2, plusButton3, plusButton4;
    /*Bundle itemBundle = new Bundle();
    Bundle counterBundle = new Bundle();
    Bundle amountBundle = new Bundle();
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<String> counterList = new ArrayList<>();
    ArrayList<String> amountList = new ArrayList<>();*/

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.minus1:
                    minusCount1();
                    setAmount1();
                    /*itemList.remove("Cheese Burger");
                    counterList.remove(counter1+"");
                    amountList.remove(amount1+"");
                    sendToBundle1();*/
                    break;
                case R.id.minus2:
                    minusCount2();
                    setAmount2();
                    /*itemList.remove("Veg Burger");
                    counterList.remove(counter2+"");
                    amountList.remove(amount2+"");
                    sendToBundle2();*/
                    break;
                case R.id.minus3:
                    minusCount3();
                    setAmount3();
                    /*itemList.remove("Paneer Burger");
                    counterList.remove(counter3+"");
                    amountList.remove(amount3+"");
                    sendToBundle3();*/
                    break;
                case R.id.minus4:
                    minusCount4();
                    setAmount4();
                    /*itemList.remove("Mexican Burger");
                    counterList.remove(counter4+"");
                    amountList.remove(amount4+"");
                    sendToBundle4();*/
                    break;
                case R.id.plus1:
                    plusCount1();
                    setAmount1();
                    /*itemList.add("Cheese Burger");
                    counterList.add(counter1+"");
                    amountList.add(amount1+"");
                    sendToBundle1();*/
                    break;
                case R.id.plus2:
                    plusCount2();
                    setAmount2();
                    /*itemList.add("Veg Burger");
                    counterList.add(counter2+"");
                    amountList.add(amount2+"");
                    sendToBundle2();*/
                    break;
                case R.id.plus3:
                    plusCount3();
                    setAmount3();
                    /*itemList.add("Paneer Burger");
                    counterList.add(counter3+"");
                    amountList.add(amount3+"");
                    sendToBundle3();*/
                    break;
                case R.id.plus4:
                    plusCount4();
                    setAmount4();
                    /*itemList.add("Mexican Burger");
                    counterList.add(counter4+"");
                    amountList.add(amount4+"");
                    sendToBundle4();*/
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_layout, container, false);

        item1Text = (TextView)view.findViewById(R.id.item1Text);
        item2Text = (TextView)view.findViewById(R.id.item2Text);
        item3Text = (TextView)view.findViewById(R.id.item3Text);
        item4Text = (TextView)view.findViewById(R.id.item4Text);

        minusButton1 = (Button) view.findViewById(R.id.minus1);
        minusButton2 = (Button) view.findViewById(R.id.minus2);
        minusButton3 = (Button) view.findViewById(R.id.minus3);
        minusButton4 = (Button) view.findViewById(R.id.minus4);

        plusButton1 = (Button) view.findViewById(R.id.plus1);
        plusButton2 = (Button) view.findViewById(R.id.plus2);
        plusButton3 = (Button) view.findViewById(R.id.plus3);
        plusButton4 = (Button) view.findViewById(R.id.plus4);

        minusButton1.setOnClickListener(onClickListener);
        minusButton2.setOnClickListener(onClickListener);
        minusButton3.setOnClickListener(onClickListener);
        minusButton4.setOnClickListener(onClickListener);

        plusButton1.setOnClickListener(onClickListener);
        plusButton2.setOnClickListener(onClickListener);
        plusButton3.setOnClickListener(onClickListener);
        plusButton4.setOnClickListener(onClickListener);

        setCounter();
        /*
        CartFragment updateCartItem = new CartFragment();
        updateCartItem.setArguments(itemBundle);
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new CartFragment()).commit();

        CartFragment updateCartCounter = new CartFragment();
        updateCartCounter.setArguments(counterBundle);
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new CartFragment()).commit();

        CartFragment updateCartAmount = new CartFragment();
        updateCartAmount.setArguments(amountBundle);
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new CartFragment()).commit();
        */

        ImageButton proceedToPay = (ImageButton)view.findViewById(R.id.addToCart);

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment addToCart = new CartFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, addToCart);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void setCounter() {

        counter1 = 0;
        item1Text.setText(counter1 + "");

        counter2 = 0;
        item2Text.setText(counter2 + "");

        counter3 = 0;
        item3Text.setText(counter3 + "");

        counter4 = 0;
        item4Text.setText(counter4 + "");

    }

    private void minusCount1() {
        counter1--;
        item1Text.setText(counter1 + "");
    }

    private void minusCount2() {
        counter2--;
        item2Text.setText(counter2 + "");
    }

    private void minusCount3() {
        counter3--;
        item3Text.setText(counter3 + "");
    }

    private void minusCount4() {
        counter4--;
        item4Text.setText(counter4 + "");
    }

    private void plusCount1() {
        counter1++;
        item1Text.setText(counter1 + "");
    }

    private void plusCount2() {
        counter2++;
        item2Text.setText(counter2 + "");
    }

    private void plusCount3() {
        counter3++;
        item3Text.setText(counter3 + "");
    }

    private void plusCount4() {
        counter4++;
        item4Text.setText(counter4 + "");
    }

    private void setAmount1() {
        amount1 = amount1 * counter1;
    }

    private void setAmount2() {
        amount2 = amount2 * counter2;
    }

    private void setAmount3() {
        amount3 = amount3 * counter3;
    }

    private void setAmount4() {
        amount4 = amount4 * counter4;
    }
    /*
    public void sendToBundle1() {
        itemBundle.putStringArrayList("key", itemList);
        counterBundle.putStringArrayList("key", counterList);
        amountBundle.putStringArrayList("key", amountList);
    }

    public void sendToBundle2() {
        itemBundle.putStringArrayList("key", itemList);
        counterBundle.putStringArrayList("key", counterList);
        amountBundle.putStringArrayList("key", amountList);
    }

    public void sendToBundle3() {
        itemBundle.putStringArrayList("key", itemList);
        counterBundle.putStringArrayList("key", counterList);
        amountBundle.putStringArrayList("key", amountList);
    }

    public void sendToBundle4() {
        itemBundle.putStringArrayList("key", itemList);
        counterBundle.putStringArrayList("key", counterList);
        amountBundle.putStringArrayList("key", amountList);
    }
    */
}
