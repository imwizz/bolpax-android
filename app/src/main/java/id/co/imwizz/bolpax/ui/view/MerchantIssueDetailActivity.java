package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueDetailBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueHistoryBolpax;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/26/16.
 */
public class MerchantIssueDetailActivity extends AppCompatActivity {

    private static final String TAG = MerchantIssueDetailActivity.class.getSimpleName();
    private Long issueid;
    private String issueId,subject;
    private List<IssueHistoryBolpax> issueHistory;

    @Bind(R.id.text_suspect) TextView textSuspect;
    @Bind(R.id.text_amount) TextView textAmount;
    @Bind(R.id.text_last_status) TextView textLastStatus;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.list_history) ListView listHistory;
    @Bind(R.id.button_reply_issue) Button buttonReplyIssue;
    @Bind(R.id.text_header) TextView textHeader;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        ButterKnife.bind(this);
        setToolbar();
        Intent i = getIntent();
        issueid = i.getLongExtra("issueId", 0);
        issueId = String.valueOf(issueid);



        RestClient.getBolpax().getIssueDetail(issueId, new Callback<IssueDetailBolpax>() {
            @Override
            public void success(IssueDetailBolpax issueDetailBolpax, Response response) {

                buttonReplyIssue.setVisibility(View.VISIBLE);
                String suspect = issueDetailBolpax.getSuspect();
                String amount = issueDetailBolpax.getAmount();
                String product = issueDetailBolpax.getProduct();
                String laststatus = issueDetailBolpax.getIssueLastStatus();
                subject = issueDetailBolpax.getSubject();

                issueHistory = issueDetailBolpax.getIssueHistory();
                listHistory.setSelector(new ColorDrawable(0));
                String[] issue = new String[issueHistory.size() + 1];
                for (int i = 0; i < issueHistory.size(); i++) {
                    issue[i] = issueHistory.get(i).getTime();
                    issue[i] = issueHistory.get(i).getMessage();
                }
                ListAdapter listAdapter = new IssueHistoryAdapter(MerchantIssueDetailActivity.this, issueHistory);
                listHistory.setAdapter(listAdapter);
                textSuspect.setText(suspect);
                textHeader.setText(subject);
                textAmount.setText("Rp " + amount + " for " + product);
                textLastStatus.setText(laststatus);
                textLastStatus.setTextColor(Color.parseColor("#d36a04"));
                if (issueHistory.size() > 1){
                    buttonReplyIssue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MerchantIssueDetailActivity.this, MerchantReportIssue2Activity.class);
                            i.putExtra("Subject", subject);
                            i.putExtra("issueid", issueid);
                            startActivity(i);
                        }
                    });
                } else if (laststatus.contains("Closed")){
                    buttonReplyIssue.setVisibility(View.GONE);
                }


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_switch) {
            return true;
        }
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantIssueDetailActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");

    }
}

