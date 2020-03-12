package com.jaats.agrovehicle.activity;

class FatchAddonproduct {
    private int serviceType;
    private String AddName;
    private int Price;





    public FatchAddonproduct(int serviceType, String AddName, int price) {
        this.serviceType=serviceType;
        this.AddName=AddName;
        this.Price=price;

    }


    public  int getServiceType(){return serviceType;}
    public String getAddName(){return AddName;}
    public int getPrice(){return Price;}

}
