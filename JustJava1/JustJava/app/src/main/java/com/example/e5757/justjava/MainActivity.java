package com.example.e5757.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public int numberOfCoffees = 0;
    public int numberOfCoffeesAux = 0;
    public int priceCoffee = 2;
    private TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfCoffees));

        Button decriment_button = findViewById(R.id.decriment_button);
        decriment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
        Button increment_button = findViewById(R.id.increment_button);
        increment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });

    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int total = numberOfCoffees * priceCoffee;
//        displayPrice(total);

        String priceMessage = "Total: €" + total;
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);

    }

    public void increment() {
        if(numberOfCoffees >= 0) {
            numberOfCoffeesAux = numberOfCoffees = numberOfCoffees + 1;
            display(numberOfCoffeesAux);
        }

    }

    public void decrement() {
        if(numberOfCoffees > 0 ) {
            numberOfCoffeesAux = numberOfCoffees = numberOfCoffees - 1;
            display(numberOfCoffeesAux);
        }

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        quantityTextView.setText(String.valueOf(number));
    }
}