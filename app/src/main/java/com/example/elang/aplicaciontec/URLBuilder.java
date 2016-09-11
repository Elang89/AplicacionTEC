package com.example.elang.aplicaciontec;

/**
 * Created by elang on 9/10/2016.
 */
public class URLBuilder {
    private String URL;

    private URLBuilder()
    {
        URL = "http://192.168.2.137/AplicacionTEC/";
    }

    public String requestGET(String parameter)
    {
        String resultURL = this.URL + parameter;
        return resultURL;
    }

}
