public class Vector2D
{
    private double x,y;
    public Vector2D()
    {
        x=0;
        y=0;
    }
    public Vector2D(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    public double getx()
    {
        return this.x;
    }
    public double gety()
    {
        return this.y;
    }
    public void setx(double x)
    {
        this.x=x;
    }
    public void sety(double y)
    {
        this.y=y;
    }
    public void translate(double dx, double dy)
    {
        x+=dx;
        y+=dy;
    }
    public double norm()
    {
        return Math.sqrt(Math.pow(this.getx(), 2) + Math.pow(this.gety(), 2));
    }
    public void normalise()
    {
            double norm = norm();
            x = x / norm;
            y = y / norm;
    }
    public double dotProduct(Vector2D o)
    {
        return this.getx() * o.getx() + this.gety() * o.gety();
    }
    
}
