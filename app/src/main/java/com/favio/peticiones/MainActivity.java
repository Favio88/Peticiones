package com.favio.peticiones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import model.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtv_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_peticion).setOnClickListener(this);
        txtv_nombre=findViewById(R.id.txtv_nombre);

        //VolleyS.getInstance(this).getRequestQueue().add();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_peticion:

                JsonObjectRequest peticion01=new JsonObjectRequest(
                        Request.Method.GET,
                        "http://nuevo.rnrsiilge-org.mx/principal",
                        //"http://nuevo.rnrsiilge-org.mx/Lista", devolver el objeto 5 con el nombre

                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try{
                                    //txtv_nombre.setText(response.getString("nombre"));
                                    Gson gson=new Gson();
                                    Persona p=gson.fromJson(response.toString(), Persona.class);
                                    Log.d("valor", response.toString());

                                    txtv_nombre.setText((p.getNombre()));

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
        }

    }
}
