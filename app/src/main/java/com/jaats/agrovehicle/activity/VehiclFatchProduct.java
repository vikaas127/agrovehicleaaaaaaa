package com.jaats.agrovehicle.activity;

class VehiclFatchProduct {
    private int id;
    private String name;
    private String provider_name;
    private String image;
    private int capacity;
    private int fixed;
    private int price;
    private  int minute;
    private int distance;
    private String calculator;
    private int status;


    public VehiclFatchProduct(int id, String name,String provider_name,String image,int capacity,int fixed,int price,int minute,int distance,String calculator,int status){
        this.id=id;
        this.name=name;
        this.provider_name=provider_name;
        this.image=image;
        this.capacity=capacity;
        this.fixed=fixed;
        this.price=price;
        this.minute=minute;
        this.distance=distance;
        this.calculator=calculator;
        this.status=status;
    }


    public  int getId(){return id;}
    public String getName(){return name;}
    public String getProvider_name(){return provider_name;}
    public String getImage(){return image;}
    public int getCapacity(){return capacity;}
    public int getFixed(){return fixed;}
    public int getPrice(){return  price;}
    public int getMinute(){return minute;}
    public int getDistance(){return distance;}
    public String getCalculator(){return calculator;}
    public int getStatus(){return status;}
}

