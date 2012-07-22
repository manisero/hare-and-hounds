import com.google.inject.Inject;

public class Person 
{
	
	private String _name;
	
	private String _surname;
	
	@Inject public Person(String name, String surname)
	{
		_name = name;
		_surname = surname;
	}
	
}
