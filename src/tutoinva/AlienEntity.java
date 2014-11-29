/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoinva;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * An entity which represents one of our space invader aliens.
 * 
 * @author Kevin Glass
 */
public class AlienEntity extends Entity {
	/** The speed at which the alient moves horizontally */
	private double moveSpeed = -175;
	/** The game in which the entity exists */
	private Game game;
	
	/**
	 * Create a new alien entity
	 * 
	 * @param game The game in which this entity is being created
	 * @param ref The sprite which should be displayed for this alien
	 * @param x The intial x location of this alien
	 * @param y The intial y location of this alient
	 */
	public AlienEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		dy = -moveSpeed;
	}

	/**
	 * Request that this alien moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public boolean move(long delta) {
		// if we have reached the bottom of the screen then top pop

		if (y > game.getHeight()- sprite.getHeight()) {
			y=0;
		}
		
		// proceed with normal move

		super.move(delta);
                return true;
	}
	
	/**
	 * Update the game logic related to aliens
	 */
	public void doLogic() {
            

		dx = -dx;
		y += 10;
                

		if (y > 570) {
                    dx=-dx;
                    y-=10;
		}
	}
	
	/**
	 * Notification that this alien has collided with another entity
	 * 
	 * @param other The other entity
	 */
	public void collidedWith(Entity other) {
                
                // if AlienEntity is colliding the ShipEntity
                // we make it stop, kill it, make a PieceEntity take its place

		if (other instanceof ShotEntity) {
                    // remove the affected entities



                    game.removeEntity(other);

                    game.removeEntity(this);


                    // notify the game that the alien has been killed
                    game.notifyAlienKilled();

		}
                
                if (other instanceof ShipEntity || other instanceof PieceEntity)
                {
                    // remove the affected entities
                        
                    int xAlien = this.getX();
                    int yAlien = this.getY();
                    
                    int xOther = other.getX();
                    int yOther = other.getY();
                     
                    int x = 0;
                    int y = 0;
                    
                    int centreAlienX = xAlien+this.getSprite().getWidth()/2;
                    int centreAlienY = yAlien+this.getSprite().getHeight()/2;
                    
                    int gX = xOther;
                    int gY = yOther + other.getSprite().getHeight()/2;
                    
                    int hX = xOther + other.getSprite().getWidth()/2;
                    int hY = yOther;
                    
                    int dX = xOther + other.getSprite().getWidth();
                    int dY = gY;
                    
                    int bX = hX;
                    int bY = yOther + other.getSprite().getHeight();
                    
                    int ecartG = Math.abs(gX-centreAlienX)+Math.abs(gY-centreAlienY);
                    int ecartH = Math.abs(hX-centreAlienX)+Math.abs(hY-centreAlienY);
                    int ecartD = Math.abs(dX-centreAlienX)+Math.abs(dY-centreAlienY);
                    int ecartB = Math.abs(bX-centreAlienX)+Math.abs(bY-centreAlienY);
                    
                    ArrayList<Integer> listeEcarts = new ArrayList<Integer>();
                    listeEcarts.add(ecartG);
                    listeEcarts.add(ecartD);
                    listeEcarts.add(ecartH);
                    listeEcarts.add(ecartB);
                    
                    
                    
                    // block au top
                    if(xAlien+this.getSprite().getWidth()/2 < xOther+other.getSprite().getWidth() && xAlien+this.getSprite().getWidth()/2 > xOther && yAlien+this.getSprite().getHeight() < yOther+20)
                    {
                        x = xOther;
                        y = yOther - getSprite().getHeight()-5;
                    }
                    
                    // block on the left
                    else if(xAlien < xOther && xAlien+this.getSprite().getWidth() < xOther+other.getSprite().getWidth() && yAlien < yOther+other.getSprite().getHeight() && yAlien+this.getSprite().getHeight()>yOther)
                    {
                        x = xOther - getSprite().getWidth() - 5;
                        y = yOther;
                    }
                    
                    // block on the right
                    else if(xAlien > xOther + other.getSprite().getWidth()-20 && xAlien+this.getSprite().getWidth() > xOther+other.getSprite().getWidth() &&yAlien < yOther+other.getSprite().getHeight() && yAlien+this.getSprite().getHeight()>yOther)
                    {
                        x = xOther + other.getSprite().getWidth() + 5;
                        y = yOther;
                    }
                    
                    
                    game.removeEntity(this);


                    // notify the game that the alien has been killed
                    System.out.print("Alien Kill : alien("+xAlien+","+yAlien+") - other("+xOther+","+yOther+")");
                    game.notifyAlienKilled();


                    PieceEntity newPiece = new PieceEntity(game,"sprites/piece.gif", x, y);
                    
                    game.addBlock(newPiece);
			
		}
	}
        
}