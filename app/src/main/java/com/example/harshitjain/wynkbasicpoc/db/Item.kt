package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class Item() {
    constructor(item: Item) : this() {
        id = item.id;
        smallImage = item.smallImage
        total = item.total
        title = item.title
        subtitle = item.subtitle
        count = item.count
        offset = item.offset
        type = item.type
        duration = item.duration
        itemContentLang = item.itemContentLang

        items = item.items
    }

    @PrimaryKey
    var id: String = ""

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