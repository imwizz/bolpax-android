package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import id.co.imwizz.bolpax.data.entity.bolpax.request.Store;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class CreateStoreActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.submit_store) Button submit;
    @Bind(R.id.store_name) EditText storeName;
    String createStore;
    //LinearLayout merchant,transaction,issue;
    //Toolbar toolbar;
//    TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_store);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateStoreActivity.this, "This Home", Toast.LENGTH_SHORT).show();
            }
        });


        submit.setOnClickListener(this);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CreateStoreActivity.this, MerchantHomeActivity.class);
//                startActivity(i);
//            }
//        });




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
                Intent i = new Intent(CreateStoreActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(CreateStoreActivity.this, CreateStoreActivity.class);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.submit_store:
                createStore();
                break;

        }

    }

    private void createStore() {
        createStore = storeName.getText().toString();
        Store store = new Store();
        store.setName(createStore);
        store.setUserId(2);
        RestClient.getBolpax().postStore(store, new Callback<String>() {

            @Override
            public void success(String string, Response response) {
                Toast.makeText(getBaseContext(), "Create Store Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(CreateStoreActivity.this,MerchantList_Activity.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
