package enums

class BinOperator(char: String) extends Token(char)

object BinOperators {
  def convert(char: Char): Either[Char, BinOperator] = char match {
    case '+' => Right(Addition)
    case '-' => Right(Subtraction)
    case '*' => Right(Multiplication)
    case '/' => Right(Division)
    case '^' => Right(Power)
    case _   => Left(char)
  }
}

case object Addition extends BinOperator("+")
case object Subtraction extends BinOperator("-")
case object Multiplication extends BinOperator("*")
case object Division extends BinOperator("/")
case object Power extends BinOperator("^")
