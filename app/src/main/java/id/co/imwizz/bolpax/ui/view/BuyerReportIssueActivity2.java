package id.co.imwizz.bolpax.ui.view;

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
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryIssueBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Report;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/18/16.
 */
public class BuyerReportIssueActivity2 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.subject_report) TextView subjectReport;
    @Bind(R.id.desc_report) EditText descReport;
    @Bind(R.id.subbut)Button subBut;
    String createSubjectReport,createDescReport,token,subject,phone;
    private static final String TAG = BuyerReportIssueActivity2.class.getSimpleName();
    Long  userid,issueId,bolpax;
    @Bind(R.id.progressBar)ProgressBar progressBar;
    @Bind(R.id.notif) TextView notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue2);
        ButterKnife.bind(this);
        subBut.setOnClickListener(this);
        setToolbar();
        Intent i = getIntent();
        subject = i.getStringExtra("Subject");
        issueId = i.getLongExtra("issueid",0);
        subjectReport.setText(subject);
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerReportIssueActivity2.this, BuyerHomeActivity.class);
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
                Intent i = new Intent(BuyerReportIssueActivity2.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerReportIssueActivity2.this, CreateStoreActivity.class);
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
                            Toast.makeText(BuyerReportIssueActivity2.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
            case R.id.subbut :
                progressBar.setVisibility(View.VISIBLE);
                notif.setVisibility(View.VISIBLE);
                bolpax = BolpaxStatic.getUserid();
                userid = BolpaxStatic.getUserid();
                token = BolpaxStatic.getToken();
                createSubjectReport = subjectReport.getText().toString();
                createDescReport = descReport.getText().toString();
                Report report = new Report();
                report.setSubject(createDescReport);
                report.setDesc(createDescReport);
                report.setRole("buyer");
                report.setTrxId(1);

                AddHistoryIssueBolpax addHistoryIssueBolpax = new AddHistoryIssueBolpax();
                addHistoryIssueBolpax.setFromAdmin("N");
                addHistoryIssueBolpax.setMessage(createDescReport);
                addHistoryIssueBolpax.setIssueId(issueId);
                addHistoryIssueBolpax.setIssueStatusId(Long.valueOf(3));
                RestClient.getBolpax().postAddHistoryIssue(addHistoryIssueBolpax, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        progressBar.setVisibility(View.GONE);
                        notif.setVisibility(View.GONE);

                        Intent i = new Intent(BuyerReportIssueActivity2.this, BuyerIssueDetailActivity.class);
                        i.putExtra("issueId", issueId);
                        startActivity(i);
                        Toast.makeText(BuyerReportIssueActivity2.this, "Report Submitted", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });

//                Toast.makeText(BuyerReportIssueActivity2.this, "Report Submitted", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(BuyerReportIssueActivity2.this,BuyerIssueDetailActivity.class);
//                startActivity(i);
                break;

        }

    }
}
