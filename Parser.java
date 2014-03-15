import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;


public class Parser
{
	Lexer lexer;
	
	LinkedList<Token> tokens;
	Token current;
	int size;
	int currentIndex;
	
	ArrayList errors;

	public Parser(Lexer l)
	{
		lexer = l;
		
		tokens = new LinkedList<Token>();
		current = null;
		
		currentIndex = 0;
		
		errors = new ArrayList<String>();
		
		Token token;
		while((token = lexer.getToken()) != null)
		{
			tokens.add(token);
		}
		
		System.out.println(tokens);
		
		current = tokens.getFirst();
		size = tokens.size();
	}
	
	public void error(String err)
	{
		errors.add(err);
	}
	
	public void parse()
	{
		int size = tokens.size();
		int currIndex = 0;
		Token current;
		
		
		boolean completed = false;
		
		
		statementList();//recursive descent parsing starts here
		
	    System.out.println("Parsing Complete");
	    
	    if(errors.size() > 0)
	    {
			System.out.println("Errors Found");
			Iterator<Object> it = errors.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}
		}
		
	}
	
	private void statementList()
	{
		while(currentIndex < size-1)
		{
			statement();
		}
	}
	
	private void statement()
	{
		switch(current)
		{
			case WHILE:
				nextToken();
				whileStatement();
				break;
			case READ:
				nextToken();
				ioList();
				if(current == Token.SEMI)
				{
					nextToken();
				}
				else
				{
					error("statement read: expected ';'");
					nextToken();
				}
				break;
			case WRITE:
				nextToken();
				ioList();
				if(current == Token.SEMI)
				{
					nextToken();
				}
				else
				{
					error("statement write: expected ';'");
					nextToken();
				}
				break;
			case IF:
				nextToken();
				ifStatement();
				break;
			default:
				error("statement: invalid syntax");
				nextToken();
				break;
			}
	}
	
	private void whileStatement()
	{
		booleanExpression();
		if(current == Token.DO)
		{
			nextToken();
			statementBody();
		}
		else
		{
			error("whileStatement: expected 'do'");
			nextToken();
		}
	}
	
	private void booleanExpression()
	{
		if(current == Token.ID)
		{
			nextToken();
			operators();
			value();
		}
		else
		{
			error("booleanExpression: invalid syntax");
			nextToken();
		}
	}
	
	private void operators()
	{
		switch(current)
		{
			case LESS:
			case GREAT:
			case EQL:
			case LEQL:
			case GEQL:
			case DIAM:
				nextToken();
				break;
			default:
				error("operators: unexpected operator");
				nextToken();
				break;
		}
	}
	
	private void value()
	{
		switch(current)
		{
			case ID:
			case INT:
				nextToken();
				break;
			default:
				error("value: unexpected value");
				nextToken();
		}		
	}
	
	private void statementBody()
	{
		if(current == Token.BEGIN)
		{
			nextToken();
			statementList();
		}
		else
		{
			error("statementBody: missing statement beginning");
			nextToken();
		}
		
		if(current == Token.END)
		{
			nextToken();
		}
		else
		{
			error("statementBody: missing statement end");
			nextToken();
		}	
	}
	
	private void ioList()
	{
		if(current == Token.LOPEN)
		{
			nextToken();
		}
		else
		{
			error("ioList: missing Left Paren");
			nextToken();
		}
		
		
		if(current == Token.ID)
		{
			nextToken();
		}
		else
		{
			error("ioList: missing input");
			nextToken();
		}
		
		while(current == Token.COMMA)
		{
			nextToken();
			if(current == Token.ID)
			{
				nextToken();
			}
			else
			{
				error("ioList: missing input after comma");
				nextToken();
			}
		}
		
		if(current == Token.ROPEN)
		{
			nextToken();
		}
		else
		{
			error("ioList: missing Right Paren");
			nextToken();
		}
		
	}
	
	private void ifStatement()
	{
		booleanExpression();
		if(current == Token.THEN)
		{
			nextToken();
			statementBody();
		}
		else
		{
			error("ifStatement: expected 'then'");
			nextToken();
		}
	}

	private void nextToken()
	{
		if(currentIndex < size-1)
		{
			currentIndex++;
			current = tokens.get(currentIndex);
		}
	}


}
