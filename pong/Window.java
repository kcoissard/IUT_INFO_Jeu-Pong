import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class Window extends JFrame implements ActionListener
{
    private final int DELAY = 1000;
    private Timer timer;
    private BallAnimator b1anim;
    
	public Window()
	{
		initUI();
	}

	private void initUI()
	{
		setTitle("Pong");
		setSize(310, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Terrain terrain = new Terrain();
		add(terrain);
        
        PlayerBeam b2 = new PlayerBeam(50,15);
        b2.setPosition(20,50);
        terrain.addDrawable(b2);
        PlayerBeamAnimator b2anim = new PlayerBeamAnimator(b2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 10.0);
        terrain.addAnimator(b2anim);
        addKeyListener(b2anim);
        
        PlayerBeam b3 = new PlayerBeam(50,15);
        b3.setPosition(280,50);
        terrain.addDrawable(b3);
        PlayerBeamAnimator b3anim = new PlayerBeamAnimator(b3, KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D, 10.0);
        terrain.addAnimator(b3anim);
        addKeyListener(b3anim);
        
        /*PlayerBeam b4 = new PlayerBeam(150,15);
        b4.setPosition(150,75);
        terrain.addDrawable(b4);*/
        
        PlayerBeam bo1 = new PlayerBeam(150,5);
        bo1.setPosition(300,75);
        terrain.addDrawable(bo1);
        
        PlayerBeam bo2 = new PlayerBeam(150,5);
        bo2.setPosition(0,75);
        terrain.addDrawable(bo2);
        
        PlayerBeam bo3 = new PlayerBeam(5,300);
        bo3.setPosition(150,0);
        terrain.addDrawable(bo3);
        
        PlayerBeam bo4 = new PlayerBeam(5,300);
        bo4.setPosition(150,150);
        terrain.addDrawable(bo4);
		
		Ball b1 = new Ball(2);
        b1.setPosition(50,50);
        terrain.addDrawable(b1);
        b1anim = new BallAnimator(b1, 3.0, new Vector2D(-1.0, 0), b2, b3, bo1, bo2);
        terrain.addAnimator(b1anim);
        
        
        terrain.addCollisionObject(b1);
        b1.addObstacle(b2);
        b1.addObstacle(b3);
        b1.addObstacle(bo1);
        b1.addObstacle(bo2);
        b1.addObstacle(bo3);
        b1.addObstacle(bo4);
        /*terrain.addCollisionObject(b2);
        terrain.addCollisionObject(b3);
        b2.addObstacle(b4);
        b3.addObstacle(b4);*/

        timer = new Timer(DELAY, this);
        timer.start();
	}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setTitle("Pong player 1 : " + b1anim.getPlayer1Score() + "   player 2 : " + b1anim.getPlayer2Score());
    }
}

