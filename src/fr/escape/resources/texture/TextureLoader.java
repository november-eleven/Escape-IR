package fr.escape.resources.texture;

import java.nio.file.Path;

import fr.escape.graphics.Texture;
import fr.escape.resources.ResourcesLoader;

public abstract class TextureLoader implements ResourcesLoader<Texture> {
	
	public static final String BACKGROUND_ERROR = "berror.png";
	
	public static final String WEAPON_MISSILE = "wmissile.png";
	public static final String WEAPON_FIREBALL = "wfireball.png";
	public static final String WEAPON_SHIBOLEET = "wshiboleet.png";
	public static final String WEAPON_BLACKHOLE = "wblackhole.png";
	
	public static final String WEAPON_MISSILE_SHOT = "wsmissile.png";
	public static final String WEAPON_SHIBOLEET_SHOT = "wsshiboleet.png";
	
	// TODO REMOVE
	public static final String DEBUG_WIN = "swin.png";
	
	public Path getPath() {
		return PATH.resolve("texture");
	}
	
}