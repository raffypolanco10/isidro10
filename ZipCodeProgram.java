import java.util.*;

public class ZipCodeProgram  
{
    // draw a pattern of five bars when given a String of 1's and 0's
    public static void drawBars(String pttrns, double x)
    {
        // check each character of a String to see whether it's a 1 or a 0,
        // then draw a bar of the corresponding height
        for (int i = 0; i < 5; i++)
        {
            if (pttrns.charAt(i) == '0')
                StdDraw.line(x, 0, x, 0.50);    // draw half-height bar
            if (pttrns.charAt(i) == '1')
                StdDraw.line(x, 0, x, 1);       // draw full-height bar
            x += 0.2;   // update x after each bar
        }
    }
    
    // compute checksum of a given zip code
    public static int checksum(int[] code)
    {
        int sum = 0;
        for (int i = 0; i < code.length; i++)
        {
            if (code[i] != -1)
                sum += code[i];
        }
        return sum % 10;
        }
    
    // read in a zip code, then draw its bar code
    public static void main(String args[])
    {
        String zip = args[0];
        
        // define bar patterns for each digit 0-9; 0 = half-height bar, 1 = full-height bar
        String[] patterns = {"11000", "00011", "00101", "00110", "01001",
                             "01010", "01100", "10001", "10010", "10100"};
        
        // declare array to hold  the digits of the zip code
        int[] number;
        
        // check length of the zip code; terminate program if input is neither 5- nor 9-digits long
        if (zip.length() == 5 || zip.length() == 10)
            number = new int[zip.length()];
        else
        {
            System.out.println("Invalid code."); 
            return;
        }
        
        // convert digit String into integers
        for (int i = 0; i < zip.length(); i++)
        {
            if (i == 5)
                number[i] = -1;
            else
            number[i] = Integer.parseInt(""+zip.charAt(i));
        }
                
        // prepare drawing canvas
        StdDraw.setXscale(-1, 15);
        StdDraw.setYscale(-1, 2);
        StdDraw.setPenRadius(0.005);
        
        // set initial position of x
        double x = 0.0;
        
        // draw left guard and update x for spacing
        StdDraw.line(x, 0, x, 1);
        x += 0.4;
        
        // draw bar pattern for each digit of the zip code
        for (int i = 0; i < number.length; i++)
        {
            if (number[i] != -1)
            {
                drawBars(patterns[number[i]], x);
                x += 1.2; // update x after each bar pattern
            }
        }
        
        // Draw bar pattern for checksum
        drawBars(patterns[checksum(number)], x);
    }
}