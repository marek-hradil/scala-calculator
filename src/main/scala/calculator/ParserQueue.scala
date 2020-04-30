package calculator

import enums.Token

class ComputationParserQueue(tokens: List[Token]) {
  private var index = 0

  def isEmpty = index == tokens.length

  def pop: Token = {
    val token = tokens(index)
    index += 1

    token
  }
}
