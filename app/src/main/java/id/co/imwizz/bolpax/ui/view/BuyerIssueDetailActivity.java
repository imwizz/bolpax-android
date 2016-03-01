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
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueHistoryAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueDetailBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueHistoryBolpax;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RefoundResponse;
import id.co.imwizz.bolpax.rest.Refund;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class BuyerIssueDetailActivity extends AppCompatActivity{

    List<IssueHistoryBolpax> issueHistory;
    @Bind(R.id.toolbar) Toolbar toolbar;
    private static final String TAG = BuyerIssueDetailActivity.class.getSimpleName();
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.suspect) TextView suspectText;
    @Bind(R.id.subject) TextView subjectText;
    @Bind(R.id.amount) TextView amountText;
    @Bind(R.id.laststatus) TextView issueLastStatusText;
    @Bind(R.id.list_detail) ListView issueDetailText;
    @Bind(R.id.replyissue) Button replayIssue;
    @Bind(R.id.refundissue) Button refundIssue;
    Long bolpax,issueid;
    String userid,token,issueId,subject,phone,suspect,amount,product,laststatus;
    BuyerIssueListPojo issuelist;
    @Bind(R.id.progressBar)ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        ButterKnife.bind(this);
        setToolbar();
        Intent i = getIntent();
        issueid = i.getLongExtra("issueId", 0);
        issueId = String.valueOf(issueid);
        refreshHistory();

        bolpax = BolpaxStatic.getUserid();
//        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();



    }

    private void refreshHistory() {
        RestClient.getBolpax().getIssueDetail(issueId, new Callback<IssueDetailBolpax>() {
            @Override
            public void success(IssueDetailBolpax issueDetailBolpax, Response response) {
                suspect = issueDetailBolpax.getSuspect();
                amount = issueDetailBolpax.getAmount();
                product = issueDetailBolpax.getProduct();
                laststatus = issueDetailBolpax.getIssueLastStatus();
                subject = issueDetailBolpax.getSubject();


                issueHistory = issueDetailBolpax.getIssueHistory();
                issueDetailText.setSelector(new ColorDrawable(0));
                String[] issue = new String[issueHistory.size() + 1];
                for (int i = 0; i < issueHistory.size(); i++) {
                    issue[i] = issueHistory.get(i).getTime();
                    issue[i] = issueHistory.get(i).getMessage();


                }
                ListAdapter listAdapter = new IssueHistoryAdapter(BuyerIssueDetailActivity.this, issueHistory);
                issueDetailText.setAdapter(listAdapter);
                suspectText.setText(suspect);
                subjectText.setText(subject);
                amountText.setText("Rp "+amount +" for "+ product);

                if(laststatus.contains("Refund")){
                    refundIssue.setVisibility(View.VISIBLE);
                    issueLastStatusText.setText(laststatus);
//                    issueLastStatusText.setTextColor(Color.YELLOW);
                    issueLastStatusText.setTextColor(Color.parseColor("#d36a04"));
                }else if (laststatus.contains("Closed")){
                    replayIssue.setVisibility(View.GONE);
                    refundIssue.setVisibility(View.GONE);
                    issueLastStatusText.setText(laststatus);
//                    issueLastStatusText.setTextColor(Color.RED);
                    issueLastStatusText.setTextColor(Color.parseColor("#FF4351"));
                }else{
                    replayIssue.setVisibility(View.VISIBLE);
                    issueLastStatusText.setText(laststatus);
//                    issueLastStatusText.setTextColor(Color.YELLOW);
                    issueLastStatusText.setTextColor(Color.parseColor("#d36a04"));
                }
                refundIssue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        Refund refund = new Refund();
                        refund.setIssueId(issueid);
                        RestClient.getBolpax().postRefund(refund, new Callback<RefoundResponse>() {
                            @Override
                            public void success(RefoundResponse s, Response response) {
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
                replayIssue.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        //issuelist = (BuyerIssueListPojo) parent.getItemAtPosition(position);
                        Intent i = new Intent(BuyerIssueDetailActivity.this, BuyerReportIssueActivity2.class);
                        i.putExtra("Subject", subject);
//                        i.putExtra("Subject", (issueHistory.get(i).getIssueStatus()));
                        i.putExtra("issueid", issueid);
                        startActivity(i);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });

    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerIssueDetailActivity.this, BuyerHomeActivity.class);
                startActivity(i);
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
                Intent i = new Intent(BuyerIssueDetailActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerIssueDetailActivity.this, CreateStoreActivity.class);
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
                            Toast.makeText(BuyerIssueDetailActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
}
