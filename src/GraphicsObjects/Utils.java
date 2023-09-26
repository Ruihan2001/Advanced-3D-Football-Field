package GraphicsObjects;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Utils {

	public static FloatBuffer ConvertForGL(float[] ToConvert)

	{
		FloatBuffer Any4 = BufferUtils.createFloatBuffer(4);
		Any4.put(ToConvert[0]).put(ToConvert[1]).put(ToConvert[2]).put(ToConvert[3]).flip();
		return Any4;
	}

	public static FloatBuffer initFloat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Two auxiliary functions are added here
	
	// to float buffer
	public static FloatBuffer toBuffer(Vector4f v)
	{
		FloatBuffer buf = BufferUtils.createFloatBuffer(4);
		buf.put(v.x).put(v.y).put(v.z).put(v.a).flip();
		return buf;
	}
	
	public static FloatBuffer toBuffer(Point4f v)
	{
		FloatBuffer buf = BufferUtils.createFloatBuffer(4);
		buf.put(v.x).put(v.y).put(v.z).put(v.a).flip();
		return buf;
	}
	
	public static FloatBuffer toBuffer(vec3 v)
	{
		FloatBuffer buf = BufferUtils.createFloatBuffer(3);
		buf.put(v.x).put(v.y).put(v.z).flip();
		return buf;
	}
	
	public static FloatBuffer toBuffer(vec4 v)
	{
		FloatBuffer buf = BufferUtils.createFloatBuffer(4);
		buf.put(v.x).put(v.y).put(v.z).put(v.w).flip();
		return buf;
	}
	
	public static float random(float a, float b)
	{
		return (float)(a + (b - a) * Math.random()); 
	}
	
}
