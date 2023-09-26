package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.*;

public class Lamp {
	vec3 position = new vec3(0.0f);
	float height;
	float angle;
	
	int lightID;
	
	Cylinder cylinder;
	vec4 color;
	
	public Lamp(int light, vec3 pos, float height, float angle, vec4 color)
	{
		this.position = pos;
		this.height = height;
		this.angle = angle;
		
		lightID = light;
		gl.spotLight(light, position, position.negate(), colors.white.mul(0.5f), 40.0f, 7.0f);
		cylinder = new Cylinder();
		this.color = color;		
	}
	
	public void draw()
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(position.x, position.y, position.z);
		GL11.glRotatef(-angle, 0.0f, 1.0f, 0.0f);
		
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		
		GL11.glPushMatrix();
		GL11.glScalef(0.4f, height, 0.4f);
		GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
		cylinder.draw(null);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0f, height, 0.0f);
		GL11.glRotatef(45.0f, 0.0f, 0.0f, 1.0f);
		GL11.glScalef(0.2f, height / 4.0f, 0.2f);
		GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
		cylinder.draw(null);
	
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}
	
	public void setLight()
	{
		float x = height / 4;
		//x = (float)Math.sqrt(x * x + x * x);
		vec3 pos = position.add(new vec3(x, height + x, 0.0f));
		vec3 dir = new vec3(pos.x - x * 3.5f, 0.0f, pos.z);
		dir = dir.sub(pos);
		dir = dir.rotateY((float)Math.toRadians(angle));
		
		//angle += 1.0f;
		
		gl.spotLightPosition(lightID, pos);
		gl.spotLightDir(lightID, dir);
	}
}
