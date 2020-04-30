package calculator

import scala.language.postfixOps
import enums.{Token, Tokens, Numbers, BinOperators}

object ComputationTokenizer {
  def tokenize(input: String): List[Token] = input map tokenizeChar toList

  private def tokenizeChar(char: Char): Token = {
    val binOperator = BinOperators.convert(char)
    if (binOperator.isRight) {
      val Right(value) = binOperator
      return value
    }

    val number = Numbers.convert(char)
    if (number.isRight) {
      val Right(value) = number
      return value
    }

    val token = Tokens.convert(char)
    if (token.isRight) {
      val Right(value) = token
      return value
    }

    throw new Exception(s"Character $char is not valid input")
  }
}
