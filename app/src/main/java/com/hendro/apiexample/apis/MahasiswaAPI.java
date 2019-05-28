package com.hendro.apiexample.apis;

import com.hendro.apiexample.models.db.Status;
import com.hendro.apiexample.models.db.Mahasiswa;
import com.hendro.apiexample.utils.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MahasiswaAPI {

    @GET(Url.DATA_MHS)
    Call<List<Mahasiswa>> getAll();

    @FormUrlEncoded
    @POST(Url.READ)
    Call<Mahasiswa> getOne(@Field("nim") String nim);

    @FormUrlEncoded
    @POST(Url.CREATE)
    Call<Status> createOne(@Field("nim") String nim,
                           @Field("nama") String nama,
                           @Field("alamat") String alamat,
                           @Field("prodi") String prodi);

    @FormUrlEncoded
    @PUT(Url.UPDATE)
    Call<Mahasiswa> update(@Path("id") int id,
                           @Field("nim") String nim,
                           @Field("nama") String nama,
                           @Field("alamat") String alamat,
                           @Field("prodi") String prodi);
}
