
import java.io.*;


public class Client3 {
	public static void display(Writer out, Person person) throws IOException {
		out.write(person.toString());
	}
}
