HOME_WON = 1

def updateTable(table, team):
    if team not in table:
        table[team] = 0
    table[team] += 3
    
def tournamentWinner(competitions, results):
    table = {}

    for competition, result in zip(competitions, results):
        [home, away] = competition
        winner = home if result == HOME_WON else away
        updateTable(table, winner)

    return max(table, key=table.get)


def test_sample():
    # [Home, Away]
    competitions = [
        ["HTML", "C#"],
        ["C#", "Python"],
        ["Python", "HTML"]
    ]

    # 1 = Home won
    # 0 = Away won
    results = [0, 0, 1]

    assert tournamentWinner(competitions, results) == "Python"
