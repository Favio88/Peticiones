package com.favio.peticiones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtv_nombre;
    List<Persona> personas=new ArrayList<Persona>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_jsonObject).setOnClickListener(this);
        findViewById(R.id.btn_jsonArray).setOnClickListener(this);
        txtv_nombre=findViewById(R.id.txtv_nombre);

        //VolleyS.getInstance(this).getRequestQueue().add();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_jsonObject:

                JsonObjectRequest peticion01=new JsonObjectRequest(
                        Request.Method.GET,
                        "http://nuevo.rnrsiilge-org.mx/principal",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try{
                                    //txtv_nombre.setText(response.getString("nombre"));
                                    Gson gson=new Gson();
                                    Persona persona=gson.fromJson(response.toString(), Persona.class);
                                    Log.d("valor", response.toString());

                                    txtv_nombre.setText((persona.getNombre()));

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                VolleyS.getInstance(this).getRequestQueue().add(peticion01);
                break;

            case R.id.btn_jsonArray:

                JsonArrayRequest peticion02=new JsonArrayRequest(
                        Request.Method.GET,
                        "http://nuevo.rnrsiilge-org.mx/lista",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                //for (int i=0; i<response.length(); i++) {
                                    try {
                                        //personas.add(new List<Persona>(response.getJSONObejct(i)));

                                        Gson gson = new Gson();

                                        Type listType=new TypeToken<List<Persona>>(){}.getType();
                                        personas=gson.fromJson(response.toString(), listType);

                                        Persona[] personas= gson.fromJson(response.toString(), Persona[].class);
                                        txtv_nombre.setText(personas[4].getNombre());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                //}
                                Log.d("valor", response.toString());
                                //txtv_nombre.setText(personas.get(4).getNombre());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                VolleyS.getInstance(this).getRequestQueue().add(peticion02);
                break;

        }

    }
}
