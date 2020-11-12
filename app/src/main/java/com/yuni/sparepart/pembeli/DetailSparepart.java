package com.yuni.sparepart.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yuni.sparepart.R;
import com.yuni.sparepart.server.BaseURL;

public class DetailSparepart extends AppCompatActivity {

    EditText edtkodeBarang, edtjenisBarang, edtjumlahBarang, edthargaBarang;
    ImageView imgGambar;

    String strkodeBarang, strjenisBarang, strjumlahBarang, strhargaBarang, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sparepart);

        edtkodeBarang = (EditText) findViewById(R.id.edtkodeBarang);
        edtjenisBarang = (EditText) findViewById(R.id.edtjenisBarang);
        edtjumlahBarang = (EditText) findViewById(R.id.edtjumlahBarang);
        edthargaBarang = (EditText) findViewById(R.id.edthargaBarang);

        imgGambar = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strkodeBarang = i.getStringExtra("kodeBarang");
        strjenisBarang = i.getStringExtra("jenisBarang");
        strjumlahBarang = i.getStringExtra("jumlahBarang");
        strhargaBarang = i.getStringExtra("hargaBarang");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtkodeBarang.setText(strkodeBarang);
        edtjenisBarang.setText(strjenisBarang);
        edtjumlahBarang.setText(strjumlahBarang);
        edthargaBarang.setText(strhargaBarang);
        Picasso.get().load(BaseURL.baseURL + "gambar/" + strGambar)
                .into(imgGambar);
    }
}