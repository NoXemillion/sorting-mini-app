package com.example.RoomDB

fun quickSortUsers(users: List<User>): List<User> {
    if (users.size < 2) return users // Базовый случай: список из 0 или 1 элемента уже отсортирован

    val pivot = users[users.size / 2] // Выбор опорного элемента
    val equal = users.filter { it.number == pivot.number } // Элементы, равные pivot
    val less = users.filter { it.number < pivot.number }  // Элементы, меньше pivot
    val greater = users.filter { it.number > pivot.number } // Элементы, больше pivot

    // Рекурсивно сортируем и объединяем
    return quickSortUsers(less) + equal + quickSortUsers(greater)
}