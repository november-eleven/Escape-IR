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

package fr.escape.game;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import fr.escape.app.CollisionDetector;
import fr.escape.app.Game;
import fr.escape.app.Input;
import fr.escape.app.Overlay;
import fr.escape.game.entity.ships.Ship;
import fr.escape.game.entity.ships.ShipFactory;
import fr.escape.game.entity.weapons.BlackHole;
import fr.escape.game.entity.weapons.Missile;
import fr.escape.game.entity.weapons.Weapon;
import fr.escape.game.entity.weapons.Weapons;
import fr.escape.game.screen.Splash;
import fr.escape.game.screen.Error;
import fr.escape.game.ui.IngameUI;
import fr.escape.game.ui.UIHighscore;
import fr.escape.game.ui.UIWeapons;
import fr.escape.input.BackOff;
import fr.escape.input.Drift;
import fr.escape.input.Gesture;
import fr.escape.input.LeftLoop;
import fr.escape.input.RightLoop;

public class Escape extends Game {
	private Splash splash;
	private Error error;
	private IngameUI ingameUI;
	
	/**
	 * @see Game#create()
	 */
	@Override
	public void create() {
		try {
			float coeff = Math.max(getGraphics().getWidth(),getGraphics().getHeight());
			ShipFactory sf = new ShipFactory();
			Vec2 gravity = new Vec2(0.0f,0.0f);
			World world = new World(gravity,true);
			setWorld(world);
			ContactListener contactListener = new CollisionDetector();
			world.setContactListener(contactListener);
			// Create Screen
			splash = new Splash(this);
			// Other Screen if any ...
			
			// Create Game Components
			ingameUI = new IngameUI();

			ArrayList<Gesture> gestures = new ArrayList<>();
			
			UIHighscore uHighscore = new UIHighscore(this);
			Ship ship = sf.createRegularShip(world,"PlayerShip",(getGraphics().getWidth()/2 - 20) / coeff * 10,(getGraphics().getHeight() - 100) / coeff * 10,BodyType.DYNAMIC,0.5f,3);

			getUser().register(uHighscore);
			getUser().setShip(ship);
			
			gestures.add(new Drift());
			gestures.add(new BackOff());
			gestures.add(new LeftLoop());
			gestures.add(new RightLoop());
			getUser().setGestures(gestures);
			getUser().setShip(ship);
			List<Weapon> lWeapons = new ArrayList<>();
			
			Weapon wB = new BlackHole();
			Weapons.validate(wB);
			
			Weapon wM = new Missile();
			Weapons.validate(wM);
			
			lWeapons.add(wM);
			lWeapons.add(wB);
			lWeapons.add(wB);
			lWeapons.add(wB);
			
			UIWeapons uWeapons = new UIWeapons(this, getUser(), lWeapons);
			
			ingameUI.add(uHighscore);
			ingameUI.add(uWeapons);
			
			setScreen(splash);
		} catch(Exception e) {
			error = new Error(this);
			getActivity().error("Escape", "Exception raised during create()", e);
			setScreen(error);
		}
		
	}
	
	/**
	 * @see Game#render()
	 */
	@Override
	public void render() {
		super.render();
		if(ingameUI != null) {
			ingameUI.render(getGraphics().getDeltaTime());
		}
	}
	
	/**
	 * @see Game#touch(Input)
	 */
	@Override
	public boolean touch(Input i) {
		if(getOverlay().touch(i)) {
			return true;
		}
		return getScreen().touch(i);
	}
	
	/**
	 * @see Game#move(Input)
	 */
	@Override
	public boolean move(Input i) {
		if(getOverlay().move(i)) {
			return true;
		}
		return getScreen().move(i);
	}

	/**
	 * <p>
	 * Return the Overlay used as Ingame UI.
	 * 
	 * @return {@link Overlay}
	 */
	public Overlay getOverlay() {
		return ingameUI;
	}
	
}
