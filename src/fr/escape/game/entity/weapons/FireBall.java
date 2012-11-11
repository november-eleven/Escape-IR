package fr.escape.game.entity.weapons;

import org.jbox2d.dynamics.World;

import fr.escape.app.Foundation;
import fr.escape.app.Graphics;
import fr.escape.game.entity.CoordinateConverter;
import fr.escape.game.entity.EntityContainer;
import fr.escape.game.entity.weapons.shot.Shot;
import fr.escape.game.entity.weapons.shot.ShotFactory;
import fr.escape.graphics.Texture;
import fr.escape.resources.texture.TextureLoader;

public final class FireBall implements Weapon {

	private final Texture drawable;
	private int ammunition;
	
	private Shot shot;
	
	public FireBall(int defaultAmmunition) {
		drawable = Foundation.RESOURCES.getTexture(TextureLoader.WEAPON_FIREBALL);
		ammunition = defaultAmmunition;
	}
	
	@Override
	public Texture getDrawable() {
		return drawable;
	}

	@Override
	public int getAmmunition() {
		return ammunition;
	}
	
	@Override
	public boolean isEmpty() {
		return getAmmunition() <= 0;
	}

	@Override
	public Shot getShot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean load(World world, EntityContainer ec, float x, float y) {
		if(!isEmpty() && shot == null) {
			
			shot = ShotFactory.createBlackholeShot(world, x, y, CoordinateConverter.toMeterX(drawable.getHeight()), ec);
			shot.receive(Shot.MESSAGE_LOAD);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean unload() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean fire(float[] velocity) {
		// TODO Auto-generated method stub
		return false;
	}

}
