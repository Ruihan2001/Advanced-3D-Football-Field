package GraphicsObjects;

public class Point4f {

	public float x;
	public float y;
	public float z;
	public float a;
	
	
	// default constructor
	public Point4f() { 
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	
	//initializing constructor
	public Point4f(float x, float y, float z,float a) { 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPostion(int postion)
	{
		switch(postion)
		{
		case 0: return x;
		case 1: return y;
		case 2: return z; 
		case 3: return a; 
		default: return Float.NaN;  
		} 
	}
	
	public String toString()
	{
		return ("(" + x +"," + y +"," + z + "," + a +")");
    }
 
	 //implement Point plus a Vector 
	
	/* The PlusVector method implements the point and vector summation.  
	 * Note: Point+Vector=Point.
	 * This method returns a new point by adding the x,y,z coordinates and a of the original point to the x,y,z,a values of the vector.
	 * 
	 * Put another way, 
	 * the vector formed by taking the newly obtained point as the end point and the initial point as the origin is the parameter vector in this method.
	 */
	public Point4f PlusVector(Vector4f Additonal) { 
Point4f newPlus=new Point4f(x,y,z,a);
		
		newPlus.x=this.x+Additonal.x;
		newPlus.y=this.y+Additonal.y;
		newPlus.z=this.z+Additonal.z;
		newPlus.a=this.a+Additonal.a;
		
		return newPlus;
		
	} 
	
	 //implement Point minus a Vector 
	
	/* The PlusVector method implements the subtraction of points and vectors.  
	 * Note:Point-Vector=Point.
	 * The method returns a new point by subtracting the x, y, z, a of the origin from the x, y, z and a values of the vector.
	 * 
	 * Put another way, 
	 * the vector formed by taking the newly obtained point as the origin and the initial point as the point is the parameter vector in this method.
	 */
	public Point4f MinusVector(Vector4f Minus) { 
		Point4f newMinus=new Point4f(x,y,z,a);
		newMinus.x=this.x-Minus.x;
		newMinus.y=this.y-Minus.y;
		newMinus.z=this.z-Minus.z;
		newMinus.a=this.a-Minus.a;
		return newMinus;
	}
	
	
	/// Point - Point
	
	/*The MinusPoint method implements point and point subtraction. 
	 * Note:Point-Point=Vector.
	 * This method obtains a vector by subtracting the x, y, z and a of the Minus point from the x, y, z and a values of the origin. 
	 * Put another way,
	 * The vector formed by taking the initial point and the parameter point as the two ends is the return value of the method.
	 */
	public Vector4f MinusPoint(Point4f Minus) { 
Vector4f result=new Vector4f();
		
		result.x=this.x-Minus.x;
		result.y=this.y-Minus.y;
		result.z=this.z-Minus.z;
		result.a=this.a-Minus.a;
		
		return result;
	}
	
	 
	 // Remember point + point  is not defined so we not write a method for it. 
	
	
	 
	  
	
}

/*................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
....................................=?7777+.....................................
.............................,8MMMMMMMMMMMMMMMMM7...............................
...........................$MMMMMMMMMMMMMMMMMMMMMM7.............................
........................IMMMMMMMMMMMMMMMMMMMMMMMMMMMM?..........................
......................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN........................
.....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$......................
...................ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8....................
.................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM...................
................IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$.................
...............=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ................
..............:MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
..............7MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:...............
..............DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ...............
..............MMMMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMMM8...............
..............MMMMMMMMMMMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMMMMMMMMN...............
..............NMMMMMMMMMMMMMMMMMMMM MM MMMMMMMMMMMMMMMMMMMMMMMMMO...............
..............$MMMMMMMMMMMMMMMMMM MMMM MMMMMMMMMMMMMMMMMMMMMMMMMI...............
..............+MMMMMMMMMMMMMMMMM          MMMMMMMMMMMMMMMMMMMMMM=...............
...............8MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMMM................
................MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMM8................
................MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMN,................
................=MMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMM..................
.................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ..................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD...................
...................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
....................8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......................
.....................,8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,.......................
........................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMN?.........................
..........................NMMMMMMMMMMMMMMMMMMMMMMMMMI...........................
.............................$MMMMMMMMMMMMMMMMMMM?..............................
.................................,I$NMMMMMN$?...................................
................................................................................
................................................................................
................................................................................
.......................................................................*/
