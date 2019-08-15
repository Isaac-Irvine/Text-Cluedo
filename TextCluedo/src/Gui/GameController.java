package Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The controller for a game
 * Handles various listeners and key presses
 */
public class GameController implements MouseListener {
	private GameView view;

	/**
	 * Init game controller
	 * @param view
	 */
	public GameController(GameView view) {
		this.view = view;
	}

	/**
	 * Click on canvas
	 * @param event
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getButton() != MouseEvent.BUTTON1) return;
		System.out.println("CLICK AT " + event.getX() + "," + event.getY());
	}

	/**
	 * Press on canvas
	 * @param event
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		if(event.getButton() != MouseEvent.BUTTON1) return;
		//System.out.println("PRESS AT " + event.getX() + "," + event.getY());
	}

	/**
	 * Release on canvas
	 * @param event
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.getButton() != MouseEvent.BUTTON1) return;
		//System.out.println("RELEASE AT " + event.getX() + "," + event.getY());
	}

	/**
	 * Mouse enter canvas
	 * @param event
	 */
	@Override
	public void mouseEntered(MouseEvent event) {}

	/**
	 * Mouse exit canvas
	 * @param event
	 */
	@Override
	public void mouseExited(MouseEvent event) {}
}
