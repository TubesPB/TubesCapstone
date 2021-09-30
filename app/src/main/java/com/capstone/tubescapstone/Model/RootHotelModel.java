package com.capstone.tubescapstone.Model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class RootHotelModel {

    private ArrayList<HotelItem> hotel;

    public void setHotel(ArrayList<HotelItem> hotel){
        this.hotel = hotel;
    }

    public ArrayList<HotelItem> getHotel(){
        return hotel;
    }
}