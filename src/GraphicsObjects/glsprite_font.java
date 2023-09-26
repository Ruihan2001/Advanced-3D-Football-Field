package GraphicsObjects;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

//  font class

public class glsprite_font {
	Texture image;
	float cx;
    float cy;
    float s;
    float t;
	
	public glsprite_font()
	{
		
	}
	
	public Boolean init(String filename)
	{
		try {
			image = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(filename));
			
			float w = image.getImageWidth();
            float h = image.getImageHeight();
            cx = w / 16.0f;
            cy = h / 6.0f;
            s = image.getWidth() / 16.0f;
            t = image.getHeight() / 6.0f;
            
            System.out.printf("font init: %f, %f - %f, %f - %f, %f\n", w, h, cx, cy, s, t);
            System.out.printf("texture: w = %f h = %f\n", image.getWidth(), image.getHeight());
            
            return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public void dispose()
	{
		image.release();
		cx = cy = s = t = 0.0f;
	}

	public void textout(float x, float y, String text)
	{
		int ch;
		float u, v;
		
		image.bind();

		for(int i=0; i<text.length(); ++i) {
			ch = (int) text.charAt(i);
			ch = ch - 32;
			u = ((float)(ch % 16)) * s;
            v = ((float)(ch / 16)) * t;
			
            //System.out.printf("%d %f %f - %f %f\n", ch, u, v, u + s, v + t);
            
            gl.draw_image(x, y, cx, cy, u, v, u + s, v + t);
            
            x = x + cx;
		}
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL11.GL_NONE);
	}
	
	public void printf(float x, float y, String format, Object... args)
	{
		String s = String.format(format, args);
		this.textout(x, y, s);
	}
	
	public float char_width()
    {
        return cx;
    }

    public float char_height()
    {
        return cy;
    }
}
