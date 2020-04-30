package enums

class Token(val char: String)

case object LeftBracket extends Token("(")
case object RightBracket extends Token(")")
case object Dot extends Token(".")
case object Comma extends Token(",")

object Tokens {
  def convert(char: Char): Either[Char, Token] = {
    char match {
      case '(' => Right(LeftBracket)
      case ')' => Right(RightBracket)
      case '.' => Right(Dot)
      case ',' => Right(Comma)
      case _   => Left(char)
    }
  }
}
