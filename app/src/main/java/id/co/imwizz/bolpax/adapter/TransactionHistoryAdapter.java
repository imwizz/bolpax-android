package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryBolpax;

/**
 * Created by bimosektiw on 1/12/16.
 */
public class TransactionHistoryAdapter extends ArrayAdapter<TransactionHistoryBolpax>{

    private List<TransactionHistoryBolpax> trxHistories;

    public TransactionHistoryAdapter (Context context, List<TransactionHistoryBolpax> trxHistories){
        super(context, R.layout.transaction_detail_list, trxHistories);
        this.trxHistories = trxHistories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater trxHistoryInflater = LayoutInflater.from(getContext());
        View customView = trxHistoryInflater.inflate(R.layout.transaction_detail_list, parent, false);

        TransactionHistoryBolpax trxHistory = getItem(position);

        TextView trxhistoryDate = (TextView) customView.findViewById(R.id.text_date);
        TextView trxhistoryStatus = (TextView) customView.findViewById(R.id.text_status);

        trxhistoryDate.setText(trxHistory.getTime());
        trxhistoryStatus.setText(trxHistory.getStatus());

        return customView;

    }
}
