package calculator

import scala.math.pow

import enums.{Addition, Subtraction, Multiplication, Division, Power}

object ComputationNodeResolver {
  def resolve(node: ComputationNode): Float = node.operator match {
    case Some(Addition) =>
      resolveChildren(resolveAddition, node)
    case Some(Subtraction) =>
      resolveChildren(resolveSubtraction, node)
    case Some(Multiplication) =>
      resolveChildren(resolveMultiplication, node)
    case Some(Division) =>
      resolveChildren(resolveDivision, node)
    case Some(Power) =>
      resolveChildren(resolvePower, node)

    case _ => throw new Exception("Couldnt resolve node without operator")
  }

  private def resolveChildren(
      resolveFunction: ComputationNodeResolved => Float,
      node: ComputationNode
  ) = {
    val leftValue = node.left match {
      case Left(n)        => resolve(n)
      case Right(Some(n)) => n.toFloat
      case Right(None) =>
        throw new Exception("Left node in binary operation is none")
    }
    val rightValue = node.right match {
      case Left(n)        => resolve(n)
      case Right(Some(n)) => n.toFloat
      case Right(None) =>
        throw new Exception("Right node in binary operation is none")
    }

    val operator = node.operator match {
      case Some(o) => o
      case None    => throw new Exception("Operator in constructed tree is none")
    }

    val resolvedNode = ComputationNodeResolved(
      leftValue,
      operator,
      rightValue
    )

    resolveFunction(resolvedNode)
  }

  private def resolveAddition(node: ComputationNodeResolved) =
    node.left + node.right
  private def resolveSubtraction(node: ComputationNodeResolved) =
    node.left - node.right
  private def resolveMultiplication(node: ComputationNodeResolved) =
    node.left * node.right
  private def resolveDivision(node: ComputationNodeResolved) =
    node.left / node.right
  private def resolvePower(node: ComputationNodeResolved) =
    pow(node.left, node.right).toFloat
}
