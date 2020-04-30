package enums

class Number(char: String) extends Token(char)

object Numbers {
  def convert(char: Char): Either[Char, Number] = char match {
    case '1' => Right(One)
    case '2' => Right(Two)
    case '3' => Right(Three)
    case '4' => Right(Four)
    case '5' => Right(Five)
    case '6' => Right(Six)
    case '7' => Right(Seven)
    case '8' => Right(Eight)
    case '9' => Right(Nine)
    case '0' => Right(Zero)

    case _ => Left(char)
  }
}

case object One extends Number("1")
case object Two extends Number("2")
case object Three extends Number("3")
case object Four extends Number("4")
case object Five extends Number("5")
case object Six extends Number("6")
case object Seven extends Number("7")
case object Eight extends Number("8")
case object Nine extends Number("9")
case object Zero extends Number("0")
