package com.daizzyinfo.food18.utils;

import androidx.multidex.MultiDexApplication;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        try {
            //   Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            SSLEngine sslEngine = sslContext.createSSLEngine();
            sslEngine.setEnabledProtocols(new String[]{"TLSv1"});
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                 | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public static MyApplication getContext() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
