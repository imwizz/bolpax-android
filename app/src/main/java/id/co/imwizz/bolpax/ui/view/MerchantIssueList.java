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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantIssueListAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.TransactionLIst;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueListPojo;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantIssueList extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone,merchants,userid,token;
    Integer balance;
    LinearLayout merchant;
    TransactionLIst transactionlist;
    @Bind(R.id.listviewIssue) ListView issue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    List<MerchantIssueListPojo> merchantIssueListPojos;
    Long bolpax;
    private static final String TAG = MerchantIssueList.class.getSimpleName();
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
        ButterKnife.bind(this);
        setToolbar();

        bolpax = BolpaxStatic.getMerchantid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();

        RestClient.getBolpax().getMerchantIssuelist("2", new Callback<List<MerchantIssueListPojo>>() {
            @Override
            public void success(List<MerchantIssueListPojo> result, Response response) {
                if(result==null){
                    Toast.makeText(MerchantIssueList.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                }else{
                merchantIssueListPojos = new ArrayList<MerchantIssueListPojo>(result);
                for (int i = 0; i < merchantIssueListPojos.size(); i++) {
                    long id = merchantIssueListPojos.get(i).getIssueId();
                    String date = merchantIssueListPojos.get(i).getIssueDate();
                    String status = merchantIssueListPojos.get(i).getIssueLastStatus();
                    Double amount = merchantIssueListPojos.get(i).getAmount();
                    String suspect = merchantIssueListPojos.get(i).getSuspect();

                    ListAdapter issueListAdapter = new MerchantIssueListAdapter(MerchantIssueList.this, merchantIssueListPojos);
                    issue.setAdapter(issueListAdapter);
                }


                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });

//        String json = DummyAPI.getJson(MerchantIssueList.this, R.raw.issue_list);
//        Gson gson = new Gson();
////        JSONArray jsonArr = new JSONArray();
//
//        IssueList[] issueList = gson.fromJson(json, IssueList[].class);

//        String Test = merchant.getMerchant();
//        String[] catValues5 = new String[merchant.size() + 1];
//        for (int i = 0; i < merchant.size(); i++) {
//
//        }
//        ListAdapter issueListAdapter = new IssueListAdapter(MerchantIssueList.this, issueList);
//        issue.setAdapter(issueListAdapter);
//        MerchantListAdapter merchantlistAdapter = new MerchantListAdapter(MerchantList_Activity.this, merchant);
//        listView.setAdapter(merchantlistAdapter);

        issue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                issue = (IssueList) parent.getItemAtPosition(position);
                //catVal = Category.getCategoryName();
                //catVal = (Category)getIntent().getSerializableExtra("category");
                Intent myIntent = new Intent(MerchantIssueList.this, MerchantIssueDetailActivity.class);
//                myIntent.putExtra("amount", (transactionlist.getAmount()));
//                myIntent.putExtra("merchant", (transactionlist.getMerchant()));
//                myIntent.putExtra("date", (transactionlist.getTrxDate()));
//                myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
                startActivity(myIntent);

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
                Intent i = new Intent(MerchantIssueList.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantIssueList.this, ProfileActivity.class);
                i.putExtra("email", email);
                i.putExtra("name", name);
                i.putExtra("phone", phone);
                i.putExtra("balance", balance);
                startActivity(i);

                return true;

            case R.id.switchto_buyer:
                Intent i2 = new Intent(MerchantIssueList.this, BuyerHomeActivity.class);
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
