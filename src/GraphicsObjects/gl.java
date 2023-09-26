package GraphicsObjects;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class gl {
	
	
	// set colors
	
	public static void color(float r, float g, float b)
	{
		GL11.glColor3f(r, g, b);
	}
	
	public static void color(vec3 c)
	{
		GL11.glColor3f(c.x, c.y, c.z);
	}
	
	public static void color(float r, float g, float b, float a)
	{
		GL11.glColor4f(r, g, b, a);
	}
	
	public static void color(vec4 c)
	{
		GL11.glColor4f(c.x, c.y, c.z, c.w);
	}
	
	//Bind the texture, or if the texture is empty, bind the empty texture
	public static void bind_texture(Texture tex)
	{
		if(tex != null) {
			tex.bind();
		}
		else {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL11.GL_NONE);
		}
	}
	
	
	// Matrix transformations
	
	
	public static void translate(float x, float y, float z)
	{
		GL11.glTranslatef(x, y, z);
	}
	
	public static void translate(vec3 v)
	{
		GL11.glTranslatef(v.x, v.y, v.z);
	}
	
	public static void rotate(float a, float x, float y, float z)
	{
		GL11.glRotatef(a, x, y, z);
	}
	
	public static void rotate(float angle, vec3 v)
	{
		GL11.glRotatef(angle, v.x, v.y, v.z);
	}
	
	public static void scale(float x, float y, float z)
	{
		GL11.glScalef(x, y, z);
	}
	
	public static void scale(vec3 v)
	{
		GL11.glScalef(v.x, v.y, v.z);
	}
	
	public static void push_matrix()
	{
		GL11.glPushMatrix();
	}
	
	public static void pop_matrix()
	{
		GL11.glPopMatrix();
	}

	// Drawing 2d images
	public static void draw_image(float x, float y, float w, float h, float u1, float v1, float u2, float v2)
    {
    	GL11.glBegin(GL11.GL_QUADS);
    	GL11.glTexCoord2f(u1,  v1);
    	GL11.glVertex2f(x, y);
    	
    	GL11.glTexCoord2f(u2,  v1);
    	GL11.glVertex2f(x + w, y);
    	
    	GL11.glTexCoord2f(u2,  v2);
    	GL11.glVertex2f(x + w, y + h);
    	
    	GL11.glTexCoord2f(u1,  v2);
    	GL11.glVertex2f(x, y + h);
    	GL11.glEnd();
    }
	
	

	// set light sources
	
	//set the directional light source
	public static void directionLight(int light, vec3 position, vec4 color)
	{
		//enable light source
		GL11.glEnable(light);
		//set light source position. the last value of directional light source is 0.0
		GL11.glLight(light, GL11.GL_POSITION, Utils.toBuffer(new vec4(position, 0.0f)));
		//ambient light colour
		GL11.glLight(light, GL11.GL_AMBIENT, Utils.toBuffer(color.mul(0.2f)));
		//diffuse colour
		GL11.glLight(light, GL11.GL_DIFFUSE, Utils.toBuffer(color));
		//highlight colour
		GL11.glLight(light, GL11.GL_DIFFUSE, Utils.toBuffer(colors.black));
	}
	
	//point light source
	public static void pointLight(int light, vec3 position, vec4 color, float constantAtt, float linearAtt, float quadraticAtt)
	{
		//enable light source
		GL11.glEnable(light);
		//set light source position. the last value of directional light source is 0.0
		GL11.glLight(light, GL11.GL_POSITION, Utils.toBuffer(new vec4(position, 1.0f)));
		//set light source colour
		GL11.glLight(light, GL11.GL_DIFFUSE, Utils.toBuffer(color));
		
		//Set the light source attenuation parameters
		GL11.glLightf(light, GL11.GL_CONSTANT_ATTENUATION, constantAtt);
		GL11.glLightf(light, GL11.GL_LINEAR_ATTENUATION, linearAtt);
		GL11.glLightf(light, GL11.GL_QUADRATIC_ATTENUATION, quadraticAtt);
	}
	
	public static void pointLight(int light, vec3 position, vec4 color)
	{
		pointLight(light, position, color, 1.0f, 0.0f, 0.0f);
	}

	//Spotlight source, parameters are position, direction, colour, angle of emission
	public static void spotLight(int light, vec3 position, vec3 dir, vec4 color, float cutoff, float exponent)
	{
	    pointLight(light, position, color);
	    GL11.glLight(light, GL11.GL_SPOT_DIRECTION, Utils.toBuffer(new vec4(dir, 1.0f)));
	    
	    //Spotlight radiation angle
	    GL11.glLightf(light, GL11.GL_SPOT_CUTOFF, cutoff);
	    GL11.glLightf(light, GL11.GL_SPOT_EXPONENT, exponent);
	}
	
	public static void directionLightPosition(int light, vec3 position)
	{
		GL11.glLight(light, GL11.GL_POSITION, Utils.toBuffer(new vec4(position, 0.0f)));
	}
	
	public static void pointLightPosition(int light, vec3 position)
	{
		GL11.glLight(light, GL11.GL_POSITION, Utils.toBuffer(new vec4(position, 1.0f)));
	}
	
	public static void spotLightPosition(int light, vec3 position)
	{
		pointLightPosition(light, position);
	}
	
	public static void spotLightDir(int light, vec3 dir)
	{
		GL11.glLight(light, GL11.GL_SPOT_DIRECTION, Utils.toBuffer(new vec4(dir, 1.0f)));
	}
	
	//set texture
	public static void setMaterial(vec4 ka, vec4 kd, vec4 ks, float shininess)
	{
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, Utils.toBuffer(ka));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, Utils.toBuffer(kd));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, Utils.toBuffer(ks));
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
	}

}
