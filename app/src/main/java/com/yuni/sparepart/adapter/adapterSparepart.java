package com.yuni.sparepart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuni.sparepart.R;
import com.yuni.sparepart.model.ModelSparepart;
import com.yuni.sparepart.server.BaseURL;

import java.util.List;

public class adapterSparepart extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelSparepart> item;

    public adapterSparepart(Activity activity, List<ModelSparepart> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_sparepart, null);


        TextView jenisBarang    = (TextView) convertView.findViewById(R.id.txtjenisBarang);
        TextView kodeBarang    = (TextView) convertView.findViewById(R.id.txtkodeBarang);
        TextView jumlahBarang   = (TextView) convertView.findViewById(R.id.txtjumlahBarang);
        TextView hargaBarang    = (TextView) convertView.findViewById(R.id.txthargaBarang);
        ImageView gambar        = (ImageView) convertView.findViewById(R.id.gambar);

        jenisBarang.setText(item.get(position).getJenisBarang());
        kodeBarang.setText(item.get(position).getKodeBarang());
        jumlahBarang.setText(item.get(position).getJumlahBarang());
        hargaBarang.setText("Rp." + item.get(position).getHargaBarang());
        Picasso.get().load(BaseURL.baseURL + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambar);
        return convertView;
    }
}
