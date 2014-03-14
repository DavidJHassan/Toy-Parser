
public enum Token
{ 	
	error(-1),EOF(0), SEMI(1),WHILE(2),READ(3),WRITE(4),IF(5),DO(6),ID(7),INT(8),LESS(9),GREAT(10),
	EQL(11), LEQL(12),GEQL(13),DIAM(14),THEN(15),BEGIN(16),END(17),LOPEN(18),ROPEN(19),COMMA(20);
	
	private int value;
	
	private Token(int value)
	{
		this.value = value;
	}
	
	public int value()
	{
		return this.value;
	}
};


/*
public class Token
{
	  public static final int EPS = 0;
	  public static final int SEMI = 1;
	  public static final int WHILE = 2;
	  public static final int READ = 3;
	  public static final int WRITE = 4;
	  public static final int IF = 5;
	  public static final int DO = 6;
	  public static final int ID = 7;
	  public static final int INT = 8;
	  public static final int LESS = 9;
	  public static final int GREAT = 10;
	  public static final int EQL = 11;
	  public static final int LEQL = 12;
	  public static final int GEQL = 13;
	  public static final int DIAM = 14;
	  public static final int THEN = 15;
	  public static final int BEGIN = 16;
	  public static final int END = 17;
	  public static final int LOPEN = 18;
	  public static final int ROPEN = 19;
	  public static final int COMMA = 20;

	 public final int token;
	 public final String sequence;

	  public Token(int token, String sequence)
	  {
		super();
		this.token = token;
		this.sequence = sequence;
	  }
  }
  */
