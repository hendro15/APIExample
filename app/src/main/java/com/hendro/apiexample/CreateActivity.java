package com.hendro.apiexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hendro.apiexample.apis.MahasiswaAPI;
import com.hendro.apiexample.commons.GeneralActivity;
import com.hendro.apiexample.models.db.Status;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends GeneralActivity {
    @Bind(R.id.et_nama)
    EditText et_nama;
    @Bind(R.id.et_nim)
    EditText et_nim;
    @Bind(R.id.et_alamat)
    EditText et_alamat;
    @Bind(R.id.et_prodi)
    EditText et_prodi;
    @Bind(R.id.btn_save)
    Button btn_save;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    protected MahasiswaAPI mahasiswaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);
        onClick();
    }

    @Override
    protected void createRetrofit() {
        super.createRetrofit();
        mahasiswaAPI = retrofit.create(MahasiswaAPI.class);
    }

    private void onClick() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Call<Status> addCall = mahasiswaAPI.createOne(et_nim.getText().toString(),
                        et_nama.getText().toString(),
                        et_alamat.getText().toString(),
                        et_prodi.getText().toString());
                addCall.enqueue(statusCallback);
            }
        });
    }

    Callback<Status> statusCallback = new Callback<Status>() {
        @Override
        public void onResponse(Call<Status> call, Response<Status> response) {
            progressBar.setVisibility(View.GONE);
            if (response.isSuccessful()) {
                Status status = response.body();
                Toast.makeText(getApplicationContext(), status.getMessage(), Toast.LENGTH_LONG).show();
                setResult(200);
                finish();
            }
        }

        @Override
        public void onFailure(Call<Status> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}
