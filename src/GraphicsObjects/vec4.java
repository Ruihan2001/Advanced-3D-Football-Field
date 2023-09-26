package GraphicsObjects;

public class vec4 {
	public static int LENGTH = 4;
	
	public float x = 0;
	public float y = 0;
	public float z = 0;
	public float w = 0;

	public vec4() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		w = 0.0f;
	}

	public vec4(float scalar) {
		this.x = scalar;
		this.y = scalar;
		this.z = scalar;
		this.w = scalar;
	}

	public vec4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public vec4(vec3 v, float w) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = w;
	}
	
	public vec4(vec4 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
	}
	
	// set Vector values
	public void set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	// implement Vector plus a Vector
	public vec4 add(vec4 v) {
		return new vec4(x + v.x, y + v.y, z + v.z, w + v.w);
	}

	// implement Vector minus a Vector
	public vec4 sub(vec4 v) {
		return new vec4(x - v.x, y - v.y, z - v.z, w - v.w);
	}

	// implement Vector plus a Vector
	public vec4 mul(vec4 v) {
		return new vec4(x * v.x, y * v.y, z * v.z, w * v.w);
	}

	// Implement a Vector + Scalar
	public vec4 add(float scalar) {
		return new vec4(x + scalar, y + scalar, z + scalar, w + scalar);
	}

	// Implement a Vector - Scalar
	public vec4 sub(float scalar) {
		return new vec4(x - scalar, y - scalar, z - scalar, w - scalar);
	}

	// Implement a Vector * Scalar
	public vec4 mul(float scalar) {
		return new vec4(x * scalar, y * scalar, z * scalar, w * scalar);
	}

	// implement returning the negative of a Vector
	public vec4 negate() {
		return new vec4(-x, -y, -z, -w);
	}

	// implement getting the dot product of Vector.Vector
	public float dot(vec4 v) {
		return x * v.x + y * v.y + z * v.z + w * v.w;
	}

	// implement getting the length of a Vector
	public float length() {
		return (float) Math.sqrt(dot(this));
	}

	// Just to avoid confusion here is getting the Normal of a Vector
	public vec4 normal() {
		float LengthOfTheVector = this.length();
		return this.mul(1.0f / LengthOfTheVector);
	}

	// Implemented this for you to avoid confusion
	public vec4 cross(vec4 v) {
		return new vec4(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x, w);
	}
}
