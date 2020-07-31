package com.ibm.cloud.appid.android.sample.appid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AppIDAuthorizationManager;
import com.ibm.cloud.appid.android.api.AuthorizationException;

public class StartupActivity extends AppCompatActivity {

    private final static String region = AppID.REGION_UK;
    private AppID appId;
    private AppIDAuthorizationManager appIDAuthorizationManager;
    private TokensPersistenceManager tokensPersistenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        appId = AppID.getInstance();

        appId.initialize(this, getResources().getString(R.string.authTenantId), region);
        appIDAuthorizationManager = new AppIDAuthorizationManager(this.appId);
        tokensPersistenceManager = new TokensPersistenceManager(this, appIDAuthorizationManager);

        String storedRefreshToken = tokensPersistenceManager.getStoredRefreshToken();
        if (storedRefreshToken != null && !storedRefreshToken.isEmpty()) {
            refreshTokens(storedRefreshToken);
        } else {
            openMainActivity();
        }
    }

    private void refreshTokens(String refreshToken) {
        Log.d(logTag("refreshTokens"), "Trying to refresh tokens using a refresh token");
        boolean storedTokenAnonymous = tokensPersistenceManager.isStoredTokenAnonymous();
        AppIdSampleAuthorizationListener appIdSampleAuthorizationListener =
                new AppIdSampleAuthorizationListener(this, appIDAuthorizationManager, storedTokenAnonymous) {
                    @Override
                    public void onAuthorizationFailure(AuthorizationException exception) {
                        Log.d(StartupActivity.this.logTag("refreshTokens"), "Can not refresh tokens", exception);
                        openMainActivity();
                    }
                };
        appId.signinWithRefreshToken(this, refreshToken, appIdSampleAuthorizationListener);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String logTag(String methodName){
        return getClass().getCanonicalName() + "." + methodName;
    }
}