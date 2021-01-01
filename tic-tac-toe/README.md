# tic-tac-toe
https://kata-log.rocks/tic-tac-toe-kata

## Rules
In random order

* a game is over when all fields in a row are taken by a player
* players take turns taking fields until the game is over
* a game is over when all fields in a diagonal are taken by a player
* a game is over when all fields are taken
* there are two players in the game (X and O)
* a game has nine fields in a 3x3 grid
* a game is over when all fields in a column are taken by a player
* a player can take a field if not already taken

## How to run the tests

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.
