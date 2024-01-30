package com.hi_lo.data

data class Course(val name: String,
                  val slope: Int,
                  val rating: Float,
                  val holes: List<Hole>)

data class Hole(val holeNum: Int,
                val holeHcp: Int,
                val holePar: Int)
