package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class Collection {

    @PrimaryKey
    @ColumnInfo(name = "collection_id")
    var id: String = ""

    @ColumnInfo(name = "item_id")
    var itemId: String = ""

    var rank: Int = -1

    constructor(id: String, itemId: String, rank: Int) {
        this.id = id
        this.itemId = itemId
        this.rank = rank
    }
}