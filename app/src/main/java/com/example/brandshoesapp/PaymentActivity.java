package com.example.brandshoesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PaymentActivity extends AppCompatActivity {
    TextView subTotal, discount, shipping, total;
    Button checkOut;

    String clientID= "AVXyltUcpK0QDL4Fh7EIEAXCpttYMnUmrdeRpm8Gt3HVFzpLfaWGPcnUZxnd5OO9mRUS9YhfZwuEROjn";
    int PAYPAL_REQUEST_CODE= 123;
     public static PayPalConfiguration configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView17);
       shipping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);
       checkOut=findViewById(R.id.pay_btn);



        configuration=new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(clientID);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethod();
            }
        });
    }

    private void paymentMethod() {
        String amount ="0.0";
        amount=getIntent().getStringExtra("amount");
        subTotal.setText(amount);


        PayPalPayment payment=new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD","code with amr",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent=new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT,payment);

        startActivityForResult(intent,PAYPAL_REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PAYPAL_REQUEST_CODE){
            PaymentConfirmation confirmation=data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);

            if (confirmation!=null){

                try {

                    String paymentDetails = confirmation.toJSONObject().toString();
                    JSONObject object = new JSONObject(paymentDetails);


                } catch (JSONException e) {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }


            } else if (requestCode== Activity.RESULT_CANCELED) {

                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                
            }
        } else if (requestCode== com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID) {

            Toast.makeText(this, "invalid payment", Toast.LENGTH_SHORT).show();
            
        }
    }
}