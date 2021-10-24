package com.capstone.tubescapstone.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "api_table")
public class HotelItem{
    //gambar, nama, id, alamat, kordinat, no telp

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kode_hotel")
    private Integer kode_hotel;

    @SerializedName("nomor_telp")
    @ColumnInfo(name = "nomor_telp")
    private String nomorTelp;

    @SerializedName("kordinat")
    @ColumnInfo(name = "kordinat")
    private String kordinat;

    @SerializedName("nama")
    @ColumnInfo(name = "nama")
    private String nama;

    @SerializedName("gambar_url")
    @ColumnInfo(name = "gambar_url")
    private String gambarUrl;

    @SerializedName("id")
    @ColumnInfo (name = "id")
    private Integer id;

    @SerializedName("alamat")
    @ColumnInfo(name = "alamat")
    private String alamat;



    public void setKode_hotel(Integer kode_hotel) { this.kode_hotel = kode_hotel; }

    public Integer getKode_hotel(){return kode_hotel;}

    public void setNomorTelp(String nomorTelp){
        this.nomorTelp = nomorTelp;
    }

    public String getNomorTelp(){ return nomorTelp; }

    public void setKordinat(String kordinat){
        this.kordinat = kordinat;
    }

    public String getKordinat(){
        return kordinat;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setGambarUrl(String gambarUrl){
        this.gambarUrl = gambarUrl;
    }

    public String getGambarUrl(){
        return gambarUrl;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }
}
