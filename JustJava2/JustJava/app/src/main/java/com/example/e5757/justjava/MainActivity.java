package com.example.e5757.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public int numberOfCoffees = 0;
    public int priceCoffee = 2;
    private TextView quantityTextView;
    private EditText nomeEdit;
    public String nome;
    private CheckBox whipedCream_checkbox;
    public boolean hasWhipedCream;
    private CheckBox chocolate_checkbox;
    public boolean haschocolate;


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
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int quantity) {

        //Add 1 if the user wants whiped Cream
        if(hasWhipedCream){
            priceCoffee = priceCoffee + 1;
        }

        //Add 2 if the user wants chocolate
        if(haschocolate){
            priceCoffee = priceCoffee + 2;
        }

        //Calculate the final value
        return quantity * priceCoffee;
    }

    /**
     * this method increment the number of coffees
     */
    public void increment() {
        if(numberOfCoffees < 100) {
            numberOfCoffees = numberOfCoffees = numberOfCoffees + 1;
            display(numberOfCoffees);
        }

    }

    /**
     * this method decrement the number of coffees
     */
    public void decrement() {
        if(numberOfCoffees > 1 ) {
            numberOfCoffees = numberOfCoffees = numberOfCoffees - 1;
            display(numberOfCoffees);
        }

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        // Figure out the name of the person who orders
        nomeEdit = (EditText) findViewById(R.id.nome_editext);
        nome = nomeEdit.getText().toString();

        // Figure out if the user wants whipped cream topping
        whipedCream_checkbox = (CheckBox) findViewById(R.id.whiped_cream_checkbox);
        hasWhipedCream = whipedCream_checkbox.isChecked();

        // Figure out if the user wants chocolate topping
        chocolate_checkbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        haschocolate = chocolate_checkbox.isChecked();


        //Calcuate the price
        int total = calculatePrice(numberOfCoffees);

        //Diplay the order summary on the screen
        String priceMessage = createOrderSumary(total,numberOfCoffees, nome, hasWhipedCream, haschocolate );
        composEmail(priceMessage);

    }

    /**
     * This method is called in the submitOrder method so can create the order text before display.
     * @param priceOrder the total price of the order
     * @param numeroCoffee number of the coffes order
     * @return the complete order
     */
    public String createOrderSumary(int priceOrder, int numeroCoffee, String nome, boolean addWhipedCream, boolean addChooolate){


        String priceMessage = "Nome: " + nome;
        priceMessage = priceMessage + "\nAdd Whiped Cream? " + addWhipedCream;
        priceMessage = priceMessage + "\nAdd Chocolate? " + addChooolate;
        priceMessage = priceMessage + "\nQuantidade de Cafes: " + numeroCoffee;
        priceMessage = priceMessage + "\nTotal a pagar: €" + priceOrder;
        priceMessage = priceMessage + "\nThank you!";

        return priceMessage;
    }


    public void composEmail(String body){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "order");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        quantityTextView.setText(String.valueOf(number));
    }
}