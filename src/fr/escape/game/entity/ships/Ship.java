package fr.escape.game.entity.ships;

import java.awt.Rectangle;
import java.util.List;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import fr.escape.game.entity.Drawable;
import fr.escape.game.entity.Entity;
import fr.escape.game.entity.Moveable;
import fr.escape.game.entity.weapons.Weapon;
import fr.escape.game.message.Receiver;

// TODO Comment
public interface Ship extends Moveable, Drawable, Entity, Receiver {
	
	public static final int MESSAGE_EXECUTE_LEFT_LOOP = 0;
	public static final int MESSAGE_EXECUTE_RIGHT_LOOP = MESSAGE_EXECUTE_LEFT_LOOP + 1;
		
	public List<Weapon> getAllWeapons();
	
	public void setActiveWeapon(int which);
	
	public Weapon getActiveWeapon();
	
	public BodyDef getBodyDef();
	
	public Body getBody();
	
	public float getX();
	
	public float getY();
	
	public boolean isPlayer();
	
	public boolean isWeaponLoaded();
	
	public void createBody(World world);
	
	public boolean loadWeapon();
	
	public boolean reloadWeapon(int which, int number);
	
	public boolean fireWeapon();
	
	public boolean fireWeapon(float[] velocity);
	
	/**
	 * <p>
	 * Send an action to the Ship.
	 * 
	 * <p>
	 * If you need to execute an action, use this method with the given protocol:
	 * 
	 * <ul>
	 * <li>MESSAGE_EXECUTE_LEFT_LOOP: Execute a Left Loop to the Ship.</li>
	 * <li>MESSAGE_EXECUTE_RIGHT_LOOP: Execute a Right Loop to the Ship.</li>
	 * </ul>
	 * 
	 * @see Receiver#receive(int)
	 */
	@Override
	public void receive(int message);

	public Rectangle getEdge();
	
	public boolean reset(World world);
	
	public void damage(int value);
}
