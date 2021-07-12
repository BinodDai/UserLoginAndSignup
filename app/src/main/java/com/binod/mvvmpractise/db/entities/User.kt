package com.binod.mvvmpractise.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val Current_user_id=0
@Entity
data class User(
    var id:Int?=null,
    var name:String?=null,
    var email:String?=null,
    var password:String?=null,
    var email_verified_at:String?=null,
    var created_at:String?=null,
    var updated_at:String?=null
) {
    @PrimaryKey(autoGenerate = true)
    var uid:Int=Current_user_id
}