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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mItemNameInput;
    private EditText mItemPriceInput;
    private Button mAddButton;
    private Button mRemoveButton;
    private ListView mListView;
    private TextView mAmountSpent;
    private ItemListAdapter mAdapter;
    private final String FILE_NAME = "itemsDB";


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

    private void saveToFile(){
        try {
            mAdapter.writeListToAFile(this, FILE_NAME);
            Toast.makeText(this, "List SAVED!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error: List NOT SAVED!!!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<SingleItem> readFromFile() throws IOException, ClassNotFoundException {

        FileInputStream fis = this.openFileInput(FILE_NAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<SingleItem> tmp = (ArrayList<SingleItem>) is.readObject();
        is.close();
        fis.close();
        return tmp;
    }

    private void addItem(){
        Log.d("Xpense","I SENT SOMETHING");

        String nameInput = mItemNameInput.getText().toString();
        double priceInput = Double.valueOf(mItemPriceInput.getText().toString());

        if(!nameInput.equals("") && priceInput!=0 ){
            SingleItem object = new SingleItem(nameInput,priceInput);


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

        //TODO CLEANUP!

        try {
            mAdapter = new ItemListAdapter(this,readFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(mAdapter);

        mAmountSpent.setText(mAdapter.getTotalAmount());
    }

    @Override
    public void onStop(){
        super.onStop();

        saveToFile();


    }

}
