package com.prabha.wekan.ui.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
class Stations: Serializable {
    @SerializedName("total")
    @Expose
    var Total: Int? = 0
}