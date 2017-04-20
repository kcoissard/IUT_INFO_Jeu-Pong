public interface CollisionObject
{
    void addHandler(CollisionHandler handler);
    void removeHandler(CollisionHandler handler);
    void addObstacle(CollisionObstacle obstacle);
    void removeObstacle(CollisionObstacle obstacle);
    void testCollision();
    void testCollisionWith(CollisionObstacle obstacle);
}
