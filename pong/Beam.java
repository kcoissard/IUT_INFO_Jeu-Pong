
import java.awt.Color;
import java.awt.Graphics;


public class Beam implements Drawable, CollisionObstacle
{
    private Vector2D pos;
    private double height, width;    
    public Beam()
    {
        this.pos = new Vector2D();
        height=10;
        width=2;
    }
    public double getHeight()
    {
        return this.height;
    }
    public Vector2D getPosition()
    {
        return this.pos;
    }
    public Beam(double height,double width)
    {
        this.pos = new Vector2D();
        this.height=height;
        this.width=width;
    }
    public void setPosition(double x, double y)
    {
        this.pos.setx(x);
        this.pos.sety(y);
    }
    public void move(Vector2D vector)
    {
        this.pos.setx( this.pos.getx() + vector.getx());
        this.pos.sety( this.pos.gety() + vector.gety());
    }
    
    public boolean isInside(Vector2D point)
    {
        return point.getx() >= pos.getx() - (width / 2) && point.getx() <= pos.getx() + (width / 2) && point.gety() >= pos.gety() - (height / 2) && point.gety() <= pos.gety() + (height / 2);
    }

    public Vector2D getCollisionNormalWith(CollisionObject object)
    {
        if(object instanceof Ball)
        {
            Ball ball = (Ball)object;
            if(this.isInside(ball.getPosition()))
            {
                double top = pos.gety() - (height / 2);
                double bottom = pos.gety() + (height / 2);

                if(Math.abs(ball.getPosition().gety() - top) < 5 || Math.abs(ball.getPosition().gety() - bottom) < 5)
                    return new Vector2D(0.0, 1.0);
                else
                    return new Vector2D(1.0, 0.0);
            }
        }
        else
        {
            if(object instanceof Beam)
            {
                Beam beam = (Beam)object;
                //pas fini, mais bon OSEF le reste est pas utile pour le jeu
               Vector2D topLeft = new Vector2D(pos.getx() - (width / 2), pos.gety() - (height / 2));
               Vector2D topRight = new Vector2D(pos.getx() + (width / 2), pos.gety() - (height / 2));
               Vector2D bottomLeft = new Vector2D(pos.getx() - (width / 2), pos.gety() + (height / 2));
               Vector2D bottomRight = new Vector2D(pos.getx() + (width / 2), pos.gety() + (height / 2));
               
               if(beam.isInside(topLeft) || beam.isInside(topRight) || beam.isInside(bottomLeft) || beam.isInside(bottomRight))
                   return new Vector2D(1.0, 0.0);
               else
                   return new Vector2D(0.0, 0.0);
            }
        }
        return new Vector2D(0.0, 0.0);
    }
        
    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int)(pos.getx() - width / 2), (int)(pos.gety() -height / 2), (int)(width), (int)(height));
    }
        
}