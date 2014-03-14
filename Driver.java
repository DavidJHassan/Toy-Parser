
public class Driver
{
	
		public static void main(String args[])
		{
			Lexer l = new Lexer(args[0]);
			Parser p = new Parser(l);
			p.parse();
		}
		
}
