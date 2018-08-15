package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(primaryKeys = [ "collection_id", "item_id" ])
class Collection {

    @ColumnInfo(name = "collection_id")
    var id: String = ""

    @ColumnInfo(name = "item_id")
    var itemId: String = ""

    @ColumnInfo(name = "collection_title")
    var title: String = ""

    var rank: Int = -1

    constructor(id: String, itemId: String, title: String, rank: Int) {
        this.id = id
        this.itemId = itemId
        this.rank = rank
        this.title=title
    }
}