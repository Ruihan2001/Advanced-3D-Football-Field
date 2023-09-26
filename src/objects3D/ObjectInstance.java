package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Camera;
import GraphicsObjects.colors;
import GraphicsObjects.gl;


// Models cached up for smooth speed

public class ObjectInstance {
	public static Sphere sphere = new Sphere(1.0f, 32, 32);
	public static Cylinder cylinder = new Cylinder(1.0f, 1.0f, 32);
	public static Cube cube = new Cube();
	
	//draw sky box
	public static void drawSkybox(Camera camera, Texture texSkybox)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glPushMatrix();
		GL11.glTranslatef(camera.position.x, camera.position.y, camera.position.z);//反向移动的原点位置
		GL11.glScalef(256.0f, -256.0f, 256.0f);//纹理坐标是反着的，这里缩放取反
		SkyCube cube = new SkyCube();
		cube.DrawTexCube(texSkybox);
		GL11.glPopMatrix();
	}
	
	//draw a tree
	public static void drawTree(float x, float y, float z)
	{
		gl.bind_texture(null);
		gl.color(colors.white);
		gl.setMaterial(colors.white.mul(0.2f), colors.white.mul(0.6f), colors.white.mul(1.0f), 32.0f);
		
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);

			GL11.glPushMatrix();
			gl.color(colors.orange);
			GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(0.4f, 0.4f, 6.0f);
			cylinder.draw(null);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			gl.color(colors.dkgreen);
			GL11.glTranslatef(0.0f, 6.0f, 0.0f);
			GL11.glScalef(3.0f, 2.5f, 3.0f);
			sphere.draw(1.0f, null);
			GL11.glPopMatrix();
			
		GL11.glPopMatrix();
	}
	
	//draw goals
	public static void draw_door()
	{
		gl.color(colors.white);
		gl.bind_texture(null);
		
		//front goal
		gl.push_matrix();
		gl.translate(0.0f, 6.0f, 0.0f);
		gl.scale(16.0f, 0.2f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(7.9f, 0.0f, 0.0f);
		gl.scale(0.2f, 6.0f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-7.9f, 0.0f, 0.0f);
		gl.scale(0.2f, 6.0f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		//back goal
		gl.push_matrix();
		gl.translate(0.0f, 5.0f, -6.0f);
		gl.scale(12.0f, 0.2f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(5.9f, 0.0f, -6.0f);
		gl.scale(0.2f, 5.0f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-5.9f, 0.0f, -6.0f);
		gl.scale(0.2f, 5.0f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		
		gl.push_matrix();
		gl.translate(7.9f, 6.0f, 0.0f);
		gl.rotate(-98.0f, 1.0f, 0.0f, 0.0f);
		gl.rotate(18.0f, 0.0f, 0.0f, 1.0f);
		gl.scale(0.2f, 6.5f, 0.2f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-7.9f, 6.0f, 0.0f);
		gl.rotate(-98.0f, 1.0f, 0.0f, 0.0f);
		gl.rotate(-18.0f, 0.0f, 0.0f, 1.0f);
		gl.scale(0.2f, 6.5f, 0.2f);
		cube.draw();
		gl.pop_matrix();
	}

	//Spectator stands
	public static void drawStage(Texture tex, float dir)
	{
		float x = 25 * dir; 
		
		gl.bind_texture(tex);
		gl.push_matrix();
		gl.translate(x, 0.0f, 46.0f);
		gl.scale(30.0f, 2.0f, 2.0f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(x, 0.0f, 48.0f);
		gl.scale(30.0f, 4.0f, 2.0f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(x, 0.0f, 50.0f);
		gl.scale(30.0f, 6.0f, 2.0f);
		cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(x, 0.0f, 52.0f);
		gl.scale(30.0f, 8.0f, 2.0f);
		cube.draw();
		gl.pop_matrix();
	}
	
	public static void draw_board(float w, float h, float height, Texture texAD)
	{
		gl.color(colors.white);
		
		gl.push_matrix();
		gl.translate(0.0f, height, 0.0f);
		gl.scale(w, h, 0.2f);
		gl.bind_texture(null);
		ObjectInstance.cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(w * 0.5f - 0.1f, 0.0f, 0.0f);
		gl.scale(0.2f, height, 0.2f);
		ObjectInstance.cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-(w * 0.5f - 0.1f), 0.0f, 0.0f);
		gl.scale(0.2f, height, 0.2f);
		ObjectInstance.cube.draw();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(0.0f, height + 0.25f, 0.1f);
		gl.scale(w - 0.5f, h - 0.5f, 0.1f);
		gl.bind_texture(texAD);
		ObjectInstance.cube.draw();
		gl.pop_matrix();
	}
}
