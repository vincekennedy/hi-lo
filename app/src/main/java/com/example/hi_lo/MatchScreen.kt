package com.example.hi_lo

enum class MatchScreen(val title: String) {
  Start(title = "Match Setup"),
  HOLE(title = "Current Hole"),
  SUMMARY(title = "Summary"),
  SETTLE(title = "Settle Up")
}
