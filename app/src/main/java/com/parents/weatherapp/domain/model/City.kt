package com.parents.weatherapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.parents.weatherapp.data.source.local.database.DatabaseConstants.TABLE_CITY

@Entity(tableName = TABLE_CITY)
data class City(
    @PrimaryKey(autoGenerate = true)
    var Id: Int?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "lat")
    val lat: Double?,
    @ColumnInfo(name = "lon")
    val lon: Double?
) {
    constructor() : this(
        null, "UK", "London", 51.50853, -0.12574
    )
}