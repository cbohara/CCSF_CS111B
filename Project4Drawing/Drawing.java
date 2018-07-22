import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
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
	private Text statusText;
	private Circle circle;
	private boolean drawingCircle;
	
	public void start(Stage primaryStage) {
		drawingCircle = false;
		
		borderPane = new BorderPane();
		pane = new Pane();
		
		pane.setOnMouseClicked(this::handleMouseClicks);
		pane.setOnMouseMoved(this::handleMouseMotion);
		borderPane.setCenter(pane);
		
		statusText = new Text("Pen Off");
		statusText.setFont(Font.font(18));
		statusText.setFill(Color.BLACK);
		HBox textBox = new HBox(statusText);
		textBox.setAlignment(Pos.CENTER);
		borderPane.setBottom(textBox);

		Scene scene = new Scene(borderPane, 500, 500, Color.ALICEBLUE);
		primaryStage.setTitle("Drawing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void handleMouseClicks(MouseEvent event) {
		if(!drawingCircle) {
			drawingCircle = true;
			statusText.setText("Pen On");
			double x = event.getX();
			double y = event.getY();
			circle.setCenterX(x);
			circle.setCenterY(y);
		} else {
			statusText.setText("Pen Off");
			drawingCircle = false;
		}
	}
	
	private void handleMouseMotion(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();
		
		if (drawingCircle) {
			circle = new Circle(x, y, 5);
			pane.getChildren().add(circle);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}