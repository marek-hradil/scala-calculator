package calculator

import enums.{BinOperator}

final case class ComputationNode(
    left: Either[ComputationNode, Option[String]],
    operator: Option[BinOperator],
    right: Either[ComputationNode, Option[String]]
)

final case class ComputationNodeResolved(
    left: Float,
    operator: BinOperator,
    right: Float
)
