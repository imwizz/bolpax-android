package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.MerchantList;

//import com.squareup.picasso.Picasso;
//import id.co.imwizz.gokeel.R;
//import id.co.imwizz.gokeel.domain.service.Category;

public class MerchantListAdapter extends ArrayAdapter<MerchantList> {


    private List<MerchantList> merchant;
    MerchantList[] merchant2;
    Context mContext;

    public MerchantListAdapter(Context context, MerchantList[] merchant2) {
        super(context, R.layout.merchant_list, merchant2);
        this.merchant2 = merchant2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SEGOEUI.TTF");
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.merchant_list, parent, false);

        MerchantList category = getItem(position);

        TextView categoryText = (TextView) customView.findViewById(R.id.businessOpen);
//        ImageView categoryImage = (ImageView) customView.findViewById(R.id.categoryImage);

        categoryText.setText(category.getMerchant());
//        categoryText.setTypeface(tf);
//        if(category.getIcon() != null) {
//            Uri uri = Uri.parse(category.getIcon());
//            Context context = categoryImage.getContext();
//            Picasso.with(context).load(uri).into(categoryImage);
//        } else {
//            categoryImage.setImageResource(R.drawable.ic_launcher);
//        }
//        customView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(mContext, BuyerPaymentActivity.class);
////                // Pass all data rank
//                intent.putExtra("dbdest",(merchant2.get));
//                mContext.startActivity(intent);
//
//            }
//        });

        return customView;
    }
}
