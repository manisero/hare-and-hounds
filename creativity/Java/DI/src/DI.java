import com.google.inject.Guice;
import com.google.inject.Injector;


public class DI 
{

	public static void main(String[] args) 
	{
		Injector injector = Guice.createInjector();
		
		Dummy dummy = injector.getInstance(Dummy.class);
	}

}
