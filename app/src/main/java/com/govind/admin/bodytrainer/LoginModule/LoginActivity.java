package com.govind.admin.bodytrainer.LoginModule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.govind.admin.bodytrainer.BeanModule.User;
import com.govind.admin.bodytrainer.BodyTrainerApp;
import com.govind.admin.bodytrainer.BuildConfig;
import com.govind.admin.bodytrainer.HomePage.HomePage;
import com.govind.admin.bodytrainer.R;
import com.govind.admin.bodytrainer.Utility.ApiHandler;
import com.govind.admin.bodytrainer.Utility.ApiHandlerError;
import com.govind.admin.bodytrainer.Utility.Constants;
import com.govind.admin.bodytrainer.Utility.FirebaseGetDeviceToken;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
@BindView(R.id.btnlogin)Button btnlogin;
@BindView(R.id.signup)Button signup;
@BindView(R.id.edtUsemail)EditText userEmail;
@BindView(R.id.inpUseremail)
TextInputLayout inputEmail;
    private static final int RC_SIGN_IN = 123;
@BindView(R.id.edtPassword)EditText userPassword;
    @BindView(R.id.inppassword)TextInputLayout inputPassword;
    @BindView(R.id.parent)
    RelativeLayout parent;
    String googleType="1";
    String loginType="0";
    //String loginType="2";
    private JSONObject user_obj;
    String email="";
    private String deviceid;
    SharedPreferences pref;
    private int deviceIdChecked = 0;
    LoginButton fbbutton;
    private String emailAddress;
    private String username;
    private final String TAG = LoginActivity.this.getClass().getName();

    private CallbackManager callbackManager;
    private AccessToken accessToken;
    Button cstmfbbuton,googlecstmbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        userEmail.setFocusableInTouchMode(true);
        inputEmail.setFocusableInTouchMode(true);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        getDeviceTocken();

//        fbbutton = (LoginButton) findViewById(R.id.facebokid);
        cstmfbbuton = (Button) findViewById(R.id.cstmfbbuton);
        googlecstmbtn = (Button) findViewById(R.id.google);
        callbackManager = CallbackManager.Factory.create();
                cstmfbbuton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //cstmfbbuton.setEnabled(false);
                        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,Arrays.asList("email", "public_profile"));
                        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Log.e("loginresult", String.valueOf(loginResult));
                                handleFacebookAccessToken(loginResult.getAccessToken());
                            }

                            @Override
                            public void onCancel() {
                                Log.e("cancel","cancel");
                            }

                            @Override
                            public void onError(FacebookException error) {
                                Log.e("facebbookerror","facebookerror");
                          Toast.makeText(LoginActivity.this,"Email doesn't exist!check other way to login ",Toast.LENGTH_LONG).show();
//                                mAuth.signOut();
//                                LoginManager.getInstance().logOut();


                            }
                        });
                    }
                });



    }

    private void handleFacebookAccessToken(final AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.e("credential", String.valueOf(credential));
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            GraphRequest graphRequest = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    try {
                                        //Toast.makeText(getApplicationContext(),"facebook success2",Toast.LENGTH_LONG).show();
                                        email = object.getString("email");
                                        username = object.getString("name");


                                        emailCheck();
                                        // Toast.makeText(getApplicationContext(),"facebook success3",Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {

                                        e.printStackTrace();
                                        Toast.makeText(LoginActivity.this,"Email doesn't exist!check other way to login",Toast.LENGTH_LONG).show();
                                    }
//                                    Log.e("username",username);
//                                    Log.e("emailaddress",email);
                                }
                            });

                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,first_name,last_name,email");
                            graphRequest.setParameters(parameters);
                            graphRequest.executeAsync();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }




    private void getDeviceTocken() {

        pref = BodyTrainerApp.getInstance().getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        deviceid = pref.getString(Constants.DEVICE_TOKEN, "");
        if (deviceid.isEmpty()) {
            FirebaseGetDeviceToken.getDeviceToken();
        }
        if (deviceIdChecked != 0) {
            if (deviceIdChecked < 4) {
                checkDeviceIDValid();
            }
        }
    }

    private void checkDeviceIDValid() {

        if (deviceid == null && deviceid.isEmpty()) {
            if (deviceIdChecked < 3) {
                deviceIdChecked++;
                getDeviceTocken();
            } else {
                showSnackBar("Please wait.....");
                FirebaseGetDeviceToken.getDeviceToken();
            }
        } else {
            //
        }

    }

    private void showSnackBar(String errorMsg) {
        Snackbar snack = Snackbar.make(parent, errorMsg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();

    }
    @OnClick(R.id.btnlogin)
    public void btnSignIn() {

        if (checkValidation()) {
            login();
        }
    }
    private boolean checkValidation() {

        boolean isValid= true;

        if (userEmail.getText().toString().isEmpty()){
            inputEmail.setError("*Email not be blank");
            isValid=false;
        } else {
            inputEmail.setErrorEnabled(false);
        }
        if (userPassword.getText().toString().trim().length() < 5){
            inputPassword.setError("*Minimum 5 character required");
            isValid=false;
        } else {
            inputPassword.setErrorEnabled(false);
        }
        return isValid;
    }

    private void login() {
        HashMap<String,String> params=new HashMap<>();
        params.put("email",userEmail.getText().toString());
        params.put("password",userPassword.getText().toString());
        params.put("type",loginType);
        params.put("device_token",deviceid);

        ApiHandler.apiHit(Request.Method.POST,Constants.getBaseURL() + Constants.USER_LOGIN,apiCall,this,params);
    }

    ApiHandler.ApiCallback apiCall= new ApiHandler.ApiCallback() {
        @Override
        public void onDataFetched(JSONObject jsonObject, ApiHandlerError error) {
            if (error == null){
                try {

                    user_obj = jsonObject.getJSONObject("user_data");
                    User.setCurrentUser(user_obj);
                    //Session_Bean.setCurrentSessiondetail(SeekerUser.getCurrentSeekerUser().getId(),1);
                    Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomePage.class));
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();


            }
        }
    };
    @OnClick(R.id.btn_sign_in)
    public void btnSign(){

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(SeekerSignInActivity.this,"SignOut",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                //   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                email=response.getEmail();


                Log.d("email",email);


                emailCheck();
//                startActivity(new Intent(SeekerSignInActivity.this,SeekerRegisterActivity.class));
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(LoginActivity.this,"Sign in failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void emailCheck() {
        HashMap<String,String> params=new HashMap<>();
        params.put("email",email);
        params.put("type",googleType);
        params.put("device_token",deviceid);
        params.put("password","password");
        ApiHandler.apiHit(Request.Method.POST,Constants.getBaseURL() + Constants.USER_LOGIN, apiCallBack, this,params);
    }
    ApiHandler.ApiCallback apiCallBack = new ApiHandler.ApiCallback() {
        @Override
        public void onDataFetched(JSONObject jsonObject, ApiHandlerError error) {
            if (error==null){
                try {
                    user_obj = jsonObject.getJSONObject("user_data");
                    User.setCurrentUser(user_obj);
                    //Session_Bean.setCurrentSessiondetail(SeekerUser.getCurrentSeekerUser().getId(),1);

                    Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomePage.class));
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                if (error.getStatusCode() == 401) {
                    //       if(type.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Email does not exists, Registered First", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    public void onClick(View view) {

        if(view == googlecstmbtn){
            btnSign();
        }
    }
}
