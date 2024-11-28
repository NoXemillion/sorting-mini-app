package com.example.RoomDB

fun radixSortUsers(users: List<User>): List<User> {
    if (users.isEmpty()) return users

    val maxNumber = users.maxOf { it.number }
    var place = 1
    var sortedUsers = users

    while (maxNumber / place > 0) {
        sortedUsers = countingSortByPlace(sortedUsers, place)
        place *= 10
    }

    return sortedUsers
}
private fun countingSortByPlace(users: List<User>, place: Int): List<User> {
    val output = MutableList(users.size) { User() }
    val count = IntArray(10)


    for (user in users) {
        val digit = (user.number / place) % 10
        count[digit]++
    }


    for (i in 1 until 10) {
        count[i] += count[i - 1]
    }


    for (i in users.size - 1 downTo 0) {
        val user = users[i]
        val digit = (user.number / place) % 10
        output[count[digit] - 1] = user
        count[digit]--
    }

    return output
}