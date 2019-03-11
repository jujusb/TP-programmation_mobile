uniform mat4 u_MVPMatrix;		// A constant representing the combined model/view/projection matrix.      		       
uniform mat4 u_MVMatrix;		// A constant representing the combined model/view matrix.

uniform vec4 u_Color;
varying vec4 v_Color;

attribute vec4 a_Position;		// Per-vertex position information we will pass in.

// The entry point for our vertex shader.  
void main()                                                 	
{
    v_Color = u_Color;
	gl_Position = u_MVPMatrix * a_Position;                       		  
}                                                          
