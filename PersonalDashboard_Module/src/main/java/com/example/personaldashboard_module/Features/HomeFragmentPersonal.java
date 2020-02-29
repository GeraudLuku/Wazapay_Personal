package com.example.personaldashboard_module.Features;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.personaldashboard_module.R;
import com.example.personaldashboard_module.Model.WalletItem;
import com.example.personaldashboard_module.adapter.CardSliderViewAdapter;
import com.example.personaldashboard_module.adapter.PersonalWalletSlideAdapter;
import com.github.islamkhsh.CardSliderViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeFragmentPersonal extends Fragment implements View.OnClickListener {
    private BottomNavigationView mBottomNavigationView;

    private ViewPager mSlideViewPager;
    private PersonalWalletSlideAdapter mPersonalWalletSlideAdapter;

    //private BubblesManager bubblesManager;

    private int mCurrentPage;

    private LinearLayout mDeposit, mWithdrawals, mDirectTransfer, mMoneyRequest, mEscrowTransfer,
            mBulkPayments, mRecurringPayments, mInvoicing, mCurrencyExchange, mUtilityBills,
            mNjangi, mPiggyWallets, mCashFlowAgents, mDonations, mReferralSystem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //onscroll hide bottom navigation view
        NestedScrollView nestedScrollView = view.findViewById(R.id.nestedScrollView);
        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > oldScrollY) {
                        hideBottomNavigationView(mBottomNavigationView);
                    }
                    if (scrollY < oldScrollY) {
                        showBottomNavigationView(mBottomNavigationView);
                    }

                }
            });
        }

        mBottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
//        mBottomNavigationView.setSelectedItemId(R.id.menu_home);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Toast.makeText(getContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });


        //load dummy items in viewpager
        ArrayList<WalletItem> wallets = new ArrayList<>();
        wallets.add(new WalletItem(1, 21000, "EU", "Fr"));
        wallets.add(new WalletItem(2, 9000, "$", "USA"));
        wallets.add(new WalletItem(3, 24500, "XAF", "CM"));
        wallets.add(new WalletItem(4, 89300, "KL", "GB"));
        CardSliderViewPager cardSliderViewPager = view.findViewById(R.id.viewPager);
        cardSliderViewPager.setAdapter(new CardSliderViewAdapter(wallets));


        mDeposit = view.findViewById(R.id.ic_deposit_personal);
        mDeposit.setOnClickListener(this);
        mWithdrawals = view.findViewById(R.id.ic_withdrawals_personal);
        mWithdrawals.setOnClickListener(this);
        mDirectTransfer = view.findViewById(R.id.ic_direct_transfer_personal);
        mDirectTransfer.setOnClickListener(this);

        mMoneyRequest = view.findViewById(R.id.ic_money_request_personal);
        mMoneyRequest.setOnClickListener(this);
        mEscrowTransfer = view.findViewById(R.id.ic_escrow_personal);
        mEscrowTransfer.setOnClickListener(this);
        mBulkPayments = view.findViewById(R.id.ic_bulk_payments_personal);
        mBulkPayments.setOnClickListener(this);

        mRecurringPayments = view.findViewById(R.id.ic_recurring_payment_personal);
        mRecurringPayments.setOnClickListener(this);
        mInvoicing = view.findViewById(R.id.ic_invoicing_personal);
        mInvoicing.setOnClickListener(this);
        mCurrencyExchange = view.findViewById(R.id.ic_currency_personal);
        mCurrencyExchange.setOnClickListener(this);

        mUtilityBills = view.findViewById(R.id.ic_utility_bills_personal);
        mUtilityBills.setOnClickListener(this);
        mNjangi = view.findViewById(R.id.ic_ngangi_personal);
        mNjangi.setOnClickListener(this);
        mPiggyWallets = view.findViewById(R.id.ic_piggy_wallets_personal);
        mPiggyWallets.setOnClickListener(this);

        mCashFlowAgents = view.findViewById(R.id.ic_cash_flow_personal);
        mCashFlowAgents.setOnClickListener(this);
        mDonations = view.findViewById(R.id.ic_donations_personal);
        mDonations.setOnClickListener(this);
        mReferralSystem = view.findViewById(R.id.ic_referral_system_personal);
        mReferralSystem.setOnClickListener(this);

        mSlideViewPager = view.findViewById(R.id.walletViewPager);
        mPersonalWalletSlideAdapter = new PersonalWalletSlideAdapter(getContext());
        mSlideViewPager.setAdapter(mPersonalWalletSlideAdapter);
        mSlideViewPager.addOnPageChangeListener(mOnPageChangeListener);

        super.onViewCreated(view, savedInstanceState);
    }

    private void hideBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(view.getHeight());
    }

    private void showBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(0);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            mCurrentPage = i;
            if (i == 0) {

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_deposit_personal:
                Toast.makeText(getContext(), "Deposit Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_withdrawals_personal:
                Toast.makeText(getContext(), "Withdrawals Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_direct_transfer_personal:
                Toast.makeText(getContext(), "Direct Deposit Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_money_request_personal:
                Toast.makeText(getContext(), "Money Request Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_escrow_personal:
                Toast.makeText(getContext(), "Escrow Transfer Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_bulk_payments_personal:
                Toast.makeText(getContext(), "Bulk Payment Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_recurring_payment_personal:
                Toast.makeText(getContext(), "Recurring Payment Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_invoicing_personal:
                Toast.makeText(getContext(), "Invoice Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_currency_personal:
                Toast.makeText(getContext(), "Currency Exchange Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_utility_bills_personal:
                Toast.makeText(getContext(), "Utility Bills Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_ngangi_personal:
                Toast.makeText(getContext(), "Njangi Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_piggy_wallets_personal:
                Toast.makeText(getContext(), "Piggy Wallet Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ic_cash_flow_personal:
                Toast.makeText(getContext(), "Cash-Flow Agents Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_donations_personal:
                Toast.makeText(getContext(), "Donations Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_referral_system_personal:
                Toast.makeText(getContext(), "Referral System Clicked", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //This method is executed to add a new bubble.
//    private void addNewBubble() {
//        BubbleLayout bubbleView = (BubbleLayout)LayoutInflater.from(HomeFragmentPersonal.this).inflate(R.layout.bubble_layout, null);
//        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
//            @Override
//            public void onBubbleRemoved(BubbleLayout bubble) { }
//        });

//        //The Onclick Listener for the bubble has been set below.
//        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {
//
//            @Override
//            public void onBubbleClick(BubbleLayout bubble) {
//
//                //Do what you want onClick of bubble.
////                Toast.makeText(getApplicationContext(), "Clicked !",
////                        Toast.LENGTH_SHORT).show();
//            }
//        });
//        bubbleView.setShouldStickToWall(true);
//        bubblesManager.addBubble(bubbleView, 60, 20);
//    }
//
//    private void initializeBubblesManager() {
//        bubblesManager = new BubblesManager.Builder(this)
//                .setTrashLayout(R.layout.bubble_trash_layout)
//                .setInitializationCallback(new OnInitializedCallback() {
//                    @Override
//                    public void onInitialized() {
//                        addNewBubble();
//                    }
//                })
//                .build();
//        bubblesManager.initialize();

}
