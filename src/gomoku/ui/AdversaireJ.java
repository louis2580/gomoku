package gomoku.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import gomoku.core.Player;
import gomoku.core.model.Grid;
import gomoku.core.model.Spot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdversaireJ extends Application {

	private Player player1_ = new Player("Mr. Blue", Color.BLUE),
					player2_ = new Player("Mr. Red", Color.RED);
	
	private boolean player1Turn_ = true, gameIsOver = false;
	private Grid gameGrid_ = new Grid();
	private PrintWriter out_;
	Canvas playGround;
	static String[] args_;
	
	public AdversaireJ()
	{
		try {
			out_ = new PrintWriter("result.txt");
			out_.print("");
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gomoku Game!");

		playGround = new Borad(this.gameGrid_, this::makeMove); // In Java 8, ceci is the equivalent of
																			// passing a ref to a methode (lambda exp et
																			// InnerClasse)
		BorderPane root = new BorderPane();
		root.setCenter(playGround);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void makeMove(Spot place) {
		if (!gameIsOver)
		{
			gameGrid_.placeStone(place.x, place.y, getCurrentPlayer());
			saveMove(place);
			player1Turn_ = !player1Turn_;
		}
	}

	private Player getCurrentPlayer() {
		if (player1Turn_)
		{
			return player1_;
		} else
		{
			return player2_;
		}
	}
	
	// Method to save the result in a file "result.txt" at the end of the game
	private void saveMove(Spot place)
	{
		String line = getCurrentPlayer().getName() + " : " + place.x + "; " + place.y;
		out_.println(line);
		System.out.println("ici");
		if (gameGrid_.isGameOver())
		{
			int endInt;
			out_.println("**********************************");
			if(gameGrid_.isWonBy(player1_))
			{
				out_.println(player1_.getName() + " wins this round !");
				endInt = new FinJeu().Show(playGround, player1_);
			} else
			{
				out_.println(player2_.getName() + " wins this round !");
				endInt = new FinJeu().Show(playGround, player2_);
			}
			out_.println("**********************************");
			out_.close();
			endOption(endInt);
		}
	}
	
	private void endOption(int i)
	{
		gameIsOver = true;
		switch (i)
		{
		case 1: // Rester
			break;
		case -1: // Quitter
			try {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "result.txt");
				pb.start();
			} catch (IOException e) {
			}
			System.exit(0);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		args_ = args;
		launch(args);
	}
	
}
