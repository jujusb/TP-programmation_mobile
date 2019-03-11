uniform mat4 u_MVPMatrix;		// A constant representing the combined model/view/projection matrix.      		       
uniform mat4 u_MVMatrix;		// A constant representing the combined model/view matrix.       		
		  			
attribute vec4 a_Position;		// Per-vertex position information we will pass in.   				
attribute vec2 a_TexCoordinate; // Per-vertex texture coordinate information we will pass in. 		
		  
varying vec2 v_TexCoordinate;   // This will be passed into the fragment shader.

uniform vec4 u_Color;
varying vec4 v_Color;

// The entry point for our vertex shader.  
void main()                                                 	
{
    v_Color = u_Color;

	// Transform the vertex into eye space. 	

	// Pass through the texture coordinate.
	v_TexCoordinate = a_TexCoordinate;                                      
	
	gl_Position = u_MVPMatrix * a_Position;                       		  
}                                                          
