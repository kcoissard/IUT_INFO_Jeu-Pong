import java.util.LinkedList;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;
import java.io.File;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;

class Terrain extends JPanel implements ActionListener
{

    private final int DELAY = 150;
    private Timer timer;
    private LinkedList<Drawable> drawables;
    private LinkedList<Animator> animators;
    private LinkedList<CollisionObject> collisionObjects;
    private Image backgroundImage;

    public Terrain()
    {
        drawables = new LinkedList<Drawable>();
        animators = new LinkedList<Animator>();
        collisionObjects = new LinkedList<CollisionObject>();
        try
        {
            backgroundImage = ImageIO.read(new File("background.png"));
        }
        catch(Exception e)
        {
            backgroundImage = null;
            System.err.println("Background not found");
        }
        
        initTimer();
    }
    
    void addDrawable(Drawable d)
    {
        drawables.add(d);
    }
    
    void removeDrawable(Drawable d)
    {
        drawables.remove(d);
    }
    
    void addAnimator(Animator a)
    {
        animators.add(a);
    }
    
    void removeAnimator(Animator a)
    {
        animators.remove(a);
    }
    
    void addCollisionObject(CollisionObject o)
    {
        collisionObjects.add(o);
    }
    
    void removeCollisionObject(CollisionObject o)
    {
        collisionObjects.remove(o);
    }

    private void initTimer()
    {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer()
    {
        return timer;
    }

    private void doDrawing(Graphics g)
    {
        if(backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, 1000, 700, Color.BLACK, null);
        for(Drawable elem : drawables)
            elem.draw(g);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(CollisionObject elem : collisionObjects)
            elem.testCollision();
        for(Animator elem : animators)
            elem.update();
        repaint();
    }
}
