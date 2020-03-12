package com.jaats.agrovehicle.activity;

class CouponProduct {
    private int id;
    private String promo_code;
    private int discount;
    private String discount_type;
    private String expiration;
    private String status;
    private String deleted_at;


    public CouponProduct(int id, String promo_code,int discount,String discount_type,String expiration,String status,String deleted_at){
        this.id=id;
        this.promo_code=promo_code;
        this.discount=discount;
        this.discount_type=discount_type;
        this.expiration=expiration;
        this.status=status;
        this.deleted_at=deleted_at;
    }


    public  int getId(){return id;}
    public String getPromo_code(){return promo_code;}
    public int getDiscount(){return discount;}
    public String getDiscount_type(){return discount_type;}
    public String getExpiration(){return expiration;}
    public String getStatus(){return status;}
    public String getDeleted_at(){return  deleted_at;}
}

