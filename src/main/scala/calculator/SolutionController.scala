package calculator

object SolutionController {
  def proccess(input: String): Either[String, Float] = {
    try {
      val inputTrimmed = input.replaceAll("\\s", "")
      val tokenizedInput = ComputationTokenizer.tokenize(inputTrimmed)
      val tokenQueue = new ComputationParserQueue(tokenizedInput)
      val parser = new ComputationParser(tokenQueue)
      val tree = parser.parse(ComputationNode(Right(None), None, Right(None)))
      val result = ComputationNodeResolver.resolve(tree)

      Right(result)
    } catch {
      case e: Throwable => Left(e.getMessage)
    }
  }
}
