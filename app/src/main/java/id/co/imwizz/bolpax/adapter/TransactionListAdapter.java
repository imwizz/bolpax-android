package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerTransactionListPojo;

//import com.squareup.picasso.Picasso;
//import id.co.imwizz.gokeel.R;
//import id.co.imwizz.gokeel.domain.service.Category;

public class TransactionListAdapter extends ArrayAdapter<BuyerTransactionListPojo> {


    private List<BuyerTransactionListPojo> transactionList;
//    TransactionLIst[] transactionList2;
    Context mContext;

    public TransactionListAdapter(Context context, List<BuyerTransactionListPojo> transactionList) {
        super(context, R.layout.transaction_list, transactionList);
        this.transactionList = transactionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.transaction_list, parent, false);

        BuyerTransactionListPojo transaction = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.tvdate);
        TextView statusText = (TextView) customView.findViewById(R.id.tvsatus);
        TextView nominalText = (TextView) customView.findViewById(R.id.tvnominal);
//        ImageView categoryImage = (ImageView) customView.findViewById(R.id.categoryImage);

        String status = transaction.getTrxLastStatus();

        dateText.setText(transaction.getTrxDate());
        if (status != null) {
            if (status.contains("Transaction complete")) {
                statusText.setText(transaction.getTrxLastStatus());
//                statusText.setTextColor(Color.GREEN);
                statusText.setTextColor(Color.parseColor("#49E845"));
            }else {
                statusText.setText(transaction.getTrxLastStatus());
//                statusText.setTextColor(Color.YELLOW);
                statusText.setTextColor(Color.parseColor("#d36a04"));
            }
        }

        nominalText.setText("Rp."+transaction.getAmount()+" to "+transaction.getMerchant()+"");
        return customView;
    }
}
