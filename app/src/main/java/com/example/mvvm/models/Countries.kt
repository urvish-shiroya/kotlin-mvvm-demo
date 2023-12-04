package com.example.mvvm.models

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("name") var name: CountriesName? = CountriesName(),
    @SerializedName("tld") var tld: List<String>? = mutableListOf(),
    @SerializedName("cca2") var cca2: String? = null,
    @SerializedName("ccn3") var ccn3: String? = null,
    @SerializedName("cca3") var cca3: String? = null,
    @SerializedName("independent") var independent: Boolean? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("unMember") var unMember: Boolean? = null,
    @SerializedName("currencies") var currencies: HashMap<String, Currencies>? = null,
    @SerializedName("capital") var capital: List<String>? = mutableListOf(),
    @SerializedName("altSpellings") var altSpellings: List<String>? = mutableListOf(),
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,
    @SerializedName("languages") var languages: HashMap<String, String>? = null,
    @SerializedName("latlng") var latlng: List<Double>? = mutableListOf(),
    @SerializedName("landlocked") var landlocked: Boolean? = null,
    @SerializedName("area") var area: Double? = 0.0,
    @SerializedName("flag") var flag: String? = null,
    @SerializedName("maps") var maps: MapsData? = MapsData(),
    @SerializedName("population") var population: Double? = 0.0,
    @SerializedName("flags") var flags: FlagsData? = FlagsData(),
    @SerializedName("coatOfArms") var coatOfArms: HashMap<String, String>? = null,
    @SerializedName("startOfWeek") var startOfWeek: String? = null,
)

data class CountriesName(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
)

data class Currencies(
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
)

data class MapsData(
    @SerializedName("googleMaps") var googleMaps: String? = null,
    @SerializedName("openStreetMaps") var openStreetMaps: String? = null,
)

data class FlagsData(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
)