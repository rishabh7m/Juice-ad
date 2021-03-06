package com.twinetree.juice;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyApplication extends Application {

    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        queue = Volley.newRequestQueue(this);
        printHashKey();
    }

    public void printHashKey () {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.twinetree",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("RISHABH", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("Error", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.i("Error", e.getMessage());
        }
    }
}
