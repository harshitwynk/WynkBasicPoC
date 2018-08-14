package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class Item {

    @PrimaryKey
    var id: String=""

    var smallImage: String? = null
    var total: Int? = null
    var title: String? = null
    var subtitle: String? = null
    var count: Int? = null
    var offset: Int? = null
    var type: String? = null
    var duration: Int? = null
    var itemContentLang: String? = null

    @Ignore
    var items: List<Item>? = null

}