package com.pratik;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CouponManagementSystem {

    static class Coupon {

        private String code;
        private double discountPercentage;
        private LocalDate expiryDate;
        private int usageLimit;
        private int usedCount;

        public Coupon(String code,
                      double discountPercentage,
                      LocalDate expiryDate,
                      int usageLimit) {

            this.code = code;
            this.discountPercentage = discountPercentage;
            this.expiryDate = expiryDate;
            this.usageLimit = usageLimit;
            this.usedCount = 0;
        }

        public boolean isValid() {

            return LocalDate.now().isBefore(expiryDate)
                    && usedCount < usageLimit;
        }

        public double applyDiscount(double amount) {

            usedCount++;

            return amount -
                    (amount * discountPercentage / 100);
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {

            return "Coupon{" +
                    "code='" + code + '\'' +
                    ", discount=" + discountPercentage +
                    "%, expiry=" + expiryDate +
                    ", used=" + usedCount +
                    "/" + usageLimit +
                    '}';
        }
    }

    static class CouponService {

        private final Map<String, Coupon> coupons =
                new HashMap<>();

        public void addCoupon(Coupon coupon) {

            coupons.put(
                    coupon.getCode(),
                    coupon);

            System.out.println(
                    "Coupon Added Successfully");
        }

        public double applyCoupon(
                String couponCode,
                double billAmount) {

            Coupon coupon =
                    coupons.get(couponCode);

            if (coupon == null) {

                System.out.println(
                        "Invalid Coupon");

                return billAmount;
            }

            if (!coupon.isValid()) {

                System.out.println(
                        "Coupon Expired or Limit Reached");

                return billAmount;
            }

            return coupon.applyDiscount(
                    billAmount);
        }

        public void displayCoupons() {

            System.out.println(
                    "\nAvailable Coupons:");

            for (Coupon coupon
                    : coupons.values()) {

                System.out.println(coupon);
            }
        }
    }

    public static void main(String[] args) {

        CouponService service =
                new CouponService();

        service.addCoupon(
                new Coupon(
                        "SAVE20",
                        20,
                        LocalDate.now().plusDays(30),
                        5));

        service.displayCoupons();

        double billAmount = 2500;

        double finalAmount =
                service.applyCoupon(
                        "SAVE20",
                        billAmount);

        System.out.println(
                "\nOriginal Amount: ₹"
                        + billAmount);

        System.out.println(
                "Final Amount: ₹"
                        + finalAmount);
    }
}