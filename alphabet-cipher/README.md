# alphabet-cipher

https://github.com/gigasquid/wonderland-clojure-katas/tree/master/alphabet-cipher

This Alphabet Cipher involves alphabet substitution using a keyword.

First you must make a substitution chart like this, where each row of the alphabet is rotated by one as each letter goes down the chart.

```
   ABCDEFGHIJKLMNOPQRSTUVWXYZ
 A abcdefghijklmnopqrstuvwxyz
 B bcdefghijklmnopqrstuvwxyza
 C cdefghijklmnopqrstuvwxyzab
 D defghijklmnopqrstuvwxyzabc
 E efghijklmnopqrstuvwxyzabcd
 F fghijklmnopqrstuvwxyzabcde
 G ghijklmnopqrstuvwxyzabcdef
 H hijklmnopqrstuvwxyzabcdefg
 I ijklmnopqrstuvwxyzabcdefgh
 J jklmnopqrstuvwxyzabcdefghi
 K klmnopqrstuvwxyzabcdefghij
 L lmnopqrstuvwxyzabcdefghijk
 M mnopqrstuvwxyzabcdefghijkl
 N nopqrstuvwxyzabcdefghijklm
 O opqrstuvwxyzabcdefghijklmn
 P pqrstuvwxyzabcdefghijklmno
 Q qrstuvwxyzabcdefghijklmnop
 R rstuvwxyzabcdefghijklmnopq
 S stuvwxyzabcdefghijklmnopqr
 T tuvwxyzabcdefghijklmnopqrs
 U uvwxyzabcdefghijklmnopqrst
 V vwxyzabcdefghijklmnopqrstu
 W wxyzabcdefghijklmnopqrstuv
 X xyzabcdefghijklmnopqrstuvw
 Y yzabcdefghijklmnopqrstuvwx
 Z zabcdefghijklmnopqrstuvwxy
```

Both parties need to decide on a secret keyword. This keyword is not written down anywhere, but memorized.

To encode the message, first write down the message.

`meetmebythetree`
Then, write the keyword, (which in this case is scones), repeated as many times as necessary.

`sconessconessco`
`meetmebythetree`

Now you can look up the column S in the table and follow it down until it meets the M row. The value at the intersection is the letter e. All the letters would be thus encoded.

`sconessconessco`
`meetmebythetree`
`egsgqwtahuiljgs`
The encoded message is now egsgqwtahuiljgs

To decode, the person would use the secret keyword and do the opposite.
