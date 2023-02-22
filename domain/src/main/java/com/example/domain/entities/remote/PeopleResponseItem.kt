package com.example.domain.entities.remote

data class PeopleResponseItem(
    val affiliations: List<String>,
    //val apprentices: List<String>, // and String
    //val armament: List<String>, // and String
    val born: Any,
    val bornLocation: String,
    val `class`: String,
    val creator: String,
    //val cybernetics: String,  String and List<String>
    val dateCreated: Int,
    val dateDestroyed: Int,
    val degree: String,
    val destroyedLocation: String,
    val died: Int,
    val diedLocation: String,
    //val equipment: String, String and List<String>
    //val era: List<String>, // And String
    val eyeColor: String,
    val formerAffiliations: List<String>,
    val gender: String,
    val hairColor: String,
    val height: Double,
    //val homeworld: String, // and List<String>
    val id: Int,
    val image: String,
    val kajidic: String,
    val manufacturer: String,
    val mass: Double,
    //val masters: List<String>, and String
    val model: String,
    val name: String,
    val platingColor: String,
    val productLine: String,
    val sensorColor: String,
    val skinColor: String,
    val species: String,
    val wiki: String
)