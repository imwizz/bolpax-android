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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionListAdapter;
import id.co.imwizz.bolpax.data.entity.TransactionLIst;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantTransactionList extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone,merchants;
    Integer balance;
    LinearLayout merchant;
    TextView merchantName;
    ListView transaction;
    TransactionLIst transactionlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        merchantName=(TextView)findViewById(R.id.textView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MerchantTransactionList.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("BOLPAX");

        transaction=(ListView)findViewById(R.id.listviewTransaction);
        String json = DummyAPI.getJson(MerchantTransactionList.this, R.raw.trx_list);
        Gson gson = new Gson();
//        JSONArray jsonArr = new JSONArray();

        TransactionLIst[] transactionList = gson.fromJson(json, TransactionLIst[].class);

//        String Test = merchant.getMerchant();
//        String[] catValues5 = new String[merchant.size() + 1];
//        for (int i = 0; i < merchant.size(); i++) {
//
//        }
        ListAdapter transactionListAdapter = new TransactionListAdapter(MerchantTransactionList.this, transactionList);
        transaction.setAdapter(transactionListAdapter);
//        MerchantListAdapter merchantlistAdapter = new MerchantListAdapter(MerchantList_Activity.this, merchant);
//        listView.setAdapter(merchantlistAdapter);

        transaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                transactionlist = (TransactionLIst) parent.getItemAtPosition(position);
                //catVal = Category.getCategoryName();
                //catVal = (Category)getIntent().getSerializableExtra("category");
                Intent myIntent = new Intent(MerchantTransactionList.this, BuyerTransactionDetailActivity.class);
                myIntent.putExtra("amount", (transactionlist.getAmount()));
                myIntent.putExtra("merchant", (transactionlist.getMerchant()));
                myIntent.putExtra("date", (transactionlist.getTrxDate()));
                myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
                startActivity(myIntent);

            }
        });


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
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("phone", phone);
                i.putExtra("balance", balance);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(MerchantTransactionList.this, MerchantHomeActivity.class);
                i2.putExtra("email", email);
                i2.putExtra("name", name);
                i2.putExtra("phone", phone);
                i2.putExtra("balance", balance);
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
}
