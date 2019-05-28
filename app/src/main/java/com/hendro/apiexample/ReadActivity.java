package com.hendro.apiexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hendro.apiexample.apis.MahasiswaAPI;
import com.hendro.apiexample.commons.GeneralActivity;
import com.hendro.apiexample.models.db.Mahasiswa;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends GeneralActivity {
    @Bind(R.id.et_search)
    EditText et_search;
    @Bind(R.id.btn_search)
    Button btn_search;
    @Bind(R.id.ll_data)
    LinearLayout ll_data;
    @Bind(R.id.tv_nim)
    TextView tv_nim;
    @Bind(R.id.tv_nama)
    TextView tv_nama;
    @Bind(R.id.tv_alamat)
    TextView tv_alamat;
    @Bind(R.id.tv_prodi)
    TextView tv_prodi;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private MahasiswaAPI mahasiswaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.bind(this);
        onClick();
    }

    protected void createRetrofit(){
        super.createRetrofit();
        mahasiswaAPI = retrofit.create(MahasiswaAPI.class);
    }

    private void onClick(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Call<Mahasiswa> findCall = mahasiswaAPI.getOne(et_search.getText().toString());
                findCall.enqueue(mahasiswaCallback);
            }
        });
    }

    Callback<Mahasiswa> mahasiswaCallback = new Callback<Mahasiswa>() {
        @Override
        public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
            if(response.isSuccessful()){
                progressBar.setVisibility(View.GONE);
                Mahasiswa mahasiswa = response.body();
                tv_nim.setText(mahasiswa.getNim());
                tv_nama.setText(mahasiswa.getNama());
                tv_alamat.setText(mahasiswa.getAlamat());
                tv_prodi.setText(mahasiswa.getProdi());
                ll_data.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call<Mahasiswa> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}
