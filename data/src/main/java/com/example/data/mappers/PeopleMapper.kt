package com.example.data.mappers

import com.example.data.db.entities.PeopleCache
import com.example.domain.entities.remote.PeopleResponseItem


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