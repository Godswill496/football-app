# Football App

## Background
Football App was created to manage players, matches, and match-player records in a simple, console-based application. It is lightweight, has minimal dependencies, and is easy to use.

## Features
- Add Players with details like name, date of birth, shirt number, salary, position, and injury status.
- Add Matches with name, location, and manager on duty.
- Record players participating in matches with goals scored, play position, and minutes played.
- List all players sorted by salary.
- View match summaries with statistics like number of players played, salary costs, and preferred positions.
- Robust input handling to prevent crashes when entering incorrect values.
- Logging support to trace function calls and actions in the console.

- Version 2.0 – New Features
✔ Added Player, Match & MatchPlayer Filtering

List played players

List not played players

Count played / not played

List players sorted by salary

✔ Added Formatting Utilities

formatListString() for clean printed lists

✔ Added JUnit Tests

PlayerControllerTest

MatchControllerTest

MatchPlayerControllerTest

Added new tests for filtering, searching, and counting
