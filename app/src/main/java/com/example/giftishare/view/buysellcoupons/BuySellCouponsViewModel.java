package com.example.giftishare.view.buysellcoupons;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.giftishare.data.DataManager;
import com.example.giftishare.data.model.Coupon;

import java.util.List;

public class BuySellCouponsViewModel extends AndroidViewModel {

    public LiveData<List<Coupon>> mCoupons;

    public MutableLiveData<Boolean> mIsSale = new MutableLiveData<>();

    private final DataManager mDataManager;

    private final Context mContext;

    public LiveData<List<Coupon>> getCoupons() {
        return mCoupons;
    }

    public LiveData<Boolean> getIsSale() {
        return mIsSale;
    }

    public BuySellCouponsViewModel(@NonNull Application context, DataManager dataManager) {
        super(context);
        mContext = context.getApplicationContext();
        mDataManager = dataManager;
    }

    public void start(boolean isSale) {
        mCoupons = mDataManager.getCoupons(isSale);
        mIsSale.setValue(isSale);
    }
}
