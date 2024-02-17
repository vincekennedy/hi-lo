package com.hi_lo.data
val augustaNational = Course(
    name = "Augusta National", slope = 129, rating = 72.1f, holes = listOf(
        Hole(1, 9, 4),
        Hole(2, 1, 5),
        Hole(3, 13, 4),
        Hole(4, 15, 3),
        Hole(5, 5, 4),
        Hole(6, 17, 3),
        Hole(7, 11, 4),
        Hole(8, 3, 5),
        Hole(9, 7, 4),
        Hole(10, 6, 4),
        Hole(11, 8, 4),
        Hole(12, 16, 3),
        Hole(13, 4, 5),
        Hole(14, 12, 4),
        Hole(15, 2, 5),
        Hole(16, 18, 3),
        Hole(17, 14, 4),
        Hole(18, 10, 4),
    )
)

val mapleCreek = Course(
    name = "Maple Creek", slope = 134, rating = 72.5f, holes = listOf(
        Hole(1, 7, 4),
        Hole(2, 1, 4),
        Hole(3, 3, 4),
        Hole(4, 13, 4),
        Hole(5, 15, 3),
        Hole(6, 9, 5),
        Hole(7, 5, 4),
        Hole(8, 11, 3),
        Hole(9, 17, 5),
        Hole(10, 6, 4),
        Hole(11, 2, 4),
        Hole(12, 10, 4),
        Hole(13, 14, 5),
        Hole(14, 8, 4),
        Hole(15, 18, 3),
        Hole(16, 16, 5),
        Hole(17, 4, 4),
        Hole(18, 12, 3),
    )
)

data class Course(
    val name: String,
    val slope: Int,
    val rating: Float,
    val holes: List<Hole>
)

data class Hole(
    val holeNum: Int,
    val holeHcp: Int,
    val holePar: Int
)
