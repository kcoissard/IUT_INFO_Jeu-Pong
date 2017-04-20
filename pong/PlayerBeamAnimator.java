import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerBeamAnimator implements Animator, CollisionHandler, KeyListener
{
    private PlayerBeam beam;
    private int upCode;
    private int downCode;
    private int leftCode;
    private int rightCode;
    private boolean moveup = false;
    private boolean movedown = false;
    private boolean moveleft = false;
    private boolean moveright = false;
    private double speed;
    private Vector2D move;
    private boolean collide;

    public PlayerBeamAnimator(PlayerBeam beam, int upCode, int downCode, int leftCode, int rightCode, double speed)
    {
        this.beam = beam;
        this.upCode = upCode;
        this.downCode = downCode;
        this.leftCode = leftCode;
        this.rightCode = rightCode;
        this.speed = speed;
    }

    public void update()
    {
        if(!collide)
        {
            Vector2D move = new Vector2D(0.0, 0.0);

            if(moveup)
                move.translate(0.0, -speed);
            if(movedown)
                move.translate(0.0, speed);
            if(moveright)
                move.translate(speed, 0.0);
            if(moveleft)
                move.translate(-speed, 0.0);
            
            beam.move(move);
        }
    }

    public void onCollision(Vector2D normal, CollisionObstacle obstacle)
    {
        collide = !normal.equals(new Vector2D(0.0, 0.0));
    }
    
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == upCode)
            moveup = true;
        else if(e.getKeyCode() == downCode)
            movedown = true;
        else if(e.getKeyCode() == leftCode)
            moveleft = true;
        else if(e.getKeyCode() == rightCode)
            moveright = true;
    }
    
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == upCode)
            moveup = false;
        else if(e.getKeyCode() == downCode)
            movedown = false;
        else if(e.getKeyCode() == leftCode)
            moveleft = false;
        else if(e.getKeyCode() == rightCode)
            moveright = false;
    }
    
    public void keyTyped(KeyEvent e)
    {
    }
}
