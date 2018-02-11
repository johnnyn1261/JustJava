/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
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
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
        name = "";
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();
        //displayMessage(createOrderSummary(hasWhippedCream, hasChocolate));

        EditText customerName = findViewById(R.id.customer_name);
        name = customerName.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_for, name));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(hasWhippedCream, hasChocolate));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
        String summary = getString(R.string.name, name) +
                "\n" + getString(R.string.add_w,hasWhippedCream) + "" +
                "\n" + getString(R.string.add_c, hasChocolate) + "" +
                "\n" + getString(R.string.quantity_message, quantity) + "" +
                "\n" + getString(R.string.total, calculatePrice(hasWhippedCream, hasChocolate)) + "" +
                "\n" + getString(R.string.thank_you);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /*
    public void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    */

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < 99) {
            quantity++;
            display(quantity);
        } else if (quantity == 99) {
            Toast.makeText(this, "Dude, I think you have enough...", Toast.LENGTH_SHORT).show();
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