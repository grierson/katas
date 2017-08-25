module Diamond

open System


let make letter = 
    let mirrorAndFuse l = l @ (l |> List.rev |> List.tail)
    let makeLine letterCount (letter, letterIndex) =
        let leadingSpace = String(' ', letterCount - 1 - letterIndex)
        let innerSpace = String(' ', letterCount - 1 - leadingSpace.Length)
        let left = 
            sprintf "%s%c%s" leadingSpace letter innerSpace 
            |> Seq.toList
        left
        |> mirrorAndFuse
        |> List.map string
        |> List.reduce (sprintf "%s%s")

    let indexedLetters = ['A' .. letter] |> List.mapi (fun i l -> l, i)
    indexedLetters
    |> mirrorAndFuse
    |> List.map (makeLine indexedLetters.Length)
    |> List.reduce (fun x y -> sprintf "%s%s%s" x Environment.NewLine y)