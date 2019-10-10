package com.example.myapplication

/**
 * Created by nickevan on 10,October,2019
 */
data class Note(
    var id: Int,
    var username: String,
    var title: String,
    var description: String
) {
    constructor() : this(0, "", "", "")
}