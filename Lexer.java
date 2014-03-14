import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Lexer
{
	Scanner sc;
	
	public Lexer(String s)
	{
		sc = null;
		
		try 
		{
			sc = new Scanner(new File(s));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}

	public Token getToken()
	{
		if(sc.hasNext())
		{
			int val = sc.nextInt();
			for (Token t : Token.values())
					if(t.value() == val)
					{
						return t;
					}
			
			return Token.error;
		}
		
		return null;
	}
	

}
