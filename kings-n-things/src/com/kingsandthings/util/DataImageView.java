package com.kingsandthings.util;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DataImageView extends ImageView {
	
	private static ImageViewHoverHandler hoverHandler = new ImageViewHoverHandler();
	private static final float hoverRatio = 2.8f;
	
	private Object data;
	
	private DataImageView hoverImageView;
	
	public DataImageView() {
		setPreserveRatio(true);
		setCache(true);
		
		this.setOnMouseEntered(hoverHandler);
		this.setOnMouseExited(hoverHandler);
	}
	
	public DataImageView(double width) {
		this();
		
		setFitWidth(width);
	}
	
	public static void clear(List<DataImageView> imageViews) {
		
		for (DataImageView imageView : imageViews) {
			imageView.setData(null);
			imageView.setImage(null);
		}
		
	}
	
	public void setVisiblity(boolean visible, boolean border) {
		
		setVisible(visible);
		
		if (visible && border) {
			showBorder();
		} else if (border) {
			hideBorder();
		}
		
	}
	
	public void setData(Object object) {
		this.data = object;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public void showHoverImage() {

		Pane parent = (Pane) this.getParent();

		double imageWidth = this.getBoundsInParent().getWidth();
		
		if (hoverImageView == null) {
			hoverImageView = new DataImageView();
			hoverImageView.setOpacity(0.95);
			hoverImageView.setPreserveRatio(true);
			hoverImageView.setFitWidth(imageWidth * hoverRatio);
			hoverImageView.managedProperty().bind(hoverImageView.visibleProperty());
			
			parent.getChildren().add(hoverImageView);
		}
		
		hoverImageView.setImage(getImage());

		double x = getLayoutX() + imageWidth + 7;
		
		double hoverImageHeight = hoverImageView.getBoundsInParent().getHeight();
		double viewHeight = parent.getHeight();
		
		double y = getLayoutY();
		if (y + hoverImageHeight > viewHeight) {
			y -= (hoverImageHeight / 2);
		}
		
		hoverImageView.relocate(x, y);
		hoverImageView.setVisiblity(true, true);
		
	}
	
	public void hideHoverImage() {
		
		if (hoverImageView != null) {
			hoverImageView.setVisible(false);
		}
		
	}
	
	public void showBorder() {
		setEffect(DropShadowBuilder.create().color(Color.TURQUOISE).spread(1).radius(3).build());
	}
	
	public void hideBorder() {
		setEffect(null);
	}
	
	private static class ImageViewHoverHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			
			DataImageView imgView = (DataImageView) event.getSource();
			
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				imgView.showHoverImage();
				imgView.showBorder();
			}
			
			if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				imgView.hideHoverImage();
				imgView.hideBorder();
			}
			
		}
		
	}

}
