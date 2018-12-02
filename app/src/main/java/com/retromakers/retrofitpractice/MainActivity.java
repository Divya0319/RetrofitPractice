package com.retromakers.retrofitpractice;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.retromakers.retrofitpractice.helpers.ApiIntegrationHelper;
import com.retromakers.retrofitpractice.helpers.DialogHelper;
import com.retromakers.retrofitpractice.models.request.NameJobRequest;
import com.retromakers.retrofitpractice.models.response.NameJobResponse;
import com.retromakers.retrofitpractice.models.response.SingleUserResponse;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected ApiIntegrationHelper mApiIntegrationHelper;
    public TextInputEditText mEmailET, mPasswordET;
    public TextView mIdTV, mFnameTV, mLnameTV;
    private ImageView mUserImageIV;
    private MaterialButton mPostBT, mGetBT;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailET = findViewById(R.id.et_email);
        mPasswordET = findViewById(R.id.et_password);
        mApiIntegrationHelper = new ApiIntegrationHelper();
        mUserImageIV = findViewById(R.id.iv_user_image);
        mPostBT = findViewById(R.id.bt_post);
        mIdTV = findViewById(R.id.tv_id);
        mFnameTV = findViewById(R.id.tv_fname);
        mLnameTV = findViewById(R.id.tv_lname);
        mGetBT = findViewById(R.id.bt_get);
        mPostBT.setOnClickListener(this);
        mGetBT.setOnClickListener(this);
        mDialog = DialogHelper.getProgressDialog(this);


    }

    private Callback<NameJobResponse> loginResponseCallback = new Callback<NameJobResponse>() {
        @Override
        public void onResponse(Call<NameJobResponse> call, Response<NameJobResponse> response) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            NameJobResponse loginResponse = response.body();
            String idCreated = loginResponse.getId();
            String createdAt = loginResponse.getCreatedAt();
            Toast.makeText(MainActivity.this, "ID received: " + idCreated + " " + createdAt, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(Call<NameJobResponse> call, Throwable t) {

        }
    };
    private Callback<SingleUserResponse> singleUserResponseCallback = new Callback<SingleUserResponse>() {
        @Override
        public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            SingleUserResponse singleUserResponse = response.body();
            int id = singleUserResponse.getData().getId();
            String fname = singleUserResponse.getData().getFirst_name();
            String lname = singleUserResponse.getData().getLast_name();
            String avatar_url = singleUserResponse.getData().getAvatar();
            Picasso.get().load(avatar_url).into(mUserImageIV);
            mIdTV.setText(String.valueOf(id));
            mFnameTV.setText(fname);
            mLnameTV.setText(lname);

        }

        @Override
        public void onFailure(Call<SingleUserResponse> call, Throwable t) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get:
                mDialog.show();
                mApiIntegrationHelper.getUserDetails(singleUserResponseCallback);
                break;
            case R.id.bt_post:
                mDialog.show();
                if (!mEmailET.getText().toString().equals("") || !mPasswordET.getText().toString().equals(""))
                    mApiIntegrationHelper.doLogin(new NameJobRequest(mEmailET.getText().toString(), mPasswordET.getText().toString()), loginResponseCallback);

                else {
                    Toast.makeText(this, "Fields are empty", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
