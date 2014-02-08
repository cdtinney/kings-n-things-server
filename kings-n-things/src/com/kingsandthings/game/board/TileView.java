package com.kingsandthings.game.board;

import java.util.logging.Logger;

import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import com.kingsandthings.model.board.Tile;

public class TileView extends ImageView {
	
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger.getLogger(TileView.class.getName());
	
	private static final int TILE_WIDTH = 100;
	private static Image defaultImg = new Image("/images/tiles/back.png");
	
	// Model
	private Tile tile;		
	
	// View
	private TileActionMenu actionMenu;
	private ImageView controlMarkerView;
	
	/*
	 * Constructor
	 */
	public TileView (String id, int x, int y) {
		
		if (id != null) {
			setId(id);
		}
		
		setImage(defaultImg);

		setPreserveRatio(true);
		setCache(true);
		
		setFitWidth(TILE_WIDTH);
		setX(x);
		setY(y);
		
		actionMenu = new TileActionMenu(this);
	
	}
	
	/*
	 * Returns an instance of an action menu specific to this tile instance.
	 */
	public TileActionMenu getActionMenu() {
		return actionMenu;
	}
	
	/*
	 * Toggles visibility of the action menu.
	 */
	public void toggleActionMenu() {

		boolean visible = actionMenu.showingProperty().get();
		
		if (visible) {
			actionMenu.hide();
		} else {
			
			// Menu must be shown to determine width
			actionMenu.show(this, Side.RIGHT, -25, 10);
			double menuWidth = actionMenu.getWidth();
			actionMenu.hide();

			double parentWidth = ((Pane) this.getParent()).getWidth();
			double tileX = getX();
			double tileWidth = getFitWidth();
			
			if ((tileX + tileWidth/2 + menuWidth) < parentWidth) {
				actionMenu.show(this, Side.RIGHT, -25, 10);
			} else {
				actionMenu.show(this, Side.LEFT, 25, 10);
			}
			
		}
		
	}
	
	/*
	 * Associates a tile model with the tile view.
	 */
	public void setTile(Tile tile) {
		
		this.tile = tile;
		
		if (tile != null && tile.getImage() != null) {
			setImage(tile.getImage());
		}
		
	}

	/*
	 * Returns the tile model associated with the tile view.
	 */
	public Tile getTile() {
		return tile;
	}
	
	/*
	 * Sets an image view representing the control marker.
	 */
	public void setControlMarkerView(ImageView controlMarkerView) {
		this.controlMarkerView = controlMarkerView;
	}
	
	/*
	 * Returns the image view representing the control marker.
	 */
	public ImageView getControlMarkerView() {
		return controlMarkerView;
	}
	
}
