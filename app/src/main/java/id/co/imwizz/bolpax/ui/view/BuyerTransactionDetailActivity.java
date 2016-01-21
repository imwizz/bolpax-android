package id.co.imwizz.bolpax.ui.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.TransactionDetail;
import id.co.imwizz.bolpax.data.entity.TrxHistory;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by bimosektiw on 11/01/2016.
 */
public class BuyerTransactionDetailActivity extends AppCompatActivity {

    private TextView merchantText, amountText, laststatusText;
    List<TrxHistory> trxHistory;
    private ListView trxDetailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        initWidget();
        setToolbar();

        String json = DummyAPI.getJson(BuyerTransactionDetailActivity.this ,R.raw.trx_detail);
        Gson gson = new Gson();
        TransactionDetail transactionDetail = gson.fromJson(json, TransactionDetail.class);

        String merchant = transactionDetail.getMerchant();
        String amount = transactionDetail.getAmount();
        String product = transactionDetail.getProduct();
        String laststatus = transactionDetail.getTrxLastStatus();

        trxHistory = transactionDetail.getTrxHistory();
        trxDetailText.setSelector(new ColorDrawable(0));
        String[] trx = new String[trxHistory.size() + 1];
        for (int i = 0; i < trxHistory.size(); i++) {
            trx[i] = trxHistory.get(i).getTime();
            trx[i] = trxHistory.get(i).getStatus();
        }
        ListAdapter listAdapter = new TransactionHistoryAdapter(BuyerTransactionDetailActivity.this, trxHistory);
        trxDetailText.setAdapter(listAdapter);
        merchantText.setText(merchant);
        amountText.setText("Rp "+amount +" for "+ product);
        laststatusText.setText(laststatus);
    }

    private void initWidget(){
        merchantText = (TextView) findViewById(R.id.merchant);
        amountText = (TextView) findViewById(R.id.amount);
        laststatusText = (TextView) findViewById(R.id.laststatus);
        trxDetailText = (ListView) findViewById(R.id.list_detail);
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
