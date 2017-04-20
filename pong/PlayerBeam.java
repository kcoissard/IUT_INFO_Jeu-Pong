import java.util.LinkedList;

public class PlayerBeam extends Beam implements CollisionObject
{
    public PlayerBeam()
    {
        super();
    }
    
    public PlayerBeam(double height,double width)
    {
        super(height, width);
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
