package com.jaats.agrovehicle.activity;

class TankerProduct {
    private String tankerCategoryserviceType;
    private String tankerSubCategoryName;
    private String tankerSubcategoryPrice;
    public String typeTanker;
    public String tankerimage;
    private boolean isSelected;



    public TankerProduct(String serviceTypeTanker,String NameAddTanker,String priceAddTanker,String typeTanker,String image){

        this.tankerCategoryserviceType=serviceTypeTanker;
        this.tankerSubCategoryName=NameAddTanker;
        this.tankerSubcategoryPrice=priceAddTanker;
        this.typeTanker=typeTanker;
        this.tankerimage=image;

    }


    public String getTankerCategoryserviceType(){return tankerCategoryserviceType;}


    public String getTankerSubCategoryName(){return tankerSubCategoryName;}


    public String getTankerSubcategoryPrice(){return tankerSubcategoryPrice;}


    public String getTypeTanker(){return typeTanker;}
    public  void setType(String type){    this.typeTanker = type;  }

    public String getTankerimage(){return  tankerimage;}
    public void setTankerimage(String tankerimage){this.tankerimage=tankerimage;}

    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean selected){
        isSelected=selected;
    }
}
