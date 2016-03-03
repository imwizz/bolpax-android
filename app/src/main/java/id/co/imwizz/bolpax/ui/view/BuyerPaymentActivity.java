package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.PaymentRqs;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.PaymentResponse;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerPaymentActivity.class.getSimpleName();
    
    protected Context mContext;
    private MenuItem createstore,switchtomerchant,buyername;
    private String phone,merchantname,token,productName,amountString,nama;
    private Long userid,merchantid,trxid,merchantId;
    private double amount;
    
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.text_merchant_name) TextView textMerchantName;
    @Bind(R.id.text_notification) TextView textNotification;
    @Bind(R.id.edit_amount) EditText editAmount;
    @Bind(R.id.edit_product_name) EditText editProductName;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.button_pay) Button buttonPay;
    @Bind(R.id.progress_bar) ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_payment);
        ButterKnife.bind(this);
        toolbar.setOnClickListener(this);
        buttonPay.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setTitle("");

        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerPaymentActivity.this,BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        merchantid = i.getLongExtra("merchantId", 1L);
        merchantname = i.getStringExtra("merchantName").toString();

        textMerchantName.setText(merchantname);
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        nama = BolpaxStatic.getFullname();
        merchantId = BolpaxStatic.getMerchantid();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        buyername.setTitle(nama.toString());
        if ( merchantId !=null){
            createstore.setVisible(false);
        } else {
            switchtomerchant.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(BuyerPaymentActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerPaymentActivity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(BuyerPaymentActivity.this, MerchantHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token,phone,new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if(success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BuyerPaymentActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });



                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.button_pay:
                progressBar.setVisibility(View.VISIBLE);
                textNotification.setVisibility(View.VISIBLE);
                amountString = editAmount.getText().toString();
                productName= editProductName.getText().toString();
                amount = Double.parseDouble(amountString);
                PaymentRqs payment = new PaymentRqs();
                payment.setUserId(userid);
                payment.setMerchantId(merchantid);
                payment.setAmount(amount);
                payment.setProduct(productName);
                payment.setToken(token);

                RestClient.getBolpax().getBuyerPayment(payment, new Callback<PaymentResponse>() {
                    @Override
                    public void success(PaymentResponse s, Response response) {
                        trxid = s.getTrxId();
                        String test = s.getAmount();
                        String test2=s.getFromAccount();
                        Toast.makeText(getBaseContext(), "Payment Successful", Toast.LENGTH_LONG).show();
                        Intent i2 = new Intent(BuyerPaymentActivity.this,BuyerTransactionDetailActivity.class);
                        i2.putExtra("trxid", trxid);
                        startActivity(i2);
                        progressBar.setVisibility(View.GONE);
                        textNotification.setVisibility(View.GONE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());
                    }
                });
                break;


        }
    }
}
