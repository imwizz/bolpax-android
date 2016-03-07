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
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.ReportRqs;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author bimosektiw
 */
public class BuyerReportIssue2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerReportIssue2Activity.class.getSimpleName();

    private String createSubjectReport,createDescReport,token,subject,phone;
    private Long  userid,issueId,bolpax;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.text_subject) TextView textSubject;
    @Bind(R.id.edit_description) EditText editDescription;
    @Bind(R.id.button_submit) Button buttonSubmit;
    @Bind(R.id.progress_bar) ProgressBar progressBar;
    @Bind(R.id.text_notification) TextView textNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue2);
        ButterKnife.bind(this);
        buttonSubmit.setOnClickListener(this);
        setToolbar();
        Intent i = getIntent();
        subject = i.getStringExtra("Subject");
        issueId = i.getLongExtra("issueid",0);
        textSubject.setText(subject);
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(BuyerReportIssue2Activity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerReportIssue2Activity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(BuyerReportIssue2Activity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button_submit:
                progressBar.setVisibility(View.VISIBLE);
                textNotification.setVisibility(View.VISIBLE);
                bolpax = BolpaxStatic.getUserid();
                userid = BolpaxStatic.getUserid();
                token = BolpaxStatic.getToken();
                createSubjectReport = textSubject.getText().toString();
                createDescReport = editDescription.getText().toString();
                ReportRqs report = new ReportRqs();
                report.setSubject(createDescReport);
                report.setDesc(createDescReport);
                report.setRole("buyer");
                report.setTrxId(1);

                AddHistoryIssueRqs addHistoryIssueRqs = new AddHistoryIssueRqs();
                addHistoryIssueRqs.setFromAdmin("N");
                addHistoryIssueRqs.setMessage(createDescReport);
                addHistoryIssueRqs.setIssueId(issueId);
                addHistoryIssueRqs.setIssueStatusId(Long.valueOf(3));
                RestClient.getBolpax().postAddHistoryIssue(addHistoryIssueRqs, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        progressBar.setVisibility(View.GONE);
                        textNotification.setVisibility(View.GONE);

                        Intent i = new Intent(BuyerReportIssue2Activity.this, BuyerIssueDetailActivity.class);
                        i.putExtra("issueId", issueId);
                        startActivity(i);
                        Toast.makeText(BuyerReportIssue2Activity.this, "Report Submitted", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });

                break;

        }

    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerReportIssue2Activity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });

    }
}
