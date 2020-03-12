package com.jaats.agrovehicle.activity;

class ProviderFatchOnFatch {
    private String Fid;
    private Double latitude;
    private Double longitude;




    public ProviderFatchOnFatch( String id,Double latitude, Double longitude){
        this.Fid=id;
        this.latitude=latitude;
        this.longitude=longitude;

    }

public String getId(){return  Fid;}
    public  Double getLatitude(){return latitude;}
    public Double getLongitude(){return longitude;}


}
