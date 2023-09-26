package GraphicsObjects;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z,float a)
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	 //implement Vector plus a Vector 
	
	/*The PlusVector method implements the addition of vectors and vectors. 
	 * Note:Vector+Vector=Vector.
	 * This method gets a new vector by adding the x, y, z and a values of the Addition point from the x, y, z and a values of the original vector.
	 */
	public Vector4f PlusVector(Vector4f Additonal) 
	{ 
		return new Vector4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z,this.a+Additonal.a);
	} 
	
	 //implement Vector minus a Vector 
	
	/*The MinusVector method implements the subtraction of vectors and vectors. 
	 * Note:Vector-Vector=Vector
	 * This method gets a new vector by taking the x, y, z and a values from the x, y, z and a value points of the original vector.
	 */
	public Vector4f MinusVector(Vector4f Minus) 
	{ 
		Vector4f newMinus=new Vector4f(x,y,z,a);
		newMinus.x=this.x-Minus.x;
		newMinus.y=this.y-Minus.y;
		newMinus.z=this.z-Minus.z;
		newMinus.a=this.a-Minus.a;
		
		return newMinus;
	}
	
	//implement Vector plus a Point
	
	/*The PlusPoint method implements the addition of vectors and points. 
	 * Note:Vector+Point=Point
	 * This method gets a new point by adding the x, y, z, a coordinates of the Additional point from the x, y, z, a values of the original vector. 
	 *  
	 * Put another way, 
	 * The vector formed by taking the point in the method argument and the result point as the two ends is the initial vector of the method.
	 */
	public Point4f PlusPoint(Point4f Additonal) 
	{ 
		Point4f newPlus=new Point4f(x,y,z,a);
		newPlus.x=Additonal.x+this.x;
		newPlus.y=Additonal.y+this.y;
		newPlus.z=Additonal.z+this.z;
		newPlus.a=Additonal.a+this.a;
		return newPlus;
	} 
	//Do not implement Vector minus a Point as it is undefined 
	
	//Implement a Vector * Scalar 
	
	/*The byScalar method implements the multiplication of vectors and float type scalar.
	 * Note:Vector*Scalar=Vector 
	 * This method gets a new vector by multiplying each of the x, y, y, and a values from the original vector by the scalar in the method's argument. 
	 * 
	 * Put another way, 
	 * The method extends the vector in length without affecting the vector's skewness
	 */
	public Vector4f byScalar(float scale )
	{
		Vector4f newScale=new Vector4f(x,y,z,a);
		newScale.x=this.x*scale;
		newScale.y=this.y*scale;
		newScale.z=this.z*scale;
		newScale.a=this.a*scale;
		
		return newScale;
	}
	
	//implement returning the negative of a  Vector 
	
	/*The NegateVector method implements the inverse vector method of a vector. This method obtains a new vector by taking the negative values of each of the x, y, z and a values from the original vector. 
	 * Note: a+(-a)=0
	 * 
	 * Put another way,  
	 * The method yields an inverse vector with opposite direction and equal mode to the original vector. a + (-a) = 0 i.e.
	 */
	public Vector4f  NegateVector()
	{
		Vector4f newNegate=new Vector4f(x,y,z,a);
		newNegate.x=-this.x;
		newNegate.y=-this.y;
		newNegate.z=-this.z;
		newNegate.a=-this.a;
		 
		 return newNegate;
	}
	
	//implement getting the length of a Vector  
	
	/*The length method implements the method of finding the modulus size of a vector. 
	 * This method obtains the float number by taking the squared values of each of the x, y, z, and a values from the original vector and adding them together and then squaring them.
	 * The modulus of a vector can be used to calculate the distance between a point and a vector, etc.
	 */
	public float length()
	{
	    return (float) Math.sqrt(x*x + y*y + z*z+ a*a);
	}
	
	//Just to avoid confusion here is getting the Normal  of a Vector
	
	/*The Normal method implements the method of finding the normal vector of a vector. 
	 * A unit vector is a vector whose mode is equal to 1. 
	 * Since it is a non-zero vector, the unit vector has a definite direction. 
	 * A nonzero vector divided by its mode gives the desired unit vector.
	 */
	public Vector4f Normal()
	{
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f/ LengthOfTheVector); 
	} 
	
	//implement getting the dot product of Vector.Vector  
	
	/*The dot method implements a two-vector dot product algorithm. 
	 * Note: dot(a,b)=|a||b|cos
	 * This is the result of multiplying the x,y,z,a values of two vectors with each other and then adding them together. 
	 * The result of dot multiplication can be used to calculate the angle between the vectors
	 */

	public float dot(Vector4f v)
	{ 
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	// Implemented this for you to avoid confusion 
	// as we will not normally  be using 4 float vector
	
	/*The cross method implements a fork multiplication algorithm for two vectors. 
	 * Note: cross(a,b)=|a||b|sin
	 * The algorithm is as follows, and the result of the fork multiplication can also be used to calculate the angle between two vectors.
	 */
	public Vector4f cross(Vector4f v)  
	{ 
	    float u0 = (this.y*v.z - z*v.y);
	    float u1 = (z*v.x - x*v.z);
	    float u2 = (x*v.y - y*v.x);
	    float u3 = 0; //ignoring this for now  
	    return new Vector4f(u0,u1,u2,u3);
	}
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */