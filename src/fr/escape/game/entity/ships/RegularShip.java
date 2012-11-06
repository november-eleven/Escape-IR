package fr.escape.game.entity.ships;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import fr.escape.app.Graphics;

//TODO comment
public class RegularShip extends AbstractShip {
	
	public RegularShip(Body body,int life) {
		super(body,life);
	}
	
	@Override
	public void setPosition(World world,Graphics graphics,float[] val) {
		if(!isDestroyed()) {
			getBody().setLinearVelocity(new Vec2(val[0],val[1]));
			draw(graphics);
			world.step(1.0f/60.0f,6,2);
		}
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateBy(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRotation(int angle) {
		// TODO Auto-generated method stub
		
	}

}
