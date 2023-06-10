package xtremedesar.com.Data.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MonedaConvertModel(
    @SerializedName("ID"        ) var ID      : String? = null,
    @SerializedName("Nombre"    ) var Nombre  : String? = null,
    @SerializedName("Pais"      ) var Pais    : String? = null,
    @SerializedName("Simbolo"   ) var Simbolo : String? = null
): Serializable
