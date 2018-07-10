import javafx.application.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;


public class W4M2 extends Application {
	private final static int numberOfCircles = 5;
	
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		
		for(int i = 0; i < numberOfCircles; i++) {
			Circle circle = new Circle(i*100, i*100, 50);
			circle.setFill(Color.BLACK);
			circle.setOnMouseEntered(this::handleEnterCircle);
			circle.setOnMouseExited(this::handleExitCircle);
			pane.getChildren().add(circle);
		}
		
		Scene scene = new Scene(pane, 500, 500, Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circles!");
		primaryStage.show();
	}
	
	private void handleEnterCircle(MouseEvent event) {
		System.out.println("In Circle");
	}
	
	private void handleExitCircle(MouseEvent event) {
		System.out.println("Not In CIRCLE");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}