package com.hendro.apiexample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendro.apiexample.R;
import com.hendro.apiexample.models.db.Mahasiswa;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MahasiswaViewAdapater extends RecyclerView.Adapter<MahasiswaViewAdapater.MyViewHolder> {

    protected Context context;
    protected List<Mahasiswa> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_nim)
        TextView tv_nim;
        @Bind(R.id.tv_nama)
        TextView tv_nama;
        @Bind(R.id.tv_alamat)
        TextView tv_alamat;
        @Bind(R.id.tv_prodi)
        TextView tv_prodi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public MahasiswaViewAdapater(Context context, List<Mahasiswa> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mahasiswa, viewGroup, false);
        return new MahasiswaViewAdapater.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        items.get(i);
        myViewHolder.tv_nim.setText(items.get(i).getNim());
        myViewHolder.tv_nama.setText(items.get(i).getNama());
        myViewHolder.tv_alamat.setText(items.get(i).getAlamat());
        myViewHolder.tv_prodi.setText(items.get(i).getProdi());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Mahasiswa> items) {
        this.items = items;
    }
}
