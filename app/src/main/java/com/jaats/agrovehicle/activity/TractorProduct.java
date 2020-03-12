package com.jaats.agrovehicle.activity;

class TractorProduct {
    private int tractorcategoryid;
    private String tractorCategoryserviceType;
    private String tractorSubCategoryName;
    private String tractorSubcategoryImage;
    private String tractorSubcategoryPrice;
    public String typeTractor;
    public String tractorHorsePowerimage;
    private boolean isSelected;



    public TractorProduct(int id1,String serviceType,String NameAdd,String imageAdd,String priceAdd,String type,String image){
        this. tractorcategoryid=id1;
        this.tractorCategoryserviceType=serviceType;
        this.tractorSubCategoryName=NameAdd;
        this.tractorSubcategoryImage=imageAdd;
        this.tractorSubcategoryPrice=priceAdd;
        this.typeTractor=type;
        this.tractorHorsePowerimage=image;

    }




    public  int getTractorcategoryid(){return  tractorcategoryid;}
    public  void setSubcategoryid(int  tractorcategoryid){    this. tractorcategoryid =  tractorcategoryid;  }

    public String getTractorCategoryserviceType(){return tractorCategoryserviceType;}
    public  void setCategoryserviceType(String CategoryserviceType){    this.tractorCategoryserviceType = CategoryserviceType;  }

    public String getTractorSubCategoryName(){return tractorSubCategoryName;}
    public  void setSubCategoryName(String SubCategoryName){    this.tractorSubCategoryName = SubCategoryName;  }
    public String getSubcategoryImage(){return tractorSubcategoryImage;}
    public  void setSubcategoryImage(String SubcategoryImage){    this.tractorSubcategoryImage = SubcategoryImage;  }
    public String getTractorSubcategoryPrice(){return tractorSubcategoryPrice;}
    public  void setSubcategoryPrice(String SubcategoryPrice){    this.tractorSubcategoryPrice = SubcategoryPrice;  }

    public String getTypeTractor(){return typeTractor;}
    public  void setType(String type){    this.typeTractor = type;  }
    public String getTractorHorsePowerimage(){return  tractorHorsePowerimage;}
    public void setTractorHorsePowerimage(String tractorHorsePowerimage){this.tractorHorsePowerimage=tractorHorsePowerimage;}

  public boolean isSelected(){
        return isSelected;
  }
  public void setSelected(boolean selected){
        isSelected=selected;
  }
}
