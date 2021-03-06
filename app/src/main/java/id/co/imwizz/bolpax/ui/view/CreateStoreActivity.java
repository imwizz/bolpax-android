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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.StoreRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This activity is used to display Create Store Merchant.
 *
 * @author Duway
 */
public class CreateStoreActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerTransactionListActivity.class.getSimpleName();
    private String createStore,token,phone;
    private Long  userid,bolpax;
    protected Context mContext;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.button_submit_store) Button buttonSubmitStore;
    @Bind(R.id.edit_store_name) EditText editStoreName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_store);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateStoreActivity.this, "This Home", Toast.LENGTH_SHORT).show();
            }
        });


        buttonSubmitStore.setOnClickListener(this);
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
                Intent i = new Intent(CreateStoreActivity.this, BuyerProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(CreateStoreActivity.this, CreateStoreActivity.class);
                startActivity(i2);

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
                        Toast.makeText(CreateStoreActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
            case R.id.button_submit_store:
                createStore();
                break;

        }

    }

    /**
     * This method is used to Create Store.
     */
    private void createStore() {
        bolpax = BolpaxStatic.getUserid();
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        createStore = editStoreName.getText().toString();
        StoreRqs store = new StoreRqs();
        store.setName(createStore);
        store.setUserId(userid);
        RestClient.getBolpax().postStore(store, new Callback<String>() {

            @Override
            public void success(String string, Response response) {
                Toast.makeText(getBaseContext(), "Create Store Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(CreateStoreActivity.this,MerchantHomeActivity.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
