package bnc.tech.myapplication.network.dto

import com.google.gson.annotations.SerializedName

class BaseModel<R>(@SerializedName("data") val response: List<R>

)
class BaseModelObj<R>(@SerializedName("data") val response: R

)

