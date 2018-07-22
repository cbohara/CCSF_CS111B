import javafx.application.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Drawing extends Application {
	private Pane pane;
	private Circle circle;
	private boolean drawingCircle;
	
	public void start(Stage primaryStage) {
		drawingCircle = false;
		
		pane = new Pane();
		pane.setOnMouseClicked(this::handleMouseClicks);
		pane.setOnMouseMoved(this::handleMouseMotion);

		Scene scene = new Scene(pane, 500, 500, Color.ALICEBLUE);
		primaryStage.setTitle("Drawing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleMouseClicks(MouseEvent event) {
		if(!drawingCircle) {
			drawingCircle = true;
			double x = event.getX();
			double y = event.getY();
			circle.setCenterX(x);
			circle.setCenterY(y);
		} else {
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