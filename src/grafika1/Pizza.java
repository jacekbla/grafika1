package grafika1;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class Pizza
{
	public static void main(String[] args)
	{
		System.out.println("Ring pattern synthesis");
		
		BufferedImage image;
		
		int x_res;
		int y_res;
		
		int x_c;
		int y_c;
		
		int black;
		int white;
		
		int i;
		int j;
		
		final double w = Math.PI/8;
		
		x_res = 400;
		y_res = 400;
		
		image = new BufferedImage(x_res, y_res, BufferedImage.TYPE_INT_RGB);
		
		black = int2RGB(0, 0, 0);
		white = int2RGB(255, 255, 255);
		
		x_c = x_res/2;
		y_c = y_res/2;
		
		for(i = 0; i < y_res; i++)
			for(j = 0; j < x_res; j++)
			{
				double d;
				int r;
				double alpha;
				
				d = Math.sqrt((i-y_c)*(i-y_c) + (j-x_c)*(j-x_c));
				
				alpha = Math.asin(Math.abs(x_c - j)/d);
				
				r = (int) (alpha / w);
				
				if(r % 2 == 0)
					if((j > x_c && i > y_c) || (j < x_c && i < y_c))
						image.setRGB(j, i, black);
					else
						image.setRGB(j, i, white);
				else
					if((j > x_c && i > y_c) || (j < x_c && i < y_c))
						image.setRGB(j, i, white);
					else
						image.setRGB(j, i, black);
			}
		try
		{
			ImageIO.write(image, "bmp", new File("pizza.bmp"));
			System.out.println("Ring image created successfully");
		}
		catch(IOException e)
		{
			System.out.println("The image cannot be stored");
		}
	}
	
	static int int2RGB( int red, int green, int blue)
	{
		red = red & 0x000000FF;
		green = green & 0x000000FF;
		blue = blue & 0x000000FF;
		return (red << 16) + (green << 8) + blue;
	}
}
