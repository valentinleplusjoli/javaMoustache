/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoinva;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PieceEntity extends Entity {
	/** The game in which this entity exists */
	private Game game;
	/**
	 * Create a new shot from the player
	 * 
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public PieceEntity(Game game,String sprite,int x,int y) {
		super(sprite,x,y);
		
                this.game = game;
	}
    
        public boolean move(long delta) {
        
            //return false;
            
            // if we're moving left and have reached the left hand side
            // of the screen, don't move
            if ((dx < 0) && (x < 10)) {
                    return false;
            }
            // if we're moving right and have reached the right hand side
            // of the screen, don't move
            if ((dx > 0) && (x > 750)) {
                    return false;
            }


            super.move(delta);
            return true;
        }
        
        public void rotationGauche(ShipEntity ship)
        {
            int xShip = ship.getX();
            int yShip = ship.getY();
            
            int xBlock = this.getX();
            int yBlock = this.getY();
            
            int eX = xShip - xBlock;
            int eY = yShip - yBlock;
            
            this.x = xShip + eY*-1;
            this.y = yShip + eX;
        }
        
        public void rotationDroite(ShipEntity ship)
        {
            int xShip = ship.getX();
            int yShip = ship.getY();
            
            int xBlock = this.getX();
            int yBlock = this.getY();
            
            int eX = xShip - xBlock;
            int eY = yShip - yBlock;
            
            this.x = xShip + eY;
            this.y = yShip + eX*-1;
        }
    
    /**
	 * Notification that this piece has collided with another entity
	 * 
	 * @param other The other entity
	 */
	public void collidedWith(Entity other) {
                
                
	}
}
