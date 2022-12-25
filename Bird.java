/*  Name: Angela Yu
*  PennKey: yuangel
*  Recitation: 206
*  Execution: N/A (no main method)
*
*  A class that represents the bird projectile in
*  Irate Avians. Can update its own position based
*  on velocity and time, and can compute whether
*  it overlaps a given Target.
*
*/

public class Bird {

    // The position, velocity, and radius members of the bird.
    private double xPos, yPos, xVel, yVel, radius;
    
    /**
    * How many more times the player can throw the bird
    * before losing the game.
    */
    private int numThrowsRemaining;
    
    /**
    * Initialize the bird's member variables
    * with the same names as the inputs to those values.
    * Initializes the bird's velocity components to 0.
    */
    public Bird(double xPos, double yPos, double radius, int numThrows) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        numThrowsRemaining = numThrows;

        xVel = 0.0;
        yVel = 0.0;
    }
    
    /**
    * Draws a circle centered at the bird's position
    * with a radius equal to the bird's radius.
    * Additionally, draws a triangular beak and two
    * circular eyes somewhere on the circle to make
    * the bird look more like a bird. Additional details
    * are up to your discretion.
    * Also draws the bird's remaining throws 0.1 units
    * above its circular body.
    */
    public void draw() {
        PennDraw.setPenColor(192, 72, 46);
        PennDraw.filledCircle(xPos, yPos, radius);

        PennDraw.setPenColor(0, 0, 0);
        PennDraw.filledCircle(xPos - 0.05, yPos + 0.05, 0.05);
        PennDraw.filledCircle(xPos + 0.05, yPos + 0.05, 0.05);

        PennDraw.setPenColor(241, 189, 62);
        PennDraw.filledPolygon(xPos - 0.025, yPos + 0.025, xPos + 0.025, 
        yPos + 0.025, xPos, yPos - 0.05);

        PennDraw.text(xPos, yPos + radius + 0.1, "" + numThrowsRemaining);
    }
    
    /**
    * Draw the line representing the bird's initial velocity
    * when the player is clicking and dragging the mouse.
    */
    public void drawVelocity() {
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.line(xPos, yPos, xPos + xVel, yPos + yVel);
    }
    
    /**
    * Set xPos and yPos to 1.0,
    * set xVel and yVel to 0.0,
    * and clear the list of targets hit this launch.
    */
    public void reset() {
        xPos = 1.0;
        yPos = 1.0;
        xVel = 0.0;
        yVel = 0.0;
    }
    
    /**
    * Compute the bird's initial velocity as the
    * vector from the mouse's current position to
    * the bird's current position. This will be used
    * in mouse listening mode to update the launch
    * velocity.
    */
    public void setVelocityFromMousePos() {
        xVel = xPos - PennDraw.mouseX();
        yVel = yPos - PennDraw.mouseY();
    }
    
    /**
    * Given the change in time, compute the bird's
    * new position and new velocity.
    */
    public void update(double timeStep) {
        xPos = xPos + xVel * timeStep;
        yPos = yPos + yVel * timeStep;

        yVel = yVel - 0.25 * timeStep;
    }
    
    /**
    * A helper function used to find the distance
    * between two 2D points. Remember to use the
    * Pythagorean Theorem.
    */
    private static double distance(double x1, double y1, double x2, double y2) {
        double distanceFrom = Math.sqrt((x1 - x2) * (x1 - x2) + 
        (y1 - y2) * (y1 - y2));
        return distanceFrom;
    }
    
    /**
    * Given a Target, determine if the bird should
    * test for collision against it. If the bird
    * *should* see if it collides with the target,
    * then perform that test. If the bird collides,
    * then decrease the target's HP by 1 and add
    * the target to the bird's list of targets hit
    * during this launch.
    */
    public void testAndHandleCollision(Target t) {
        if (t.getHitPoints() > 0) {
            if (radius + t.getRadius() > distance(t.getXpos(), t.getYpos(), 
            xPos, yPos)) {
                t.setHitThisShot(true);
            } 
        }
    }
    
    /**
     * Inputs: None
     * Outputs: None (void)
     * Description: Reduce numThrowsRemaining by 1.
    */
    public void decrementThrows() {
        numThrowsRemaining--;
    }
    
    /**
     * Inputs: None
     * Outputs: double representing X position of the bird object
     * Description: Getter function that returns a copy
     *              of the indicated member variable.
    */
    public double getXpos() {
        return xPos;
    }

    /**
     * Inputs: None
     * Outputs: double representing Y position of the bird object
     * Description: Getter function that returns a copy
     *              of the indicated member variable.
    */
    public double getYpos() {
        return yPos;
    }

    /**
     * Inputs: None
     * Outputs: double representing the radius of the bird object
     * Description: Getter function that returns a copy
     *              of the indicated member variable.
    */
    public double getRadius() {
        return radius;
    }

    /**
     * Inputs: None
     * Outputs: int representing the number of remaining
     *          throws for the bird 
     * Description: Getter function that returns a copy
     *              of the indicated member variable.
    */
    public int getNumThrowsRemaining() {
        return numThrowsRemaining;
    }
}
