package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionListAdapter;
import id.co.imwizz.bolpax.data.entity.TransactionLIst;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerTransactionList extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.listviewTransaction) ListView transaction;
    TransactionLIst transactionlist;


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
                Intent i = new Intent(BuyerTransactionList.this,BuyerHomeActivity.class);
                startActivity(i);
            }
        });


        String json = DummyAPI.getJson(BuyerTransactionList.this, R.raw.trx_list);
        Gson gson = new Gson();
        TransactionLIst[] transactionList = gson.fromJson(json, TransactionLIst[].class);

        ListAdapter transactionListAdapter = new TransactionListAdapter(BuyerTransactionList.this, transactionList);
        transaction.setAdapter(transactionListAdapter);

    }
    @OnItemClick(R.id.listviewTransaction)
    void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
        transactionlist = (TransactionLIst) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(BuyerTransactionList.this, BuyerTransactionDetailActivity.class);
        myIntent.putExtra("amount", (transactionlist.getAmount()));
        myIntent.putExtra("merchant", (transactionlist.getMerchant()));
        myIntent.putExtra("date", (transactionlist.getTrxDate()));
        myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
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
                Intent i = new Intent(BuyerTransactionList.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerTransactionList.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;

            case R.id.quit:
                finish();

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
