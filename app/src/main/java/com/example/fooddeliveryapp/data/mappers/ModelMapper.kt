package com.example.fooddeliveryapp.data.mappers

interface ModelMapper <API, DOMAIN, DATABASE> {

    fun apiToDomain(apiResponse: API): List<DOMAIN>

    fun apiToDatabase(apiResponse: API): List<DATABASE>

    fun databaseToDomain(databaseModelsList: List<DATABASE>): List<DOMAIN>

}