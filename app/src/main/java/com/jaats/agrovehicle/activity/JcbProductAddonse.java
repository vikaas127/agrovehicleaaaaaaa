package com.jaats.agrovehicle.activity;

class JcbProductAddonse {
    private int jcbcategoryid;
    private String jcbCategoryserviceType;
    private String jcbSubCategoryName;
    private String jcbSubcategoryImage;
    private String jcbSubcategoryPrice;
    public String typeJcb;
    private String typeimage;
    private boolean isSelected;



    public JcbProductAddonse(int id1,String serviceType,String NameAdd,String imageAdd,String priceAdd,String type,String image){
        this. jcbcategoryid=id1;
        this.jcbCategoryserviceType=serviceType;
        this.jcbSubCategoryName=NameAdd;
        this.jcbSubcategoryImage=imageAdd;
        this.jcbSubcategoryPrice=priceAdd;
        this.typeJcb=type;
        this.typeimage=image;

    }




    public  int getJcbcategoryid(){return  jcbcategoryid;}
    public  void setJcbcategoryid(int  jcbcategoryid){    this. jcbcategoryid =  jcbcategoryid;  }

    public String getjcbCategoryserviceType(){return jcbCategoryserviceType;}
    public  void setJcbCategoryserviceType(String jcbCategoryserviceType){    this.jcbCategoryserviceType = jcbCategoryserviceType;  }

    public String getJcbSubCategoryName(){return jcbSubCategoryName;}
    public  void setJcbSubCategoryName(String jcbSubCategoryName){    this.jcbSubCategoryName = jcbSubCategoryName;  }
    public String getJcbSubcategoryImage(){return jcbSubcategoryImage;}
    public  void setJcbSubcategoryImage(String jcbSubcategoryImage){    this.jcbSubcategoryImage = jcbSubcategoryImage;  }

    public String getJcbSubcategoryPrice(){return jcbSubcategoryPrice;}
    public  void setJcbSubcategoryPrice(String jcbSubcategoryPrice){    this.jcbSubcategoryPrice = jcbSubcategoryPrice;  }

    public String getTypeJcb(){return typeJcb;}
    public  void setTypeJcb(String typeJcb){    this.typeJcb = typeJcb;  }

    public String getTypeimage(){return typeimage;}
    public void setTypeimage(String typeimage){this.typeimage=typeimage;}

    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean selected){
        isSelected=selected;
    }
}
