package com.yuni.sparepart.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yuni.sparepart.R;
import com.yuni.sparepart.adapter.adapterSparepart;
import com.yuni.sparepart.model.ModelSparepart;
import com.yuni.sparepart.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.yuni.sparepart.server.BaseURL.baseURL;

public class ActivityDataSparepart extends AppCompatActivity {

    ProgressDialog pDialog;

    adapterSparepart adapter;
    ListView list;

    ArrayList<ModelSparepart> newsList = new ArrayList<ModelSparepart>();
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sparepart);

        getSupportActionBar().setTitle("Data Sparepart");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new adapterSparepart(ActivityDataSparepart.this, newsList);
        list.setAdapter(adapter);
        getAllSparepart();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataSparepart.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllSparepart() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.DataSparepart, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("Data Sparepart = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelSparepart sparepart = new ModelSparepart();
                                    final String _id = jsonObject.getString("_id");
                                    final String jenisBarang = jsonObject.getString("jenisBarang");
                                    final String kodeBarang = jsonObject.getString("kodeBarang");
                                    final String jumlahBarang = jsonObject.getString("jumlahBarang");
                                    final String hargaBarang = jsonObject.getString("hargaBarang");
                                    final String gambar = jsonObject.getString("gambar");
                                    sparepart.setKodeBarang(kodeBarang);
                                    sparepart.setJenisBarang(jenisBarang);
                                    sparepart.setJumlahBarang(jumlahBarang);
                                    sparepart.setHargaBarang(hargaBarang);
                                    sparepart.setGambar(gambar);

                                    sparepart.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataSparepart.this, EditSparepartDanHapusActivity.class);
                                            a.putExtra("kodeBarang", newsList.get(position).getKodeBarang());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("jenisBarang", newsList.get(position).getJenisBarang());
                                            a.putExtra("jumlahBarang", newsList.get(position).getJumlahBarang());
                                            a.putExtra("hargaBarang", newsList.get(position).getHargaBarang());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                     }
                                   });
                                    newsList.add(sparepart);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}