package com.jaats.agrovehicle.model

import java.io.Serializable

open class BaseTractorBean : Serializable {

    var isWebError: Boolean = false
    var status: String = ""
    var error: String = ""
    var errorMsg: String = ""
    var webMessage: String = ""


}