package gomoku.ui;

import java.awt.Component;

import javax.swing.JOptionPane;

import gomoku.core.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class FinJeu {

	public FinJeu ()
	{
		
	}
	
	public int Show(Canvas playGround, Player winner)
	{
		Alert alert = new Alert(AlertType.NONE, "Félicitation : " + winner.getName() + " remporte la partie \n Quitter ?",
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return -1;
		}
		if (alert.getResult() == ButtonType.NO) {
		    return 1;
		}
		else
		{
			return 0;
		}
	}
}
