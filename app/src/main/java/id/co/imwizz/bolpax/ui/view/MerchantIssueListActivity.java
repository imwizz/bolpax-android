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
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantIssueAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantIssueListActivity extends AppCompatActivity {

    private static final String TAG = MerchantIssueListActivity.class.getSimpleName();
    final Context context = this;

    protected Context mContext;
    private String phone,userid,token,merchantid,merchantName;
    private MenuItem createstore,switchtomerchant,buyername;
    private List<MerchantIssueRqs> merchantIssueRqses;
    private Long bolpax;
    private MerchantIssueRqs issuelist;

    @Bind(R.id.list_issue) ListView listIssue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
        ButterKnife.bind(this);
        setToolbar();

        bolpax = BolpaxStatic.getMerchantid();
        merchantid = bolpax.toString();
        userid = BolpaxStatic.getUserid().toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        merchantName = BolpaxStatic.getMerchantname();

        RestClient.getBolpax().getMerchantIssuelist(merchantid, new Callback<List<MerchantIssueRqs>>() {
            @Override
            public void success(List<MerchantIssueRqs> result, Response response) {
                if(result==null){
                    Toast.makeText(MerchantIssueListActivity.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                }else{
                merchantIssueRqses = new ArrayList<MerchantIssueRqs>(result);
                for (int i = 0; i < merchantIssueRqses.size(); i++) {
                    long id = merchantIssueRqses.get(i).getIssueId();
                    String date = merchantIssueRqses.get(i).getIssueDate();
                    String status = merchantIssueRqses.get(i).getIssueLastStatus();
                    Double amount = merchantIssueRqses.get(i).getAmount();
                    String suspect = merchantIssueRqses.get(i).getSuspect();

                    ListAdapter issueListAdapter = new MerchantIssueAdapter(MerchantIssueListActivity.this, merchantIssueRqses);
                    listIssue.setAdapter(issueListAdapter);
                }


                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });
        listIssue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                issuelist = (MerchantIssueRqs) parent.getItemAtPosition(position);
                Intent myIntent = new Intent(MerchantIssueListActivity.this, MerchantIssueDetailActivity.class);
                myIntent.putExtra("issueId", (issuelist.getIssueId()));
                startActivity(myIntent);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        buyername = menu.findItem(R.id.profile);
        buyername.setTitle(merchantName.toString());
        createstore.setVisible(false);
        switchtomerchant.setTitle("Switch To Buyer");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantIssueListActivity.this, MerchantProfileActivity.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantIssueListActivity.this, BuyerHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<LogoutRsp>() {
                    @Override
                    public void success(LogoutRsp s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantIssueListActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantIssueListActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
    }

}
