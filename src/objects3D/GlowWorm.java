package objects3D;
import org.lwjgl.opengl.GL11;

import GraphicsObjects.*;

//fireflyies class

public class GlowWorm {
	public vec3 position = new vec3(0.0f);
	vec3 dest = new vec3(0.0f);
	float speed = 50.0f;
	int lightID;
	
	Sphere sphere;
	vec4 color;
	
	float a1;
	float a2;
	float a3;
	
	//Simple particle effect
	static int COUNT = 40;
	vec3 particles[] = new vec3[COUNT];
	vec3 velocity[] = new vec3[COUNT];
	float life[] = new float[COUNT];
	
	public GlowWorm(int light, vec4 color)
	{
		lightID = light;
		gl.pointLight(light, position, color, 1.0f, 0.20001f, 0.0f);
		sphere = new Sphere();
		this.color = color;
		
		a1 = (float)Math.random() * 360.0f;
		a2 = (float)Math.random() * 360.0f;
		a3 = (float)Math.random() * 360.0f;
		
		for(int i=0; i<COUNT; ++i) {
			particles[i] = new vec3();
			velocity[i] = new vec3();
			reset(i);
		}
	}

	//movement
	public void move(float x, float y, float z)
	{
		dest.set(x, y, z);
	}
	
	public void move(vec3 pos)
	{
		dest.set(pos.x,  pos.y, pos.z);
	}
	
	//update
	public void update(float delay)
	{
		//calculate distence
		vec3 dir = dest.sub(position);
		float distance = dir.length();
		if(distance < 0.01f) {
			float range = 10.0f;
			//move randomly
			float x = position.x + Utils.random(-range, range);
			float y = Utils.random(0.2f, 4.0f);
			float z = position.z + Utils.random(-range, range);
			move(x, y, z);
		}
		else {
			//Calculate velocity
			float n = Math.min(speed, distance) * delay;
			//movement
			dir = dir.normal();
			dir = dir.mul(n);
			position = position.add(dir);
			
			//System.out.printf("move %f %f %f -> %f %f %f\n", position.x, position.y, position.z, dir.x, dir.y, dir.z);
		}
		//System.out.print(distance);

		//update particle effect
		for(int i=0; i<COUNT; ++i) {
			--life[i];
			if(life[i] < 0.0f) {
				reset(i);
			}
			else {
				particles[i] = particles[i].add(velocity[i].mul(delay));
			}
		}
		
		a1 += delay * 200.0f;
		a2 += delay * 200.0f;
		a3 += delay * 200.0f;
	}
	
	public void setLight()
	{
		//update light source position
		gl.pointLightPosition(lightID, position);
	}
	
	public void draw()
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(position.x, position.y, position.z);
		GL11.glScalef(0.25f, 0.25f, 0.25f);
		
		GL11.glColor3f(color.x, color.y, color.z);
		
		sphere.draw(null);
		
		//GL11.glColor3f(1.0f - color.x, 1.0f - color.y, 1.0f - color.z);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		
		GL11.glPushMatrix();
		GL11.glRotatef(a1, 1.0f, 1.0f, 0.0f);
		GL11.glTranslatef(2.0f, 0.0f, 0.0f);
		GL11.glScalef(0.25f, 0.25f, 0.25f);
		sphere.draw(null);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glRotatef(a2, 0.0f, 1.0f, 1.0f);
		GL11.glTranslatef(2.0f, 0.0f, 0.0f);
		GL11.glScalef(0.25f, 0.25f, 0.25f);
		sphere.draw(null);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glRotatef(a2, 1.0f, 0.0f, 1.0f);
		GL11.glTranslatef(2.0f, 0.0f, 0.0f);
		GL11.glScalef(0.25f, 0.25f, 0.25f);
		sphere.draw(null);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_POINT_SMOOTH);
		GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glPointSize(4.0f);
		//GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		GL11.glColor4f(color.x + 0.5f, color.y + 0.5f, color.z + 0.5f, 0.5f);
		GL11.glBegin(GL11.GL_POINTS);
		for(int i=0; i<COUNT; ++i) {
			GL11.glVertex3f(particles[i].x, particles[i].y, particles[i].z);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	

	void reset(int i)
	{
		particles[i].set(position.x, position.y, position.z);
		
		float range = 0.05f;
		velocity[i].x = Utils.random(-range, range);
		velocity[i].y = Utils.random(-range * 2.0f, -range * 4.0f);
		velocity[i].z = Utils.random(-range, range);
		
		life[i] = Utils.random(100.0f,  200.0f);
	}
}
