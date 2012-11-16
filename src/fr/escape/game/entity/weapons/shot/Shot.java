package fr.escape.game.entity.weapons.shot;

import fr.escape.game.entity.Entity;
import fr.escape.game.message.Receiver;

//TODO Comment
public interface Shot extends Receiver, Entity {
	
	/**
	 * @see Shot#receive(int)
	 */
	public final static int MESSAGE_LOAD = 0;
	public final static int MESSAGE_FIRE = 1;
	public final static int MESSAGE_CRUISE = 2;
	public final static int MESSAGE_HIT = 3;
	public final static int MESSAGE_DESTROY = 4;
	
	/**
	 * <p>
	 * Shot have different state depending on the situation.
	 * 
	 * <p>
	 * If you need to change its state, use this method with the given protocol:
	 * 
	 * <ul>
	 * <li>MESSAGE_LOADED: Shot loaded in Ship.</li>
	 * <li>MESSAGE_FIRE: Shot just shoot from Ship.</li>
	 * <li>MESSAGE_CRUISE: Shot in cruise state.</li>
	 * <li>MESSAGE_HIT: Shot hit something.</li>
	 * <li>MESSAGE_DESTROY: Shot need to be destroyed.</li>
	 * </ul>
	 * 
	 * <b>By default:</b> MESSAGE_LOADED.
	 * 
	 * @see Receiver#receive(int)
	 */
	@Override
	public void receive(int message);
		
	public int getState();
	
	public int getDamage();
	
	public boolean setShotConfiguration(ShotContext configuration);
	
	public void setPosition(float x, float y);
	
	public static final class ShotContext {
		
		private final boolean player;
		private final int width;
		private final int height;
		
		public ShotContext(boolean player, int width, int height) {
			this.player = player;
			this.width = width;
			this.height = height;
		}
		
		public boolean isPlayer() {
			return player;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
		
	}

	public void setUntouchable();
}
