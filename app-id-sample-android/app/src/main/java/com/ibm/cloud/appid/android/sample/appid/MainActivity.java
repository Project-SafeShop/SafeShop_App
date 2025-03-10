/*
 * Copyright 2016, 2017 IBM Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.cloud.appid.android.sample.appid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AppIDAuthorizationManager;
import com.ibm.cloud.appid.android.api.LoginWidget;

/**
 * This is the App front page activity.
 * It demonstrates the use of {@link AppID} for two forms of authorization:
 * 1. loginAnonymously for creating a guest user profile.
 * 2. Using the loginWidget to log in through identity providers authentication. This could create a new user profile or
 * provide access to an existing one.
 * In both cases App Id generates and returns Access and Identity tokens. The Identity token provides information
 * about the user which could come from the Identity Provider (e.g. facebook, google...) and the access token can be used
 * to access the profile attributes.
 *
 * This sample also demonstrates how a token can be stored on the device and reused when coming back to the app.
 */
public class MainActivity extends AppCompatActivity {

    private final static String region = AppID.REGION_UK;
    private final static String authTenantId = "60d75fe6-17f6-4b4f-ad2c-ab8ff17ac14b";

    private AppID appId;
    private AppIDAuthorizationManager appIDAuthorizationManager;
    private TokensPersistenceManager tokensPersistenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appId = AppID.getInstance();

        appId.initialize(this, authTenantId, region);

        appIDAuthorizationManager = new AppIDAuthorizationManager(this.appId);
        tokensPersistenceManager = new TokensPersistenceManager(this, appIDAuthorizationManager);

        String storedRefreshToken = tokensPersistenceManager.getStoredRefreshToken();
        if (storedRefreshToken != null && !storedRefreshToken.isEmpty()) {
            refreshTokens(storedRefreshToken);
        }
    }

    private void refreshTokens(String refreshToken) {
        Log.d(logTag("refreshTokens"), "Trying to refresh tokens using a refresh token");
        boolean storedTokenAnonymous = tokensPersistenceManager.isStoredTokenAnonymous();
        AppIdSampleAuthorizationListener appIdSampleAuthorizationListener =
                new AppIdSampleAuthorizationListener(this, appIDAuthorizationManager, storedTokenAnonymous);
        appId.signinWithRefreshToken(this, refreshToken, appIdSampleAuthorizationListener);
    }

    /**
     * Continue as guest action
     * @param v
     */
    public void onAnonymousClick (View v) {
        Log.d(logTag("onAnonymousClick"),"Attempting anonymous authorization");

        final String storedAccessToken = tokensPersistenceManager.getStoredAnonymousAccessToken();
        AppIdSampleAuthorizationListener appIdSampleAuthorizationListener =
                new AppIdSampleAuthorizationListener(this, appIDAuthorizationManager, true);

        appId.signinAnonymously(getApplicationContext(), storedAccessToken, appIdSampleAuthorizationListener);
    }

    /**
     * Log in with identity provider authentication action
     * @param v
     */
    public void onLoginClick(View v) {
        Log.d(logTag("onLoginClick"),"Attempting identified authorization");
        LoginWidget loginWidget = appId.getLoginWidget();
        final String storedAccessToken;
        storedAccessToken = tokensPersistenceManager.getStoredAccessToken();

        AppIdSampleAuthorizationListener appIdSampleAuthorizationListener =
                new AppIdSampleAuthorizationListener(this, appIDAuthorizationManager, false);

        loginWidget.launch(this, appIdSampleAuthorizationListener, storedAccessToken);
    }

    private String logTag(String methodName){
        return getClass().getCanonicalName() + "." + methodName;
    }
}