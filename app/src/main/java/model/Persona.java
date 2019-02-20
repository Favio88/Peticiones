package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Persona {

    private String Nombre;
    private String Apellido;
    private int Edad;

    public Persona(String nombre, String apellido, int edad){
        Nombre=nombre;
        Apellido=apellido;
        Edad=edad;
    }

    public Persona(JSONObject jsonObject) {
    }

    public Persona(List<JSONArray> jsonArray){

    }

    public String getNombre(){
        return Nombre;
    }

    public String getApellido(){
        return  Apellido;
    }

    public int getEdad(){
        return  Edad;
    }

    public void setNombre(String nombre){
        Nombre=nombre;
    }

    public void setApellido(String apellido){
        Apellido=apellido;
    }

    public void setEdad(int edad){
        Edad=edad;
    }


}
