package com.kingsandthings.util;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import com.kingsandthings.game.events.EventPropogationHandler;

public class DataImageView extends ImageView {
	
	private Pane owner;
	private Object data;
	
	private Pane wrapper;
	
	public DataImageView(Pane owner) {
		this.owner = owner;
	}
	
	public void setData(Object object) {
		this.data = object;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public void addBorder() {
		
		if (wrapper != null) {
			wrapper.setVisible(true);
			return;
		}
		
		wrapper = new Pane();
		wrapper.getStyleClass().add("imageBorder");
		wrapper.managedProperty().bind(wrapper.visibleProperty());

		double imageHeight = this.getBoundsInParent().getHeight();
		double imageWidth = this.getBoundsInParent().getWidth();
		
		wrapper.setPrefHeight(imageHeight);
		wrapper.setPrefWidth(imageWidth);
		
		double x = this.getLayoutX();
		double y = this.getLayoutY();
		
		wrapper.setLayoutX(x);
		wrapper.setLayoutY(y);
		
		owner.getChildren().add(wrapper);
		
		wrapper.addEventHandler(MouseEvent.ANY, new EventPropogationHandler(this));
		
	}
	
	public void removeBorder() {
		wrapper.setVisible(false);
	}

}
