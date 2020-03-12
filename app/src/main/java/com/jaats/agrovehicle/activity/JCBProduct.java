package com.jaats.agrovehicle.activity;

class JCBProduct {
    private int jcbcategoryid;
    private String jcbCategoryserviceType;
    private String jcbSubCategoryName;
    private String jcbSubcategoryImage;
    private String jcbSubcategoryPrice;
    public String typejcb;
    public String tractorHorsePowerimage;
    private boolean isSelected;



    public JCBProduct(int id1,String serviceType,String NameAdd,String imageAdd,String priceAdd,String type,String image){
        this. jcbcategoryid=id1;
        this.jcbCategoryserviceType=serviceType;
        this.jcbSubCategoryName=NameAdd;
        this.jcbSubcategoryImage=imageAdd;
        this.jcbSubcategoryPrice=priceAdd;
        this.typejcb=type;
        this.tractorHorsePowerimage=image;

    }




    public  int getJcbcategoryid(){return  jcbcategoryid;}
    public  void setJcbcategoryid(int  jcbcategoryid){    this. jcbcategoryid =  jcbcategoryid;  }

    public String getJcbCategoryserviceType(){return jcbCategoryserviceType;}
    public  void setJcbCategoryserviceType(String jcbCategoryserviceType){    this.jcbCategoryserviceType = jcbCategoryserviceType;  }

    public String getJcbSubCategoryName(){return jcbSubCategoryName;}
    public  void setJcbSubCategoryName(String jcbSubCategoryName){    this.jcbSubCategoryName = jcbSubCategoryName;  }
    public String getJcbSubcategoryImage(){return jcbSubcategoryImage;}
    public  void setJcbSubcategoryImage(String jcbSubcategoryImage){    this.jcbSubcategoryImage = jcbSubcategoryImage;  }

    public String getJcbSubcategoryPrice(){return jcbSubcategoryPrice;}
    public  void setJcbSubcategoryPrice(String jcbSubcategoryPrice){    this.jcbSubcategoryPrice = jcbSubcategoryPrice;  }

    public String getTypejcb(){return typejcb;}

    public  void setTypejcb(String typejcb)
    {
        this.jcbCategoryserviceType = jcbCategoryserviceType;
    }
    public String getTractorHorsePowerimage(){return  tractorHorsePowerimage;}
    public void setTractorHorsePowerimage(String tractorHorsePowerimage){this.tractorHorsePowerimage=tractorHorsePowerimage;}

    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean selected){
        isSelected=selected;
    }
}
