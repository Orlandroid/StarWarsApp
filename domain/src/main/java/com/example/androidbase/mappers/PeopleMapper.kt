package com.example.androidbase.mappers

import com.example.androidbase.entities.db.PeopleCache
import com.example.androidbase.entities.remote.PeopleResponseItem




fun PeopleResponseItem.toPeopleCache(): PeopleCache {
    return PeopleCache(
        0,
        gender = gender,
        hairColor = hairColor,
        height = height,
        homeworld = homeworld,
        id = id,
        image = image,
        mass = mass,
        name = name,
        skinColor = skinColor
    )
}

fun PeopleCache.toPeopleResponseItem(): PeopleResponseItem {
    return PeopleResponseItem(
        gender = gender,
        hairColor = hairColor,
        height = height,
        homeworld = homeworld,
        id = id,
        image = image,
        mass = mass,
        name = name,
        skinColor = skinColor,
    )
}