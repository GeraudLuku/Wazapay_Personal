package com.example.wazapaypersonal.Dashboard.adapter;

import android.view.View;
import android.widget.TextView;


import com.example.wazapaypersonal.Dashboard.Model.WalletItem;
import com.example.wazapaypersonal.R;
import com.github.islamkhsh.CardSliderAdapter;

import java.util.ArrayList;

public class CardSliderViewAdapter extends CardSliderAdapter<WalletItem> {
    public CardSliderViewAdapter(ArrayList<WalletItem> wallets){
        super(wallets);
    }
    @Override
    public void bindView(int position, View itemContentView, WalletItem item) {
        TextView currency = itemContentView.findViewById(R.id.wallet_currency_one);
        currency.setText(item.getCurrency());
        TextView balance = itemContentView.findViewById(R.id.wallet_amount_one);
        balance.setText(item.getBalance().toString());
        TextView country = itemContentView.findViewById(R.id.wallet_country_one);
        country.setText(item.getCountry());
    }
    @Override
    public int getItemContentLayout(int position) {
        return R.layout.wallet_item_personal;
    }
}
