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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantTransactionListAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantTransactionListPojo;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantTransactionList extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    private static final String TAG = BuyerTransactionList.class.getSimpleName();
    String email,name,phone,userid,token,merchantId;
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.listviewTransaction) ListView transaction;
    MerchantTransactionListPojo transactionlist;
    List<MerchantTransactionListPojo> merchantTransactionListPojos;
    final Context context = this;
    Long bolpax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");

        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantTransactionList.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });
//        long userId=1;

        bolpax = BolpaxStatic.getMerchantid();
        merchantId = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        RestClient.getBolpax().getMerchantTransactionlist(merchantId.toString(), new Callback<List<MerchantTransactionListPojo>>() {
            @Override
            public void success(List<MerchantTransactionListPojo> result, Response response) {

                if(result==null){
                    Toast.makeText(MerchantTransactionList.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                }else {
                    merchantTransactionListPojos = new ArrayList<MerchantTransactionListPojo>(result);
                    for (int i = 0; i < merchantTransactionListPojos.size(); i++) {
                        long id = merchantTransactionListPojos.get(i).getTrxId();
                        String date = merchantTransactionListPojos.get(i).getTrxDate();
                        String status = merchantTransactionListPojos.get(i).getTrxLastStatus();
                        Double amount = merchantTransactionListPojos.get(i).getAmount();
                        String merchant = merchantTransactionListPojos.get(i).getMerchant();
                        String product = merchantTransactionListPojos.get(i).getProduct();

                    }
                    ListAdapter transactionListAdapter = new MerchantTransactionListAdapter(MerchantTransactionList.this, merchantTransactionListPojos);
                    transaction.setAdapter(transactionListAdapter);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });

//                String json = DummyAPI.getJson(BuyerTransactionList.this, R.raw.trx_list);
//        Gson gson = new Gson();
//        TransactionLIst[] transactionList = gson.fromJson(json, TransactionLIst[].class);
//
//        ListAdapter transactionListAdapter = new TransactionListAdapter(BuyerTransactionList.this, transactionList);
//        transaction.setAdapter(transactionListAdapter);

    }

    @OnItemClick(R.id.listviewTransaction)
    void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
        transactionlist = (MerchantTransactionListPojo) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(MerchantTransactionList.this, MerchantTransactionDetailActivity.class);
        myIntent.putExtra("amount", (transactionlist.getAmount()));
        myIntent.putExtra("trxid", (transactionlist.getTrxId()));
        myIntent.putExtra("merchant", (transactionlist.getMerchant()));
        myIntent.putExtra("date", (transactionlist.getTrxDate()));
        myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
        myIntent.putExtra("product", (transactionlist.getProduct()));
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantTransactionList.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(MerchantTransactionList.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantTransactionList.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        Log.e(TAG, error.getMessage());

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


        }
    }
}
