/*  Name: Angela Yu
*  Execution: N/A (no main method)
*
*  A class that represents a target to be hit in
*  Irate Avians. Can update its own position based
*  on velocity and time.
*/

public class Target {
    
    // variables for width and height of screen
    private double width, height;
    
    // Position and radius
    private double xPos, yPos, radius;
    
    // Velocity components
    private double xVel, yVel;
    
    /**
    * When a target's hit points reach zero,
    * it has been destroyed by the bird.
    */
    private int hitPoints;
    
    // Track if target has been hit this shot.
    private boolean hitThisShot;
    
    /**
    * Given a position, a radius, a velocity, and a number of hit points,
    * construct a Target.
    */
    public Target(double width, double height, double xPos, double yPos,
    double radius, double xVel, double yVel, int hitPoints) {
        this.width = width; 
        this.height = height;

        this.xPos = xPos;
        this.yPos = yPos; 

        this.radius = radius;

        this.xVel = xVel;
        this.yVel = yVel;

        this.hitPoints = hitPoints; 

        hitThisShot = false; 
    }
    
    /**
    * Draw a circle centered at the target's position
    * with a radius equal to the target's radius.
    * Only draw a Target if it has more than zero
    * hit points.
    */
    public void draw() {
        if (hitPoints > 0) {
            //for (int i = 0; i < numTargets; i++)
                PennDraw.setPenColor(136, 174, 146);
                PennDraw.filledCircle(xPos, yPos, radius);

                PennDraw.setPenColor(255, 255, 255);
                PennDraw.text(xPos, yPos, "" + hitPoints);
        }
    }
    
    /**
    * Given the change in time, update the target's
    * position based on its x and y velocity. When
    * a target is completely offscreen horizontally,
    * its position should wrap back around to the opposite
    * horizontal side. For example, if the target moves off the
    * right side of the screen, its xPos should be set to the
    * left side of the screen minus the target's radius.
    * The same logic should apply to the target's vertical
    * position with respect to the vertical screen boundaries.
    */
    public void update(double timeStep) {
        xPos = xPos + xVel * timeStep;
        yPos = yPos + yVel * timeStep; 

        if (xPos + radius < 0) {
            xPos = width + radius;
        } else if (xPos - radius > width) {
            xPos = radius;
        }

        if (yPos + radius < 0) {
            yPos = height + radius;
        } else if (yPos - radius > height) {
            yPos = radius;
        }
    }
    
    /**
     * Inputs: None
     * Outputs: None (void)
     * Description: Decrement the target's hit points by 1.
    */
    public void decreaseHP() {
        --hitPoints;
    }
    
    /**
     * Inputs: None
     * Outputs: None (void)
     * Description: Setter function for whether or not 
     *              target hit this round.
    */
    public void setHitThisShot(boolean hit) {
        this.hitThisShot = hit;
    }
    
    /**
     * Inputs: None
     * Outputs: boolean representing whether or not the 
     *          target was hit 
     * Description: Return whether or not this target 
     *              is hit this round.
    */
    public boolean isHit() {
        return hitThisShot;
    }
    
    /**
     * Inputs: None
     * Outputs: int representing number of hit points
     * Description: Getter function that returns a copy of the
     *              indicated member variable.
    */
    public int getHitPoints() {
        return hitPoints;
    }
    
    /**
     * Inputs: None
     * Outputs: double representing the X position of the target
     * Description: Getter function that returns a copy of the
     *              indicated member variable.
    */
    public double getXpos() {
        return xPos;
    }
    
    /**
     * Inputs: None
     * Outputs: double representing the Y position of the target
     * Description: Getter function that returns a copy of the
     *              indicated member variable.
    */
    public double getYpos() {
        return yPos;
    }
    
    /**
     * Inputs: None
     * Outputs: double representing the radius of the target
     * Description: Getter function that returns a copy of the
     *              indicated member variable.
    */
    public double getRadius() {
        return radius;
    }
    
}
