/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int quantity = 2;
        //display(quantity);
        //displayPrice(quantity * 5);
        //Toast.makeText(this, "Order Completed", Toast.LENGTH_SHORT).show();

        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();

        //int price = calculatePrice();
        displayMessage(createOrderSummary(hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;
        price = hasWhippedCream ? price + 1 : price;
        price = hasChocolate ? price + 2 : price;
        return price * quantity;
    }

    /**
     * Create summary of order
     * @param hasWhippedCream
     * @param hasChocolate
     * @return text summary
     */
    private String createOrderSummary(boolean hasWhippedCream, boolean hasChocolate) {
        EditText customerName = findViewById(R.id.customer_name);
        String summary = "Name: " + customerName.getText().toString() +
                "\nAdd Whipped Cream? " + hasWhippedCream + "" +
                "\nAdd Chocolate? " + hasChocolate + "" +
                "\nQuantity: " + quantity + "" +
                "\nTotal: $" + calculatePrice(hasWhippedCream, hasChocolate) + "" +
                "\nThank you!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the give text on the screen
     */
    public void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < 99) {
            quantity++;
            display(quantity);
        } else if (quantity == 99) {
            Toast.makeText(this, "Dude, I think you've had enough...", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            display(quantity);
        } else {
            quantity = 0;
            display(quantity);
        }
    }
}