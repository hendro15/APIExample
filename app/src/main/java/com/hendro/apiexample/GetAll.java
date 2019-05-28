package com.hendro.apiexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hendro.apiexample.adapters.MahasiswaViewAdapater;
import com.hendro.apiexample.apis.MahasiswaAPI;
import com.hendro.apiexample.commons.GeneralActivity;
import com.hendro.apiexample.models.db.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAll extends GeneralActivity {

    protected MahasiswaAPI mahasiswaAPI;
    protected MahasiswaViewAdapater mahasiswaViewAdapater;
    protected List<Mahasiswa> mahasiswaList;

    @Bind(R.id.rv_data)
    RecyclerView rv_data;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);
        ButterKnife.bind(this);
        setLayout();
    }

    @Override
    protected void createRetrofit(){
        super.createRetrofit();
        mahasiswaAPI = retrofit.create(MahasiswaAPI.class);
    }

    protected void setLayout(){
        mahasiswaList = new ArrayList<>();
        mahasiswaViewAdapater = new MahasiswaViewAdapater(getApplicationContext(), mahasiswaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_data.setLayoutManager(layoutManager);
        rv_data.setItemAnimator(new DefaultItemAnimator());
        rv_data.setAdapter(mahasiswaViewAdapater);
        getData();
    }

    protected void getData(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Mahasiswa>> mahasiswaCall = mahasiswaAPI.getAll();
        mahasiswaCall.enqueue(mahasiswaCallback);
    }

    Callback<List<Mahasiswa>> mahasiswaCallback = new Callback<List<Mahasiswa>>() {
        @Override
        public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
            progressBar.setVisibility(View.GONE);
            if(response.isSuccessful()){
                mahasiswaList = response.body();
                mahasiswaViewAdapater.setItems(mahasiswaList);
                mahasiswaViewAdapater.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Log.i("GetAll", "error : " + t.toString());
            Toast.makeText(getApplicationContext(), "Gagal Koneksi", Toast.LENGTH_LONG).show();
        }
    };
}
