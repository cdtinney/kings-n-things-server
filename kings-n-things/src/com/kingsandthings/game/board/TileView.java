package com.kingsandthings.game.board;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import com.kingsandthings.model.board.Tile;

public class TileView extends ImageView {
	
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
		
//		setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				
//				ImageView image = (ImageView) event.getSource();
//				image.setImage(defaultImg);				
//				
//			};
//		
//		});
//		
//		setOnMouseExited(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				
//				ImageView image = (ImageView) event.getSource();
//				image.setImage(tile.getImage() == null? defaultImg : tile.getImage());
//				
//			};
//		
//		});
	
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
			actionMenu.show(this, Side.RIGHT, -25, 10);
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
