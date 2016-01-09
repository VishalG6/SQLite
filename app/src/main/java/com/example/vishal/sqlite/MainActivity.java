package com.example.vishal.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText vishInput;
    TextView vishText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vishInput = (EditText) findViewById(R.id.vishInput);
        vishText = (TextView) findViewById(R.id.vishText);
        dbHandler = new MyDBHandler(this, null, null, 1);

        printDatabase();

     }

    public void addButtonClicked (View view ){
        Products products = new Products(vishInput.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();

    }

    //Delete Item
    public void deleteButtonClicked(View view){
        String inputText = vishInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void printDatabase(){
        String dbString = dbHandler.databasetoString();
        vishText.setText(dbString);
        vishInput.setText("");

    }
}
