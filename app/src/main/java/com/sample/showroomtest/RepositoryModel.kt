package com.sample.showroomtest


data class Repository(
    val items: List<Item>
)

data class Item(
    val id: Int,
    val name: String
)