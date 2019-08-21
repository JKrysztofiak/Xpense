package com.kxkriszna.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mItemNameInput;
    private EditText mItemPriceInput;
    private Button mAddButton;
    private Button mRemoveButton;
    private ListView mListView;
    private TextView mAmountSpent;
    private ItemListAdapter mAdapter;

    private ArrayList<SingleItem> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemNameInput = (EditText) findViewById(R.id.itemNameInput);
        mItemPriceInput = (EditText) findViewById(R.id.itemPriceInput);
        mAddButton = (Button) findViewById(R.id.addItemButton);
        mRemoveButton = (Button) findViewById(R.id.removeItemButton);
        mAmountSpent = (TextView) findViewById(R.id.spentAmountLabel);
        mListView = (ListView) findViewById(R.id.itemListView);

        //TODO Wczytywanie wartosci z pliku
        itemList = new ArrayList<>();

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem();
            }
        });

    }

    private void addItem(){
        Log.d("Xpense","I SENT SOMETHING");

        String nameInput = mItemNameInput.getText().toString();
        double priceInput = Double.valueOf(mItemPriceInput.getText().toString());

        if(!nameInput.equals("") && priceInput!=0 ){
            SingleItem object = new SingleItem(nameInput,priceInput);

            //itemList.add(object);

            mAdapter.addItemToAdapter(object);

            mItemPriceInput.setText("price");
            mItemNameInput.setText("name");

            mAmountSpent.setText(mAdapter.getTotalAmount());

        }
    }

    private void removeItem(){
        Log.d("Xpense", "ITEM REMOVING");
        mAdapter.removeItemFromAdapter();
        mAmountSpent.setText(mAdapter.getTotalAmount());
    }

    @Override
    public void onStart(){
        super.onStart();
        mAdapter = new ItemListAdapter(this,itemList);
        mListView.setAdapter(mAdapter);
    }
}
