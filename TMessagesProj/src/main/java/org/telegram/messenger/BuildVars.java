/*
 * This is the source code of Telegram for Android v. 7.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2020.
 */

package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.android.billingclient.api.ProductDetails;

import java.util.Objects;

public class BuildVars {
    
    public static boolean DEBUG_VERSION = BuildConfig.DEBUG_VERSION;
    public static boolean LOGS_ENABLED = BuildConfig.DEBUG_VERSION;
    public static boolean DEBUG_PRIVATE_VERSION = BuildConfig.DEBUG_PRIVATE_VERSION;
    public static boolean USE_CLOUD_STRINGS = false; // nuko: use BuildConfig
    public static boolean CHECK_UPDATES = false; // nuko: use BuildConfig
    public static boolean NO_SCOPED_STORAGE = Build.VERSION.SDK_INT <= 29;
    public static int BUILD_VERSION = BuildConfig.VERSION_CODE; // nuko: use BuildConfig
    public static String BUILD_VERSION_STRING = BuildConfig.VERSION_NAME; // nuko: use BuildConfig
    public static int APP_ID = BuildConfig.TELEGRAM_APP_ID; // nuko: use BuildConfig
    public static String APP_HASH = BuildConfig.TELEGRAM_APP_HASH; // nuko: use BuildConfig
    
    // SafetyNet key for Google Identity SDK, set it to empty to disable
    // nuko: SafetyNet disabled
    public static String SAFETYNET_KEY = "";
    // nuko: todo what is this?
    public static String SMS_HASH = isStandaloneApp() ? "w0lkcmTZkKh" : (DEBUG_VERSION ? "O2P2z+/jBpJ" : "oLeq9AcOZkT");
    // nuko: todo playstore app
    public static String PLAYSTORE_APP_URL = "";
    public static String GOOGLE_AUTH_CLIENT_ID = "";

    // nuko: todo Huawei App Register
    public static String HUAWEI_APP_ID = "";

    // You can use this flag to disable Google Play Billing (If you're making fork and want it to be in Google Play)
    public static boolean IS_BILLING_UNAVAILABLE = false;

    static {
        if (ApplicationLoader.applicationContext != null) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", Context.MODE_PRIVATE);
            LOGS_ENABLED = DEBUG_VERSION || sharedPreferences.getBoolean("logsEnabled", DEBUG_VERSION);
        }
    }

    public static boolean useInvoiceBilling() {
        return DEBUG_VERSION || isStandaloneApp() || isBetaApp() || isHuaweiStoreApp() || hasDirectCurrency();
    }

    private static boolean hasDirectCurrency() {
        if (!BillingController.getInstance().isReady() || BillingController.PREMIUM_PRODUCT_DETAILS == null) {
            return false;
        }
        for (ProductDetails.SubscriptionOfferDetails offerDetails : BillingController.PREMIUM_PRODUCT_DETAILS.getSubscriptionOfferDetails()) {
            for (ProductDetails.PricingPhase phase : offerDetails.getPricingPhases().getPricingPhaseList()) {
                for (String cur : MessagesController.getInstance(UserConfig.selectedAccount).directPaymentsCurrency) {
                    if (Objects.equals(phase.getPriceCurrencyCode(), cur)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Boolean standaloneApp;
    public static boolean isStandaloneApp() {
        if (standaloneApp == null) {
            // nuko: use BuildConfig package id
            standaloneApp = ApplicationLoader.applicationContext != null && (BuildConfig.PACKAGE_ID+".web").equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return standaloneApp;
    }

    private static Boolean betaApp;
    public static boolean isBetaApp() {
        if (betaApp == null) {
            // nuko: use BuildConfig package id
            betaApp = ApplicationLoader.applicationContext != null && (BuildConfig.PACKAGE_ID+".beta").equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return betaApp;
    }


    public static boolean isHuaweiStoreApp() {
        return ApplicationLoader.isHuaweiStoreBuild();
    }
}
