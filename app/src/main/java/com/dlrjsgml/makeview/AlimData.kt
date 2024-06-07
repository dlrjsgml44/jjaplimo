package com.dlrjsgml.makeview

import com.google.gson.annotations.SerializedName

data class AlimData(
    @SerializedName("title")
    val title : String,
    @SerializedName("name")
    val names : String,
    @SerializedName("contents")
    val contents : String)