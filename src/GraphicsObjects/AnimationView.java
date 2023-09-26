package GraphicsObjects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class AnimationView {
	public vec3 position;
	public vec3 dir;
	public float yAngle;
	public float pitchAngle;
	float speed;
	float velocityY;			//Gravity
	
	public AnimationView()
	{
		position = new vec3(0.0f, 0.0f, 0.0f);
		dir = new vec3(0.0f, 0.0f, -1.0f);
		yAngle = -90.0f;
		pitchAngle = 0.0f;
		speed = 10.0f;
		velocityY = 0.0f;
	}
	
	public void update(float delay)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			moveLeft(delay);
			
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			moveRight(delay);
			
		}
	
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			forward(delay);
			
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			backward(delay);
			
		}
	
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			turnLeft();
			
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			turnRight();
			
		}
		
		// jump
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(position.y < 0.001f) {
				velocityY = 5.0f;
			}
		}
		
		position.y += velocityY * delay;
		if(position.y < 0.0f) {
			position.y = 0.0f;
			velocityY = 0.0f;
		}
		velocityY -= 0.25f;//gravity
	}
	
	public void touth(float x, float y)
	{
		yAngle += x;
		pitchAngle += y;
		
		computeDir();
	}
	

	public void forward(float delay)
	{
		float n = speed * delay;
		vec3 v = new vec3(dir.x, 0.0f, dir.z);
		v = v.mul(n);
		position = position.add(v);
	}
	
	public void backward(float delay)
	{
		float n = speed * delay;
		vec3 v = new vec3(dir.x, 0.0f, dir.z);
		v = v.mul(n);
		position = position.sub(v);
	}
	
	public void moveLeft(float delay)
	{
		float n = speed * delay;
		vec3 v = new vec3(dir.x, 0.0f, dir.z);
		v = v.mul(n);
		position = position.add(new vec3(v.z, 0.0f, -v.x));
	}
	
	public void moveRight(float delay)
	{
		float n = speed * delay;
		vec3 v = new vec3(dir.x, 0.0f, dir.z);
		v = v.mul(n);
		position = position.add(new vec3(-v.z, 0.0f, v.x));
	}
	
	public void turnLeft()
	{
		yAngle -= 2.0f;
		computeDir();
	}
	
	public void turnRight()
	{
		yAngle += 2.0f;
		computeDir();
	}
	//Recalculating the direction of movement
	void computeDir()
	{
		dir = (new vec3(1.0f, 0.0f, 0.0f)).rotateY((float)Math.toRadians(yAngle));
		dir.y = speed * (float) Math.sin(Math.toRadians(pitchAngle));
		
//		double ax = Math.toRadians(pitchAngle);
//		double ay = Math.toRadians(yAngle);
//		
//		double sinX = Math.sin(ax);
//		double sinY = Math.sin(ay);
//		double cosX = Math.cos(ax);
//		double cosY = Math.cos(ay);
//		
//		dir.x = (float)(speed * cosX * sinY);
//		dir.y = (float)(speed * -sinX);
//		dir.z = (float)(speed * cosX * -cosY);
//		dir = dir.normal();
	}
	public boolean ani() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)|Keyboard.isKeyDown(Keyboard.KEY_S)|Keyboard.isKeyDown(Keyboard.KEY_W)|Keyboard.isKeyDown(Keyboard.KEY_D)|Keyboard.isKeyDown(Keyboard.KEY_Q)|Keyboard.isKeyDown(Keyboard.KEY_E)) {
			return true;
		}else {
		return false;}
	}
}
