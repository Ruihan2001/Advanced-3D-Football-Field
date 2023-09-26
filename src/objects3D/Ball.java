package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Utils;
import GraphicsObjects.vec3;
import GraphicsObjects.vec4;

//Gravity-simulated items
public class Ball {
	public vec3 position = new vec3(0.0f);
	vec3 velocity = new vec3(0.0f);
	
	static float gravity = 1.0f;	//gravity
	
	public Ball()
	{
		
	}
	
	public void reset()
	{
		float range = 20.0f;
		position.x = Utils.random(-range, range);
		position.y = Utils.random(range, range * 2.0f);
		position.z = Utils.random(-range, range);
		
		range = 0.1f;
		velocity.x = Utils.random(-range, range);
		velocity.y = 0.0f;
		velocity.z = Utils.random(-range, range);
	}
	
	//add a force
	public void setForce(vec3 p)
	{
		velocity = velocity.add(p);
	}
	
	//add a force
	public void appendForce(vec3 p)
	{
		velocity = velocity.add(p);
	}

	//update
	public void update(float delay)
	{
		velocity.y -= gravity;
		
		vec3 p = position.add(velocity.mul(delay));
		if(p.y < 0.5f) {
			velocity.y = -velocity.y * 0.75f;//Stretch
			velocity = velocity.mul(0.95f);// Friction
			
			if(Math.abs(velocity.y) < 0.1f) {
				//this.reset();
				velocity.set(0.0f);
			}
		}
		else {
			position = p;
		}
		//System.out.printf("move %f %f %f\n", position.x, position.y, position.z);
	}
}
