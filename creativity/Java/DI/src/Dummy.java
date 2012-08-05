import com.google.inject.Inject;

public class Dummy 
{

	@Inject private Printer _printer;
	private Person _person;
	
	@Inject
	public Dummy(Person person)
	{
		_person = person;
	}
}
