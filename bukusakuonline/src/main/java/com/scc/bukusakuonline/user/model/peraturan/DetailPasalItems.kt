package com.scc.bukusakuonline.user.model.peraturan

import com.google.gson.annotations.SerializedName

data class DetailPasalItems(
        @SerializedName("id")
        val id :String?,
        @SerializedName("descPasal")
        val  descPasal : String?
)