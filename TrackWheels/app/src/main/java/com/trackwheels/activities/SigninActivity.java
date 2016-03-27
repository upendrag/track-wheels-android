package com.trackwheels.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;
import com.trackwheels.R;
import com.trackwheels.kinvey.AuthorizedActivity;
import com.trackwheels.kinvey.KinveyClient;
import com.trackwheels.kinvey.Response;
import com.trackwheels.kinvey.ResponseHandler;
import com.trackwheels.utilities.Constants;
import com.trackwheels.utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AuthorizedActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 200;
    private static final int GPLAY_REQUEST_CODE = 201;
    private GoogleApiClient mGoogleApiClient;
    private Client kinveyClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        kinveyClient = ((KinveyClient) getApplication()).getKinveyClient();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:

                submitGoogle(view);
                break;
        }
    }

    public void submitGoogle(View view) {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        startActivityForResult(intent, GPLAY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPLAY_REQUEST_CODE && resultCode == RESULT_OK) {
            String account = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            get(Constants.Kinvey.PLUS_PEOPLE_ME, account, Constants.Kinvey.SCOPE_STRING, null, new ResponseHandler() {
                @Override
                public void handle(Response response) {
                    if (response.status != 200) {
                        error(response);
                        return;
                    }
                    try {
                        JSONObject json = new JSONObject(new String(response.body));
                        String accountId = json.optString("id");
                        loginGoogleKinveyUser();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void error(Response response) {
        // TextView tv = (TextView) findViewById(R.id.output);
        String b = new String(response.body);
        Log.d(AuthorizedActivity.TAG, "Error " + response.status + " body: "
                + b);
        // tv.setText("OUCH!  The Internet never works!\n" + b);
    }

    private void loginGoogleKinveyUser() {

        kinveyClient.user().loginGoogle(getAuthToken(),
                new KinveyUserCallback() {

                    public void onFailure(Throwable e) {
//                        Log.e(TAG, "Failed Kinvey login", e);
                        // TextView tv = (TextView) findViewById(R.id.output);
                        String b = new String(e.getMessage());
//                        Log.e(AuthorizedActivity.TAG, "Error: " + b);
                        // tv.setText("DOH!  Great Scott!\nKinvey: " + b);
                    }

                    ;

                    @Override
                    public void onSuccess(User r) {
                        CharSequence text = "Logged in.";
                        Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_LONG).show();
                        Utility.putToSharedPreference(getApplicationContext(), Constants.SharedPref.KEY_IS_SIGN_IN, "yes");
                        SigninActivity.this.startActivity(new Intent(
                                SigninActivity.this, MainActivity.class));
                        SigninActivity.this.finish();

                    }
                });
    }

}
