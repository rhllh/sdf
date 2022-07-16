package vttp2022.day6b.workshop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        BoxString b = new BoxString();
        b.setContent("This is a string");
        System.out.println(b.getContent());

        BoxString b2 = new BoxString("This is not a string");
        System.out.println(b2.getContent());

        Box b3 = new Box();
        b3.setContent(2333);
        System.out.println(b3.getContent());

        Box b4 = new Box();
        b4.setContent(false);
        System.out.println(b4.getContent());

        b4.setContent(new BoxString());
        System.out.println(b4.getContent());    // memory 

        BoxG<Integer> b5Int = new BoxG<>();
        //b5Int.setContent("setting string");       // not allowed
        b5Int.setContent(123);
        System.out.println(b5Int.getContent());

        BoxG<Boolean> b5Bool = new BoxG<>();
        b5Bool.setContent(true);
        System.out.println(b5Bool.getContent());

        //BoxG<Double> b5Double = new BoxG<>();
        //b5Double.setContent(new Double(b5Int.getContent());     // to fix
        
        String[] lines = new String[10];
        lines[0] = "hello";
        System.out.println(lines);

        //float[][] mat = new float[3][3];
    }
}
