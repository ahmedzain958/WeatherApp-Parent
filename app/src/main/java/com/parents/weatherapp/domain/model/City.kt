package com.parents.weatherapp.domain.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Embedded
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
    @Embedded(prefix = "coord_")
    val coord: Coord?
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readTypedObject(Coord.CREATOR)
    )

    constructor() : this(
        null, "UK", "London", Coord(51.50853, -0.12574)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(Id)
        parcel.writeString(country)
        parcel.writeString(name)
        parcel.writeParcelable(coord, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}