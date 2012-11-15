package fr.escape.game.entity.weapons.shot;

import java.awt.Color;
import java.awt.Rectangle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import fr.escape.app.Foundation;
import fr.escape.app.Graphics;
import fr.escape.game.entity.CollisionBehavior;
import fr.escape.game.entity.CoordinateConverter;
import fr.escape.game.entity.EntityContainer;
import fr.escape.graphics.Texture;
import fr.escape.resources.texture.TextureLoader;

//TODO Comment
public class EarthShot extends AbstractShot {
	private final Texture coreEarthShot;
	
	private boolean isVisible;

	public EarthShot(Body body, EntityContainer container, CollisionBehavior collisionBehavior) {
		super(body, container, container, collisionBehavior, 5);
		
		this.coreEarthShot = Foundation.RESOURCES.getTexture(TextureLoader.EARTH_SPECIAL);
	}

	@Override
	public void receive(int message) {
		switch(message) {
			case Shot.MESSAGE_LOAD: {
				isVisible = true;
				break;
			}
			case Shot.MESSAGE_FIRE: {
				break;
			}
			case Shot.MESSAGE_CRUISE: {
				isVisible = true;
				break;
			}
			case Shot.MESSAGE_HIT: {
				getBody().setType(BodyType.STATIC);
				getBody().setLinearVelocity(new Vec2(0.0f, 0.0f));
			}
			case Shot.MESSAGE_DESTROY: {
				
				isVisible = false;
				
				Foundation.ACTIVITY.post(new Runnable() {
					
					@Override
					public void run() {
						toDestroy();
					}
					
				});
	
				break;
			}
			default: {
				throw new IllegalArgumentException("Unknown Message: "+message);
			}
		}
	}

	@Override
	public void draw(Graphics graphics) {
		if(isVisible) {
			
			Rectangle area = getEdge();
			
			graphics.draw(coreEarthShot, (int) area.getX(), (int) area.getY(), (int) area.getMaxX(), graphics.getHeight());
			graphics.draw(getEdge(), Color.RED);
		}
	}

	@Override
	public void update(Graphics graphics, long delta) {
		draw(graphics);
		
		if(!getEdgeNotifier().isInside(getEdge())) {
			getEdgeNotifier().edgeReached(this);
		}
	}

	@Override
	protected Rectangle getEdge() {
		int x = CoordinateConverter.toPixelX(getX());
		int y = CoordinateConverter.toPixelY(getY());
		
		System.out.println("GETEDGE : " + x + " -> " + y);
		
		return new Rectangle(x - (coreEarthShot.getWidth() / 2), y - (coreEarthShot.getHeight() / 2), coreEarthShot.getWidth(), coreEarthShot.getHeight());
	}
	
	
}
