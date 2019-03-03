package com.example.giftishare.view.buycoupon;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.giftishare.Event;
import com.example.giftishare.R;
import com.example.giftishare.ViewModelFactory;
import com.example.giftishare.data.model.Coupon;
import com.example.giftishare.databinding.ActivityBuyCouponBinding;
import com.example.giftishare.helper.CategoryNameMapper;

import static com.example.giftishare.view.onsalecoupons.OnSaleCouponsAdapter.INTENT_BUY_COUPON;

public class BuyCouponActivity extends AppCompatActivity {

    private ActivityBuyCouponBinding mBinding;

    private BuyCouponViewModel mBuyCouponViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBuyCouponViewModel = obtainViewModel(this);

        mBinding = DataBindingUtil.setContentView(BuyCouponActivity.this, R.layout.activity_buy_coupon);
        mBinding.setLifecycleOwner(this);
        mBinding.setBuyCouponViewModel(mBuyCouponViewModel);

        setupToolBar();

        Intent intent = getIntent();
        Coupon coupon = (Coupon) intent.getSerializableExtra(INTENT_BUY_COUPON);
        mBinding.cardInfo.ivCouponImage.setImageResource(CategoryNameMapper.toImageDrawable(coupon.getCategory()));
        mBuyCouponViewModel.start(coupon);

        mBinding.btnDecline.setOnClickListener((View view) -> onBackPressed());

        mBuyCouponViewModel.getBuyCouponEvent().observe(this,
                (Event<String> event) -> {
                    Toast.makeText(getApplicationContext(), event.getContentIfNotHandled(), Toast.LENGTH_LONG).show();
                    finish();
                });
    }

    private BuyCouponViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        BuyCouponViewModel buyCouponViewModel = ViewModelProviders.of(this, factory).get(BuyCouponViewModel.class);
        return buyCouponViewModel;
    }

    private void setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBinding.btnBack.setOnClickListener((View v) -> onBackPressed());
    }
}
