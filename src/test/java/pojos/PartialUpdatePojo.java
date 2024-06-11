package pojos;

public class PartialUpdatePojo{
	private String firstname;
	private String lastname;

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"PartialUpdatePojo{" + 
			"firstname = '" + firstname + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}
