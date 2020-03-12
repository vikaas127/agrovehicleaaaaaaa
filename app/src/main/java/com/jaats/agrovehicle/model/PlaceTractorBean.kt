package com.jaats.agrovehicle.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place

class PlaceTractorBean : BaseTractorBean(), Comparable<PlaceBean> {

    //    10.015861  76.341867  10.107570  76.345662

    var id: Int = 1
    var address: String = ""
    var latitude: String = ""
    var longitude: String = ""
    var name: String = ""
    var place: Place? = null


    fun getLatLng(): LatLng {
        return LatLng(dLatitude, dLongitude)
    }





    val dLatitude:Double
        get()  {
            try {
                return java.lang.Double.parseDouble(latitude)
            }catch (e:java.lang.NumberFormatException){
                e.printStackTrace()
                return 0.0
            }
        }


    val dLongitude:Double
        get() {
            try {
                return java.lang.Double.parseDouble(longitude)

            }catch (e:java.lang.NumberFormatException){
                e.printStackTrace()
                return 0.0
            }
        }


    override operator fun compareTo(other: PlaceBean): Int {
        val bean  = other
        return if (id == bean.id)
            0
        else if (id > bean.id)
            1
        else
            -1
    }
}
