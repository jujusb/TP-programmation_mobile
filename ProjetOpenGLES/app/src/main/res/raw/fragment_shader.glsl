precision mediump float;       	// Set the default precision to medium. We don't need as high of a 
								// precision in the fragment shader.
uniform sampler2D u_Texture;    // The input texture.
    								// triangle per fragment.
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
varying vec4 v_Color;

// The entry point for our fragment shader.
void main()                    		
{                              

    gl_FragColor = (v_Color * texture2D(u_Texture, v_TexCoordinate));
}


