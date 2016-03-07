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
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerTransactionRqs;

/**
 * This adapter is used to display transaction list
 *
 * @author bimosektiw
 */

public class TransactionAdapter extends ArrayAdapter<BuyerTransactionRqs> {


    private List<BuyerTransactionRqs> transactionList;
    Context mContext;

    public TransactionAdapter(Context context, List<BuyerTransactionRqs> transactionList) {
        super(context, R.layout.item_transaction, transactionList);
        this.transactionList = transactionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.item_transaction, parent, false);

        BuyerTransactionRqs transaction = getItem(position);

        TextView textDate = (TextView) customView.findViewById(R.id.text_date);
        TextView textStatus = (TextView) customView.findViewById(R.id.text_satus);
        TextView textNominal = (TextView) customView.findViewById(R.id.text_nominal);

        String status = transaction.getTrxLastStatus();

        textDate.setText(transaction.getTrxDate());
        if (status != null) {
            if (status.contains("Transaction complete")) {
                textStatus.setText(transaction.getTrxLastStatus());
                textStatus.setTextColor(Color.parseColor("#49E845"));
            }else {
                textStatus.setText(transaction.getTrxLastStatus());
                textStatus.setTextColor(Color.parseColor("#d36a04"));
            }
        }

        textNominal.setText("Rp." + transaction.getAmount() + " to " + transaction.getMerchant() + "");
        return customView;
    }
}
