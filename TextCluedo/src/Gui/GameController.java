package Gui;

import Game.Cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.event.KeyEvent.*;

/**
 * The controller for a game
 * Handles various listeners and key presses
 */
public class GameController implements MouseListener, KeyListener {
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

	/**
	 * Key typed on screen
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Key pressed on screen
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case VK_W: case VK_UP:
				view.getPlayer().move(Cell.Direction.UP);
				break;
			case VK_A: case VK_LEFT:
				view.getPlayer().move(Cell.Direction.LEFT);
				break;
			case VK_S: case VK_DOWN:
				view.getPlayer().move(Cell.Direction.DOWN);
				break;
			case VK_D: case VK_RIGHT:
				view.getPlayer().move(Cell.Direction.RIGHT);
				break;
		}
	}

	/**
	 * Key released on screen
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {}
}
