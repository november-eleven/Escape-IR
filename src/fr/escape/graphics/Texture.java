package fr.escape.graphics;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Objects;

import javax.imageio.ImageIO;

import fr.escape.app.Disposable;

/**
 * 
 * <p>
 * This class is Immutable.
 */
// TODO Comment
public final class Texture implements Disposable {

	private final BufferedImage image;
	
	//TODO DEBUG
	private int alpha = 100;
	
	public Texture(File file) throws IOException {
		Objects.requireNonNull(file);
		image = ImageIO.read(file);
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public void dispose() {
		image.flush();
	}
	
	private Image getImage() {
		return image;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public void draw(Graphics2D graphics, int x, int y, int width, int height,
			int srcX, int srcY, int srcWidth, int srcHeight) {
		
		draw(graphics, x, y, width, height, srcX, srcY, srcWidth, srcHeight, 0.);
	}
	
	public void draw(Graphics2D graphics, int x, int y, int width, int height,
			int srcX, int srcY, int srcWidth, int srcHeight, double angle) {

		AffineTransform transformMatrix = null;
		boolean updateMatrix = false;
		
		// Create a Transform Matrix if we need to apply a rotation on Texture
		if(angle != 0) {
			
			transformMatrix = graphics.getTransform();
			updateMatrix = true;
			
			AffineTransform rotationMatrix = new AffineTransform();
			
			// Create Transform Matrix
			rotationMatrix.rotate(Math.toRadians(angle), (x + width) / 2, (y + height) / 2);
			
			// Apply Transform Matrix
			graphics.setTransform(rotationMatrix);

		}
		
		Composite oComposite = null;
		
		if(alpha != 100) {
			Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha / 100);
			oComposite = graphics.getComposite();
	        graphics.setComposite(comp);
		}
        
		graphics.drawImage(getImage(), x, y, width, height, srcX, srcY, srcWidth, srcHeight, null);
		
		if(alpha != 100) {
			graphics.setComposite(oComposite);
		}
		
		// Restore Previous Matrix
		if(updateMatrix) {
			assert transformMatrix != null;
			graphics.setTransform(transformMatrix);
		}
		
	}
	
}
