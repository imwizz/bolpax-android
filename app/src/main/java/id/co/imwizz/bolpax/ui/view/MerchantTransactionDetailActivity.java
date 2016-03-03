package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryTrxRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.IdRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionDetailRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/22/16.
 */
public class MerchantTransactionDetailActivity extends AppCompatActivity {

    private static final String TAG = MerchantTransactionDetailActivity.class.getSimpleName();

    List<TransactionHistoryRsp> trxHistory;
    Long  userid,merchantid,bolpax;
    String token,trxId;
    long trxid;

    @Bind(R.id.text_merchant) TextView textMerchant;
    @Bind(R.id.text_amount) TextView textMmount;
    @Bind(R.id.text_last_status) TextView textLaststatus;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.list_history) ListView listHistory;
    @Bind(R.id.button_reply) Button buttonReply;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.progress_bar) ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        Intent i = getIntent();
        trxid = i.getLongExtra("trxid",0);
        trxId = String.valueOf(trxid);
        ButterKnife.bind(this);
        setToolbar();
        refreshHistory();
    }

    private void refreshHistory(){
        RestClient.getBolpax().getTransactionDetail(trxId, "merchant",new Callback<TransactionDetailRsp>() {
            @Override
            public void success(TransactionDetailRsp transactionDetailRsp, Response response) {
                String merchant = transactionDetailRsp.getMerchant();
                String amount = transactionDetailRsp.getAmount();
                String product = transactionDetailRsp.getProduct();
                String laststatus = transactionDetailRsp.getTrxLastStatus();

                trxHistory = transactionDetailRsp.getTrxHistory();
                listHistory.setSelector(new ColorDrawable(0));
                String[] trx = new String[trxHistory.size() + 1];
                for (int i = 0; i < trxHistory.size(); i++) {
                    trx[i] = trxHistory.get(i).getTime();
                    trx[i] = trxHistory.get(i).getStatus();
                }
                ListAdapter listAdapter = new TransactionHistoryAdapter(MerchantTransactionDetailActivity.this, trxHistory);
                listHistory.setAdapter(listAdapter);
                textMerchant.setText(merchant);
                textMmount.setText("Rp " + amount + " for " + product);
                if (laststatus.contains("Transaction complete")) {
                    textLaststatus.setText(laststatus);
                    textLaststatus.setTextColor(Color.parseColor("#49E845"));

                }else {
                    textLaststatus.setText(laststatus);
                    textLaststatus.setTextColor(Color.parseColor("#d36a04"));
                }

                if (trxHistory.size() == 1) {
                    buttonReply.setVisibility(View.VISIBLE);
                    buttonReply.setText("Item Shipment");
                    buttonReply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            buttonReply.setVisibility(View.GONE);
                            AddHistoryTrxRqs addHistoryTrxRqs = new AddHistoryTrxRqs();
                            addHistoryTrxRqs.setTrxId(trxId);
                            IdRqs id = new IdRqs();
                            id.setId(Long.valueOf(3));
                            List<IdRqs> ids = new ArrayList<>();
                            ids.add(id);
                            addHistoryTrxRqs.setTrxStatusMapping(ids);
                            RestClient.getBolpax().postAddHistoryTransaction(addHistoryTrxRqs, new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    progressBar.setVisibility(View.GONE);
                                    refreshHistory();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.e(TAG, error.getMessage());
                                }
                            });
                        }
                    });
                } else {
                    buttonReply.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantTransactionDetailActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_report) {
            Intent i = new Intent(MerchantTransactionDetailActivity.this, MerchantReportIssueActivity.class);
            i.putExtra("trxid",trxId);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}