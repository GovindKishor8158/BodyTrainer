package com.govind.admin.bodytrainer.LoginModule;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.govind.admin.bodytrainer.BeanModule.User;
import com.govind.admin.bodytrainer.HomePage.HomePage;
import com.govind.admin.bodytrainer.Utility.Constants;
import com.govind.admin.bodytrainer.R;
import com.govind.admin.bodytrainer.Utility.ApiHandler;
import com.govind.admin.bodytrainer.Utility.ApiHandlerError;
import com.govind.admin.bodytrainer.Utility.FirebaseGetDeviceToken;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.inputUsrName)
    TextInputLayout inputName;
    @BindView(R.id.inputEmail)
    TextInputLayout inputEmail;
    @BindView(R.id.inputPassword)
    TextInputLayout inputPassword;
    @BindView(R.id.inputMobile)
    TextInputLayout inputMobile;


@BindView(R.id.userName)EditText userName;
@BindView(R.id.userEmail)EditText userEmail;
@BindView(R.id.userPassword)EditText userPassword;
@BindView(R.id.userMobile)EditText userMobile;
@BindView(R.id.userLocation)EditText userLocation;
@BindView(R.id.userheight)EditText userheight;
@BindView(R.id.userweight)EditText userweight;
@BindView(R.id.userBirthDate)EditText userBirthDate;
@BindView(R.id.calender)ImageView calender;
    private String deviceid;
    SharedPreferences pref;
    private int deviceIdChecked = 0;
    String birthDate="";
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    String radioValue="";
    String type="1";
    String email;
    private JSONObject user_obj;


    @BindView(R.id.parent)
    RelativeLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        userBirthDate.setEnabled(false);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b!=null)
        {
            String txtData = b.getString("email","");
            userEmail.setText(txtData);
        }



//          String deviceToken;
//          deviceToken = FirebaseInstanceId.getInstance().getToken();
//          saveInSharedPref(deviceToken);


















//
//        if(b!=null)
//        {
//            String txtData = b.getString("email","");
//            userEmail.setText(txtData);
//        }

//        String token= FirebaseInstanceId.getInstance().getToken();
//        Log.e("jhsdj",token);
//        userName.setText(token);
//        Log.e("gdgdgdgd",FirebaseGetDeviceToken.getDeviceToken());
       getDeviceid();
        radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) findViewById(i);
                radioValue=radioButton.getText().toString();
            }
        });
    }

    private void getDeviceid() {
        pref = RegistrationActivity.this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        deviceid = pref.getString(Constants.DEVICE_TOKEN, "");

        if (deviceid.isEmpty()) {
            FirebaseGetDeviceToken.getDeviceToken();
            //FirebaseInstanceId.getInstance().getToken();
        }
        if (deviceIdChecked != 0) {
            if (deviceIdChecked < 4) {
                checkDeviceIDValid();
            }
        }
    }

    public void checkDeviceIDValid() {
        if (deviceid == null && deviceid.isEmpty()) {
            if (deviceIdChecked < 3) {
                deviceIdChecked++;
                getDeviceid();
            } else {
                showSnackBar("Please wait.....");
               FirebaseGetDeviceToken.getDeviceToken();
                //FirebaseInstanceId.getInstance().getToken();
            }
        } else {
            registeruser();
        }
    }
    @OnClick(R.id.btnRegister)
    public void btnRegister(){

        if(checkValidation()){
            registeruser();
        }
    }

    private boolean checkValidation() {
        boolean isValid= true;
        email=userEmail.getText().toString();

        if (userName.getText().toString().isEmpty()){
            inputName.setError("*Name not be blank");
            isValid=false;
        } else {
            inputName.setErrorEnabled(false);
        }
        if (!isValidEmail(email)){
            inputEmail.setError("*InValid Email");
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
        if (userMobile.getText().toString().isEmpty()){
            inputMobile.setError("*Contact not be blank");
            isValid=false;
        } else {
            inputMobile.setErrorEnabled(false);
        }
        return isValid;
    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void registeruser() {

        HashMap<String,String> params= new HashMap<>();

        params.put("name",userName.getText().toString());
        params.put("email",userEmail.getText().toString());
        params.put("password",userPassword.getText().toString());
        params.put("mobileno",userMobile.getText().toString());
        params.put("location",userLocation.getText().toString());
        params.put("height",userheight.getText().toString());
        params.put("weight",userweight.getText().toString());
        params.put("device_token",deviceid);
        Log.e("device_token",deviceid);
        params.put("gender",radioValue);
        params.put("birth_date",birthDate);
        params.put("type",type);
        ApiHandler.apiHit(Request.Method.POST,Constants.getBaseURL() + Constants.REGISTER_URL,apiCall,this,params);
       // ApiHandler.apiHit(Request.Method.POST, Constants.getBaseURL() + Constants.REGISTER_URL, apiCallBackss,this,params);
    }

    ApiHandler.ApiCallback apiCall= new ApiHandler.ApiCallback() {
        @Override
        public void onDataFetched(JSONObject jsonObject, ApiHandlerError error) {
            if (error== null){

                try {
                    user_obj=jsonObject.getJSONObject("user_data");
                    User.setCurrentUser(user_obj);


                    Toast.makeText(RegistrationActivity.this, "registered", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegistrationActivity.this,HomePage.class));

                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }  else{
                Toast.makeText(RegistrationActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    };

    @OnClick(R.id.calender)
    public void calender(){
        java.util.Calendar cal = java.util.Calendar.getInstance(TimeZone.getDefault()); // Get current date
        DatePickerDialog datePicker = new DatePickerDialog(this,
                R.style.MyDatePickerDialogTheme, ondateRene,
                cal.get(java.util.Calendar.YEAR),
                cal.get(java.util.Calendar.MONTH),
                cal.get(java.util.Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(new Date().getTime());
        datePicker.setCancelable(false);
        datePicker.show();
    }
    DatePickerDialog.OnDateSetListener ondateRene = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            java.util.Calendar calendar = java.util.Calendar.getInstance(TimeZone.getDefault());
            calendar.set(year, monthOfYear, dayOfMonth);
            Date dbirthdate = calendar.getTime();
            Calendar value_datePicker = calendar;
            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = fmtOut.format(dbirthdate);
            birthDate=dateString;
            userBirthDate.setText(dateString);
        }
    };
    private void showSnackBar(String errorMsg) {

        Snackbar snack = Snackbar.make(parent, errorMsg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();

    }
//    public  void saveInSharedPref(String deviceToken) {
//        if(deviceToken!=null) {
//            SharedPreferences   preferences = RegistrationActivity.this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
//            preferences.edit().putString(Constants.DEVICE_TOKEN, deviceToken).apply();
//
//        }
//    }
}
