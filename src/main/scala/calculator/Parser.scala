package calculator

import enums.{Token, RightBracket, LeftBracket, Number, BinOperator, Dot, Comma}

class ComputationParser(tokens: ComputationParserQueue) {
  def parse(
      currentNode: ComputationNode
  ): ComputationNode = {
    var node = currentNode

    while (!tokens.isEmpty) {
      tokens.pop match {
        case LeftBracket    => node = assignNextNodeToNode(node)
        case RightBracket   => return node
        case Dot | Comma    => node = assignNextCharToNode(".", node)
        case n: Number      => node = assignNextNumberToNode(n.char, node)
        case o: BinOperator => node = node.copy(operator = Some(o))
        case c              => throw new Exception(s"Couldnt recognize character: $c")
      }
    }

    node
  }

  private def assignNextCharToNode(
      char: String,
      node: ComputationNode
  ): ComputationNode = {
    node match {
      case ComputationNode(_, _, Right(Some(r))) =>
        node.copy(right = Right(Some(r + char)))

      case ComputationNode(Right(Some(l)), _, _) =>
        node.copy(left = Right(Some(l + char)))

      case _ => throw new Exception("Cannot assign char")
    }
  }

  private def assignNextNumberToNode(
      number: String,
      node: ComputationNode
  ): ComputationNode = {
    node match {
      case ComputationNode(_, _, Right(Some(r))) =>
        node.copy(right = Right(Some(r + number)))

      case ComputationNode(_, Some(_), Right(None)) =>
        node.copy(right = Right(Some(number)))

      case ComputationNode(Right(Some(l)), _, _) =>
        node.copy(left = Right(Some(l + number)))

      case ComputationNode(Right(None), _, _) =>
        node.copy(left = Right(Some(number)))

      case _ => throw new Exception("Cannot assign number")
    }
  }

  private def assignNextNodeToNode(
      node: ComputationNode
  ): ComputationNode = {
    val newNode = ComputationNode(Right(None), None, Right(None))
    node match {
      case ComputationNode(_, Some(_), Right(None)) =>
        node.copy(right = Left(parse(newNode)))

      case ComputationNode(Right(None), _, _) =>
        node.copy(left = Left(parse(newNode)))

      case _ => throw new Exception("Cannot assign node")
    }
  }
}
