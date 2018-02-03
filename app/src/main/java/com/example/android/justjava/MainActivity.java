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
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

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
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Calculates the price of the order
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * Create summary of order
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price) {
        String summary = "Name: Winkie Long Nguyen" +
                "\nQuantity: " + quantity + "" +
                "\nTotal: $" + price + "" +
                "\nThank you!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the give text on the screen
     */
    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
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