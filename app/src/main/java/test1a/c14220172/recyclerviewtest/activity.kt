package test1a.c14220172.recyclerviewtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class activity(
    val id : Int,
    val nama : String,
    val imageUrl : String,
    val deskripsi : String,
    var deadline : String,
    var status: String
) : Parcelable
