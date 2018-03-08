module DiamondProperties

open System
open FsCheck
open Expecto
open Expecto.ExpectoFsCheck

open Diamond
let split (x : string) =
    x.Split([| Environment.NewLine |], StringSplitOptions.None)

let trim (x : string) =
    x.Trim()

let leadingSpaces (x : string) =
    let indexOfNonSpace = x.IndexOfAny [| 'A' .. 'Z' |]
    x.Substring(0, indexOfNonSpace)

let trailingSpaces (x : string) =
    let lastIndexOfNonSpace = x.LastIndexOfAny [| 'A' .. 'Z' |]
    x.Substring(lastIndexOfNonSpace + 1)

type Letters =
    static member Char() = Gen.elements ['A' .. 'Z'] |> Arb.fromGen

let config = {
    FsCheckConfig.defaultConfig with arbitrary = [typeof<Letters>]
}

[<Tests>]
let properties =
    testList "Test Dimond Properties" [
        testPropertyWithConfig config "Diamond is non-empty" <| fun (letter : char) ->
            let actual = Diamond.make letter
            not (String.IsNullOrWhiteSpace actual)

        testPropertyWithConfig config "First row contains A" <| fun (letter : char) ->
            let actual = Diamond.make letter
            let rows = split actual
            rows |> Seq.head |> trim = "A"

        testPropertyWithConfig config "All rows must have symmetric contour" <| fun (letter : char) ->
            let actual = Diamond.make letter
            let rows = split actual
            rows |> Array.forall (fun r -> (leadingSpaces r) = (trailingSpaces r))

        testPropertyWithConfig config "Top of figure has correct letters in alphabetacal order" <| fun (letter : char) ->
            let actual = Diamond.make letter
            let rows = split actual
            let expected = ['A' .. letter]
            let firstNonWhiteSpaceLetters =
                rows 
                |> Seq.take expected.Length 
                |> Seq.map trim
                |> Seq.map Seq.head
                |> Seq.toList
            expected = firstNonWhiteSpaceLetters

        testPropertyWithConfig config "Figure is symmetric around the horizontal axis" <| fun (letter : char) ->
            let actual = Diamond.make letter
            let rows = split actual
            let topRows =
                rows
                |> Seq.takeWhile (fun x -> not (x.Contains(string letter)))
                |> Seq.toList
            let bottomRows =
                rows
                |> Seq.skipWhile (fun x -> not (x.Contains(string letter)))
                |> Seq.skip 1
                |> Seq.toList
                |> List.rev
            topRows = bottomRows

        testPropertyWithConfig config "Diamond is as wide as it's high" <| fun (letter : char) ->
            let actual = Diamond.make letter
            let rows = split actual

            let expected = rows.Length
            rows |> Array.forall (fun x -> x.Length = expected)

        testPropertyWithConfig config "All rows except top and bottom have two identical letters" <| fun (letter : char) ->
            let actual = Diamond.make letter

            let isTwoIdenticalLetters x =
                let hasIdenticalLetters = x |> Seq.distinct |> Seq.length = 1
                let hasTwoLetters = x |> Seq.length = 2
                hasIdenticalLetters && hasTwoLetters

            let rows = split actual
            rows
            |> Array.filter (fun x -> not (x.Contains("A")))
            |> Array.map (fun x -> x.Replace(" ", ""))
            |> Array.forall isTwoIdenticalLetters

        testPropertyWithConfig config "Lower left space is a triangle" <| fun (letter : char) ->
            let actual = Diamond.make letter

            let rows = split actual
            let lowerLeftSpace =
                rows
                |> Seq.skipWhile (fun x -> not (x.Contains(string letter)))
                |> Seq.map leadingSpaces
                |> Seq.toList

            let spaceCounts = lowerLeftSpace |> List.map (fun x -> x.Length)
            let expected =
                Seq.initInfinite id
                |> Seq.take spaceCounts.Length
                |> Seq.toList
            expected = spaceCounts
    ]

[<EntryPoint>]
let main argv =
    Tests.runTestsInAssembly defaultConfig argv