let greet name age reddit =
    sprintf "your name is %s, you are %d years old, and your username is %s" name age reddit

let writeToFile text =
    let path = System.IO.Path.GetTempFileName()
    System.IO.File.WriteAllText(path, text)
    path

greet "Kyle" 24 "lambacurry"
|> writeToFile