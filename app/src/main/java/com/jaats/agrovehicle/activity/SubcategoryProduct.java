package com.jaats.agrovehicle.activity;

import java.util.List;

class SubcategoryProduct {
    private int Subcategoryid;
    private String CategoryserviceType;
    private String SubCategoryName;
    private String SubcategoryImage;
    private String SubcategoryPrice;
    public String type;




    public SubcategoryProduct(int id1,String serviceType,String NameAdd,String imageAdd,String priceAdd,String type){
        this.Subcategoryid=id1;
        this.CategoryserviceType=serviceType;
        this.SubCategoryName=NameAdd;
        this.SubcategoryImage=imageAdd;
        this.SubcategoryPrice=priceAdd;
        this.type=type;

    }




    public  int getSubcategoryid() {return Subcategoryid;}
    public  void setSubcategoryid(int Subcategoryid){    this.Subcategoryid = Subcategoryid;  }

    public String getCategoryserviceType(){return CategoryserviceType;}
    public  void setCategoryserviceType(String CategoryserviceType){    this.CategoryserviceType = CategoryserviceType;  }

    public String getSubCategoryName(){return SubCategoryName;}
    public  void setSubCategoryName(String SubCategoryName){    this.SubCategoryName = SubCategoryName;  }
    public String getSubcategoryImage(){return SubcategoryImage;}
    public  void setSubcategoryImage(String SubcategoryImage){    this.SubcategoryImage = SubcategoryImage;  }
    public String getSubcategoryPrice(){return SubcategoryPrice;}
    public  void setSubcategoryPrice(String SubcategoryPrice){    this.SubcategoryPrice = SubcategoryPrice;  }

    public String getType(){return type;}
    public  void setType(String type){    this.type = type;  }
}
