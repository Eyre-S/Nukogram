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

public class BuildVars {

    public static boolean DEBUG_VERSION = BuildConfig.DEBUG;
    public static boolean LOGS_ENABLED = BuildConfig.DEBUG;
    public static boolean DEBUG_PRIVATE_VERSION = BuildConfig.DEBUG;
    public static boolean USE_CLOUD_STRINGS = false;
    public static boolean CHECK_UPDATES = false;
    public static boolean NO_SCOPED_STORAGE = Build.VERSION.SDK_INT <= 29;
    public static int BUILD_VERSION = BuildConfig.VERSION_CODE;
    public static String BUILD_VERSION_STRING = BuildConfig.VERSION_NAME;
    public static int APP_ID = BuildConfig.TELEGRAM_APP_ID;
    public static String APP_HASH = BuildConfig.TELEGRAM_APP_HASH;
    //todo what is this?
    public static String SMS_HASH = isStandaloneApp() ? "w0lkcmTZkKh" : (DEBUG_VERSION ? "O2P2z+/jBpJ" : "oLeq9AcOZkT");
    // todo nukox playstore app
    public static String PLAYSTORE_APP_URL = "";

    // You can use this flag to disable Google Play Billing (If you're making fork and want it to be in Google Play)
    public static boolean IS_BILLING_UNAVAILABLE = false;

    static {
        if (ApplicationLoader.applicationContext != null) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", Context.MODE_PRIVATE);
            LOGS_ENABLED = DEBUG_VERSION || sharedPreferences.getBoolean("logsEnabled", DEBUG_VERSION);
        }
    }

    public static boolean useInvoiceBilling() {
        return DEBUG_VERSION || isStandaloneApp() || isBetaApp();
    }

    private static Boolean standaloneApp;
    public static boolean isStandaloneApp() {
        if (standaloneApp == null) {
            standaloneApp = ApplicationLoader.applicationContext != null && (BuildConfig.PACKAGE_ID+".web").equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return standaloneApp;
    }

    private static Boolean betaApp;
    public static boolean isBetaApp() {
        if (betaApp == null) {
            betaApp = ApplicationLoader.applicationContext != null && (BuildConfig.PACKAGE_ID+".beta").equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return betaApp;
    }
}
