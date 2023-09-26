package GraphicsObjects;

public class vec3 {
	public static int LENGTH = 3;
	
	public float x = 0;
	public float y = 0;
	public float z = 0;

	public vec3() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}

	public vec3(float scalar) {
		this.x = scalar;
		this.y = scalar;
		this.z = scalar;
	}

	public vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public vec3(vec3 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	// set Vector values
	public void set(float scalar)
	{
		this.x = scalar;
		this.y = scalar;
		this.z = scalar;
	}
	
	// set Vector values
	public void set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// implement Vector plus a Vector
	public vec3 add(vec3 v) {
		return new vec3(x + v.x, y + v.y, z + v.z);
	}

	// implement Vector minus a Vector
	public vec3 sub(vec3 v) {
		return new vec3(x - v.x, y - v.y, z - v.z);
	}

	// implement Vector plus a Vector
	public vec3 mul(vec3 v) {
		return new vec3(x * v.x, y * v.y, z * v.z);
	}

	// Implement a Vector + Scalar
	public vec3 add(float scalar) {
		return new vec3(x + scalar, y + scalar, z + scalar);
	}

	// Implement a Vector - Scalar
	public vec3 sub(float scalar) {
		return new vec3(x - scalar, y - scalar, z - scalar);
	}

	// Implement a Vector * Scalar
	public vec3 mul(float scalar) {
		return new vec3(x * scalar, y * scalar, z * scalar);
	}

	// implement returning the negative of a Vector
	public vec3 negate() {
		return new vec3(-x, -y, -z);
	}

	// implement getting the dot product of Vector.Vector
	public float dot(vec3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	// implement getting the length of a Vector
	public float length() {
		return (float) Math.sqrt(dot(this));
	}

	// Just to avoid confusion here is getting the Normal of a Vector
	public vec3 normal() {
		float LengthOfTheVector = this.length();
		return this.mul(1.0f / LengthOfTheVector);
	}

	// Implemented this for you to avoid confusion
	public vec3 cross(vec3 v) {
		return new vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	public vec3 rotateX(float angle)
	{
        float sine = (float)Math.sin(angle);
        float cosine = (float)Math.cos(angle);
        return new vec3(
        	x,
            y * cosine - z * sine,
            z * cosine + y * sine);
	}
	
	public vec3 rotateY(float angle)
	{
        float sine = (float)Math.sin(angle);
        float cosine = (float)Math.cos(angle);
        return new vec3(
            x * cosine - z * sine,
            y,
            z * cosine + x * sine);
	}
	
	public vec3 rotateZ(float angle)
	{
        float sine = (float)Math.sin(angle);
        float cosine = (float)Math.cos(angle);
        return new vec3(
            x * cosine - y * sine,
            y * cosine + x * sine,
            z);
	}
}
