package com.example.restapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import Model.PokemonData;
import Adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RequestQueue requestQueue;
    private MyAdapter adapter;
    List<PokemonData> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        list = new ArrayList<>();
        getPokemonList();
    }
    private void getPokemonList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://dummyapi.online/api/pokemon",
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Log.d("response", "onResponse: " + jsonArray);
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("pokemon");
                        String type = jsonObject.getString("type");
                        String image = jsonObject.getString("image_url");
                        list.add(new PokemonData(name,type,image));
                    } catch (JSONException e) {
                        Log.d("TAG", "onResponse: " + e);
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    adapter = new MyAdapter(MainActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("error","onErrorResponse" + volleyError);
                Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}