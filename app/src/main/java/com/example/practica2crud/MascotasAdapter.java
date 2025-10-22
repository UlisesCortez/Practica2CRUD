package com.example.practica2crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.practica2crud.modelos.mascota;

public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.VH> {

    public interface Listener {
        void onClick(mascota m);
        void onLongClick(mascota m);
    }

    private ArrayList<mascota> data;
    private final Listener listener;

    public MascotasAdapter(ArrayList<mascota> data, Listener listener) {
        this.data = data;
        this.listener = listener;
    }

    public void setData(ArrayList<mascota> nueva) {
        this.data = nueva;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mascota, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        mascota m = data.get(position);
        h.tvNombre.setText(m.getNombre());
        h.tvEdad.setText("Edad: " + m.getEdad());

        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onClick(m);
        });
        h.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onLongClick(m);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvEdad;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
        }
    }
}
