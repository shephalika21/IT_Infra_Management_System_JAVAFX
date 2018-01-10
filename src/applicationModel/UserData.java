package applicationModel;

public class UserData {
	
	private String userid;
	private String firstname,lastname,email,role, phno;
	
	//created constructor and getting and setter to access the variables
	
	public UserData(String empid,String firstname,String lastname,String emailid,String phone, String role)
	{
		super();
		this.userid=empid;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=emailid;
		this.phno=phone;
		this.role=role;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String empid) {
		this.userid= empid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailid) {
		this.email = emailid;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phoneno) {
		this.phno = phoneno;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
