package com.kingsandthings.game.board;

import java.util.logging.Logger;

import javafx.geometry.Side;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	private ImageView controlMarkerImageView;
	private ImageView fortImageView;
	
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
		
		if (visible || !actionMenu.visibleItems()) {
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
	
	public void addHighlight(boolean valid) {
	
		InnerShadow innerShadow = InnerShadowBuilder.create().radius(4).choke(5).build();
		
		if (valid) {
			innerShadow.setColor(Color.LIMEGREEN);
		} else {
			innerShadow.setColor(Color.RED);
		}
		
		setEffect(innerShadow);
		
	}
	
	public void removeHighlight() {
		this.setEffect(null);
	}
	
	public void setTile(Tile tile) {
		
		this.tile = tile;
		
		if (tile != null && tile.getImage() != null) {
			setImage(tile.getImage());
		}
		
	}

	public Tile getTile() {
		return tile;
	}
	
	public void updateControlMarkerView() {
		
		if (tile.getOwner() == null) {
			removeControlMarkerView();
		} else {
			addControlMarkerView();
		}
		
	}
	
	public void updateFortView() {
		
		if (tile.getFort() == null) {
			
			if (fortImageView != null) {
				Pane parent = (Pane) fortImageView.getParent();
				parent.getChildren().remove(fortImageView);
				fortImageView = null;
			}
			
			return;
			
		}
		
		if (fortImageView == null) {
			fortImageView = new ImageView();
			fortImageView.setPreserveRatio(true);
			fortImageView.setFitWidth(30);
			fortImageView.setX(getX() + 18);
			fortImageView.setY(getY() + 3);
			
			((Pane) getParent()).getChildren().add(fortImageView);	
		}
			
		fortImageView.setImage(tile.getFort().getImage());
		
	}
	
	private void addControlMarkerView() {
		
		ImageView imgView = new ImageView(tile.getOwner().getControlMarker());
		imgView.setPreserveRatio(true);
		imgView.setFitWidth(30);
		imgView.setX(getX() + 47);
		imgView.setY(getY() + 3);
		
		controlMarkerImageView = imgView;
		
		((Pane) getParent()).getChildren().add(imgView);	
		
	}
	
	private void removeControlMarkerView() {
		
		if (controlMarkerImageView == null) {
			return;
		}
		
		Pane parent = (Pane) controlMarkerImageView.getParent();
		parent.getChildren().remove(controlMarkerImageView);
		
		controlMarkerImageView = null;
	}
	
}
