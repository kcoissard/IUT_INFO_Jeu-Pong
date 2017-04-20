import java.util.Random;

public class BallAnimator implements Animator, CollisionHandler
{
    private Random random = new Random();
    private Ball ball;
    private double speed;
    private Vector2D movement;
    private CollisionObstacle player1Beam, player2Beam;
    private CollisionObstacle player1Obstacle, player2Obstacle;
    private int player1Score = 0;
    private int player2Score = 0;

    public BallAnimator(Ball ball, double speed, Vector2D initialMovement, Beam player1Beam, Beam player2Beam, Beam player1Obstacle, Beam player2Obstacle)
    {
        this.ball = ball;
        this.speed = speed;
        this.movement = initialMovement;
        this.ball.addHandler(this);
        this.player1Beam = player1Beam;
        this.player2Beam = player2Beam;
        this.player1Obstacle = player1Obstacle;
        this.player2Obstacle = player2Obstacle;
    }
    
    int getPlayer1Score()
    {
        return player1Score;
    }
    
    int getPlayer2Score()
    {
        return player2Score;
    }

    public void update()
    {
        this.ball.move(new Vector2D(this.movement.getx() * this.speed, this.movement.gety() * this.speed));
    }

    public void onCollision(Vector2D normal, CollisionObstacle obstacle)
    {
        if(normal.dotProduct(new Vector2D(1.0, 0.0)) != 0)
        {
            movement = new Vector2D(-movement.getx(), movement.gety());
            if((obstacle == player1Beam || obstacle == player2Beam))
            {
                Beam beam = (Beam)obstacle;
                double deviationCoef = (ball.getPosition().gety() - beam.getPosition().gety()) / (beam.getHeight() / 2.0);
                movement.translate(0.0, deviationCoef);
                movement.normalise();
                
            }
        }
        else if(normal.dotProduct(new Vector2D(0.0, 1.0)) != 0)
        {
            movement = new Vector2D(movement.getx(), -movement.gety());
        }
        
        
        if((obstacle == player1Obstacle || obstacle == player2Obstacle) && (normal.getx() != 0.0 || normal.gety() != 0.0))
        {
            if(obstacle == player1Obstacle)
                player1Score++;
            else
                player2Score++;
            this.ball.setPosition(150, 75);
            this.movement = new Vector2D(random.nextDouble() - 0.5, random.nextDouble() - 0.5);
            this.movement.normalise();
        }
    }
}
