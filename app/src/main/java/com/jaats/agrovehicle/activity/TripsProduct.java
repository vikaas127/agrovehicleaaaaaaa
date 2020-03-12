package com.jaats.agrovehicle.activity;

class TripsProduct {


    private int id;
    private String booking_id;
    private int user_id;
    private int provider_id;
private  String payment_mode;
private String finished_at;

    public TripsProduct(int id, String booking_id, int user_id,  int provider_id,String payment_mode,String finished_at){
        this.payment_mode=payment_mode;
        this.finished_at=finished_at;
        this.id=id;
        this.booking_id=booking_id;
        this.user_id=user_id;
        this.provider_id=provider_id;
    }


public  int getId(){return id;}
public String getBooking_id(){return booking_id;}
public int getUser_id(){return user_id;}
public int getProvider_id(){return provider_id;}
public String getPayment_mode(){return payment_mode;}
public String getFinished_at(){return finished_at;}
}
