package com.example.giftishare.data.remote.ethereum;

import com.example.giftishare.data.model.Coupon;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java8.util.concurrent.CompletableFuture;

/**
 * Created by KS-KIM on 19/02/21.
 */

public interface SmartContractHelper {

    CompletableFuture<TransactionReceipt> buyCoupon(String uuid, String price);

    CompletableFuture<TransactionReceipt> resumeSaleCoupon(String uuid);

    CompletableFuture<TransactionReceipt> useCoupon(String uuid);

    CompletableFuture<TransactionReceipt> addCoupon(Coupon coupon);

    CompletableFuture<TransactionReceipt> stopSaleCoupon(String uuid);
}