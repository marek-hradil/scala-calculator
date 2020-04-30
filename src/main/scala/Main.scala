package hello

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.layout.HBox
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.text.{Text, Font}

import enums._
import calculator.SolutionController
import scalafx.scene.layout.BorderPane

object CalculatorUIController {
  val inputField = new TextField()

  val buttonField = new Button {
    text = "="
    onAction = handle {
      SolutionController.proccess(inputField.getText) match {
        case Right(result) => {
          textField.setFont(Font("Arial", 20))
          textField.setFill(Color.Black)
          textField.setText(result.toString)
        }
        case Left(error) => {
          textField.setFont(Font("Arial", 10))
          textField.setFill(Color.Red)
          textField.setText(s"Error: $error")
        }
      }
    }
  }

  val textField = new Text {
    text = ""
    font = Font.font("Arial", 20)
    margin = Insets(3, 0, 0, 0)
  }

  def readInputField: String = inputField.getText()
}

object CalculatorApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Calculator"
    sizeToScene
    scene = new Scene {
      fill = Color.White
      content = new BorderPane {
        center = new HBox {
          padding = Insets(20)
          spacing = 10
          children = Seq(
            CalculatorUIController.inputField,
            CalculatorUIController.buttonField,
            CalculatorUIController.textField
          )
        }
      }
    }
  }
}
