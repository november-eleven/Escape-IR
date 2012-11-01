/*****************************************************************************
 * 
 * Copyright 2012 See AUTHORS file.
 * 
 * This file is part of Escape-IR.
 * 
 * Escape-IR is free software: you can redistribute it and/or modify
 * it under the terms of the zlib license. See the COPYING file.
 * 
 *****************************************************************************/

package fr.escape.app;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import fr.escape.game.User;
import fr.escape.graphics.RenderListener;
import fr.escape.input.EventListener;
import fr.escape.input.Gesture;
import fr.escape.resources.Resources;
import fr.escape.ships.Ship;

/**
 * <p>
 * A class which delegates rendering to a {@link Screen}. 
 * 
 * <p>
 * Allowing multiple screens for a Game.
 */
public abstract class Game implements RenderListener, EventListener {
	
	private Screen screen;
	private ArrayList<Gesture> gestures;
	private final LinkedList<Input> events = new LinkedList<>();
	private Ship ship;
	
	private User user;
	
	public Game() {
		user = new User();
	}
	
	public abstract void create();
	
//	public void dispose() {
//		if(screen != null) {
//			screen.hide();
//		}
//	}
//
//	public void pause() {
//		if(screen != null) {
//			screen.pause();
//		}
//	}
//
//	public void resume() {
//		if(screen != null) {
//			screen.resume();
//		}
//	}

	public void render() {
		if(screen != null) {
			screen.render(getGraphics().getDeltaTime());
		}
	}
	
//	public void resize(int width, int height) {
//		if(screen != null) {
//			screen.resize(width, height);
//		}
//	}

	public void setScreen(Screen screen) {
		
		if(this.screen != null) {
			this.screen.hide();
		}
		
		this.screen = screen;
		
		screen.show();
		//screen.resize(Foundation.graphics.getWidth(), Foundation.graphics.getHeight());
	}

	/**
	 * Return the current active {@link Screen}.
	 *
	 * @return active {@link Screen}.
	 */
	public Screen getScreen() {
		return screen;
	}
	
	/**
	 * Return the {@link Activity} which created this Game.
	 * 
	 * @return {@link Activity}.
	 */
	public Activity getActivity() {
		return Foundation.activity;
	}
	
	/**
	 * Return the {@link Graphics} for the Game.
	 * 
	 * @return {@link Graphics}
	 */
	public Graphics getGraphics() {
		return Foundation.graphics;
	}

	/**
	 * Return the {@link Resources} for the Game.
	 * 
	 * @return {@link Resources}
	 */
	public Resources getResources() {
		return Foundation.resources;
	}
	
	/**
	 * 
	 */
	public void setGestures(ArrayList<Gesture> gestures) {
		Objects.requireNonNull(gestures);
		this.gestures = gestures;
	}
	
	/**
	 * 
	 */
	public ArrayList<Gesture> getGestures() {
		return gestures;
	}
	
	/**
	 * 
	 */
	public LinkedList<Input> getEvents() {
		return events;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	/**
	 * 
	 */
	public boolean touch(Input i) {
		System.out.println("Touch");
		return true;
	}
	
	/**
	 * 
	 */
	public boolean move(Input i) {
		/*Objects.requireNonNull(i);
		switch(i.getKind().name()) {
			case "ACTION_UP" :
				Iterator<Input> it = events.iterator();
				if(it.hasNext()) {
					Input start = it.next(); it.remove();
					for(Gesture g : gestures) {
						if(g.accept(start,events,i)) ship.setPosition(i.getX(),i.getY());
					}
					events.clear();
				}
				break;
			default :
				events.add(i);
		}*/
		return true;
	}
	
	/**
	 * Retrieve the {@link User} in this Game.
	 * 
	 * @return {@link User}
	 */
	public User getUser() {
		return user;
	}
}
