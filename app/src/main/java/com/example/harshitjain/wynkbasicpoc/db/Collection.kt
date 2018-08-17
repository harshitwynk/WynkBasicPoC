package com.example.harshitjain.wynkbasicpoc.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

@Entity(primaryKeys = [ "collection_id", "item_id" ])
class Collection(
    @ColumnInfo(name = "collection_id") var id: String, @ColumnInfo(name = "item_id") var itemId: String, @ColumnInfo(
        name = "collection_title"
    ) var title: String, var rank: Int
){
    companion object {
        const val TOP_PLAYLIST_ID = "srch_bsb_1490263494633,srch_bsb_1402666444551"
    }
}