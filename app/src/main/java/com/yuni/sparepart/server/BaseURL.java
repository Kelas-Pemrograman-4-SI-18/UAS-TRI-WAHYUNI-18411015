package com.yuni.sparepart.server;

public class BaseURL {

    public static String baseURL = "http://192.168.43.245:5050/";

    public static String login = baseURL + "transaksi/login";
    public static String registrasi = baseURL + "transaksi/registrasi";

    //sparepart
    public static String DataSparepart = baseURL +"sparepart/DataSparepart";

    public static String editDataSparepart = baseURL +"sparepart/ubah/";

    public static String hapusData = baseURL +"sparepart/hapus/";

    public static String inputSparepart = baseURL +"sparepart/input/";

}
