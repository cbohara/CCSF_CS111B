import java.util.ArrayList;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Drawing extends Application {
	private Pane pane;
	private BorderPane borderPane;
	Scene scene;
	private Text statusText;
	private Circle circle;
	private Color circleColor;
	private boolean drawingCircle;
	private RadioButton red, blue, yellow;
	private Button button;
	
	public void start(Stage primaryStage) {
		drawingCircle = false;
		circleColor = Color.RED;
		
		borderPane = new BorderPane();
		pane = new Pane();
		
		ToggleGroup group = new ToggleGroup();
		red = new RadioButton("Red");
		red.setToggleGroup(group);
		red.setSelected(true);
		red.setOnAction(this::handleColorOptions);
		blue = new RadioButton("Blue");
		blue.setToggleGroup(group);
		blue.setOnAction(this::handleColorOptions);
		yellow = new RadioButton("Yellow");
		yellow.setToggleGroup(group);
		yellow.setOnAction(this::handleColorOptions);
		
		button = new Button("Clear");
		button.setOnAction(this::handleButton);
		HBox optionsBox = new HBox(red, blue, yellow, button);
		optionsBox.setAlignment(Pos.CENTER);
		optionsBox.setSpacing(10);
		pane.getChildren().add(optionsBox);
		
		pane.setOnMouseClicked(this::handleMouseClicks);
		pane.setOnMouseMoved(this::handleMouseMotion);
		borderPane.setCenter(pane);
		
		statusText = new Text("Pen Off");
		statusText.setFont(Font.font(18));
		statusText.setFill(Color.BLACK);
		HBox textBox = new HBox(statusText);
		textBox.setAlignment(Pos.CENTER);
		borderPane.setBottom(textBox);
		
		scene = new Scene(borderPane, 500, 500, Color.ALICEBLUE);
		primaryStage.setTitle("Drawing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void handleMouseClicks(MouseEvent event) {
		if(!drawingCircle) {
			drawingCircle = true;
			statusText.setText("Pen On");
		} else {
			statusText.setText("Pen Off");
			drawingCircle = false;
		}
	}
	
	private void handleMouseMotion(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();
		
		if (drawingCircle) {
			circle = new Circle(x, y, 5, circleColor);
			pane.getChildren().add(circle);
		}
	}

	private void handleColorOptions(ActionEvent event) {
		if(red.isSelected()) {
			setCircleColor(Color.RED);
		} else if(blue.isSelected()) {
			setCircleColor(Color.BLUE);
		} else if(yellow.isSelected()) {
			setCircleColor(Color.YELLOW);
		}
	}
	
	private void setCircleColor(Color circleColor) {
		this.circleColor = circleColor;
	}
	
	private void handleButton(ActionEvent event) {
		pane.getChildren().removeIf(n -> n instanceof Circle);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}