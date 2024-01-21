package com.orlando.androidbase.mappers

import com.orlando.androidbase.entities.db.PeopleCache
import com.orlando.androidbase.entities.remote.PeopleResponseItem


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

fun List<PeopleResponseItem>.toPeopleCacheList(): List<PeopleCache> {
    val listOfPeopleCache = arrayListOf<PeopleCache>()
    forEach {
        listOfPeopleCache.add(it.toPeopleCache())
    }
    return listOfPeopleCache
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

fun List<PeopleCache>.toPeopleResponseItemList(): List<PeopleResponseItem> {
    val listOfPeopleItem = arrayListOf<PeopleResponseItem>()
    forEach {
        listOfPeopleItem.add(it.toPeopleResponseItem())
    }
    return listOfPeopleItem
}

