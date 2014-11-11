/**
 * Implements function for print debug message.
 * Change return value isEnabled() if you want disable or enable print.  
 */
package dotsboxes;


public class Debug
{   //@brief On/off print debug message.
    public static boolean isEnabled(){
        return true;
    }
    //@brief Print debug message.
    public static void log(Object o)
    {
    	if( isEnabled() )
    		System.out.println(o.toString());
    }
}