import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;


public class Ball implements Drawable, CollisionObject
{
    private Vector2D pos;
    private double radius;
    public Ball()
    {
        this.pos = new Vector2D();
        radius=3;
    }
    public Ball(double radius)
    {
        this.pos = new Vector2D();
        this.radius=radius;
    }
    public void move(Vector2D vector)
    {
        this.pos.setx( this.pos.getx() + vector.getx());
        this.pos.sety( this.pos.gety() + vector.gety());
    }
    public void setPosition(double x, double y)
    {
        this.pos.setx(x);
        this.pos.sety(y);
    }
    public Vector2D getPosition()
    {
        return this.pos;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawOval((int)(pos.getx() - radius), (int)(pos.gety() - radius), (int)(2 * radius), (int)(2 * radius));
    }
    
    //CollisionObject part
    private LinkedList<CollisionHandler> handlers = new LinkedList<CollisionHandler>();
    private LinkedList<CollisionObstacle> obstacles = new LinkedList<CollisionObstacle>();
    
    public void addHandler(CollisionHandler handler)
    {
        handlers.add(handler);
    }

    public void removeHandler(CollisionHandler handler)
    {
        handlers.remove(handler);
    }
    
    public void addObstacle(CollisionObstacle obstacle)
    {
        obstacles.add(obstacle);
    }
    
    public void removeObstacle(CollisionObstacle obstacle)
    {
        obstacles.remove(obstacle);
    }

    public void testCollision()
    {
        for(CollisionObstacle elem : obstacles)
            testCollisionWith(elem);
    }
 
    public void testCollisionWith(CollisionObstacle obstacle)
    {
        Vector2D normal = obstacle.getCollisionNormalWith(this);
        if(normal != null)
            for(CollisionHandler elem : handlers)
                elem.onCollision(normal, obstacle);
    }
}
