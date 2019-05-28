package com.hendro.apiexample.utils;

public class Url {
    public static final String SERVER = "http://10.0.2.2:8000/";
    public static final String API = SERVER + "api/";

    public static final String DATA_MHS = API + "mahasiswa/all";
    public static final String READ = API + "mahasiswa";
    public static final String CREATE = API + "mahasiswa/add";
    public static final String UPDATE = API + "mahasiswa/update/{id}";
}
