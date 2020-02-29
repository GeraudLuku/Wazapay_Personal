package com.example.personaldashboard_module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.personaldashboard_module.R;

public class PersonalWalletSlideAdapter extends PagerAdapter {

    private Context context;

    public PersonalWalletSlideAdapter(Context context) {
        this.context = context;
    }

    public String[] wallet_currency_array_one = {
            "CFA",
            "USD"
    };
    public String[] wallet_amount_array_one = {
            "45,000",
            "2,500"
    };
    public String[] wallet_country_array_one = {
            "CM",
            "US"
    };

    public String[] wallet_currency_array_two = {
            "CFA",
            "USD"
    };
    public String[] wallet_amount_array_two = {
            "45,000",
            "2,500"
    };
    public String[] wallet_country_array_two = {
            "CM",
            "US"
    };

    public String[] wallet_currency_array_three = {
            "CFA",
            "USD"
    };
    public String[] wallet_amount_array_three = {
            "45,000",
            "2,500"
    };
    public String[] wallet_country_array_three = {
            "CM",
            "US"
    };

    @Override
    public int getCount() {
        return wallet_amount_array_one.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.wallet_item_personal, container, false);

        TextView walletCurrencyOne = view.findViewById(R.id.wallet_currency_one);
        TextView walletCurrencyTwo = view.findViewById(R.id.wallet_currency_two);
        TextView walletCurrencyThree = view.findViewById(R.id.wallet_currency_three);

        TextView walletAmountOne = view.findViewById(R.id.wallet_amount_one);
        TextView walletAmountTwo = view.findViewById(R.id.wallet_amount_two);
        TextView walletAmountThree = view.findViewById(R.id.wallet_amount_three);

        TextView walletCountryOne = view.findViewById(R.id.wallet_country_one);
        TextView walletCountryTwo = view.findViewById(R.id.wallet_country_two);
        TextView walletCountryThree = view.findViewById(R.id.wallet_country_three);

        ImageView getStarted = view.findViewById(R.id.wallet_add_iv);

        LinearLayout defaultWallet = view.findViewById(R.id.default_wallet);


        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "New Wallet Created", Toast.LENGTH_SHORT).show();
//                    Intent i=new
//                            Intent(context,LandingHome.class);
//                    context.startActivity(i);
            }
        });


        walletCurrencyOne.setText(wallet_currency_array_one[position]);
        walletCurrencyTwo.setText(wallet_currency_array_two[position]);
        walletCurrencyThree.setText(wallet_currency_array_three[position]);

        walletAmountOne.setText(wallet_amount_array_one[position]);
        walletAmountTwo.setText(wallet_amount_array_two[position]);
        walletAmountThree.setText(wallet_amount_array_three[position]);

        walletCountryOne.setText(wallet_country_array_one[position]);
        walletCountryTwo.setText(wallet_country_array_two[position]);
        walletCountryThree.setText(wallet_country_array_three[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
