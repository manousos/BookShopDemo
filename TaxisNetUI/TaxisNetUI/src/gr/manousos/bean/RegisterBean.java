package gr.manousos.bean;

/*
 import gr.manousos.service.Contact;
 import gr.manousos.service.Taxpayer;
 import gr.manousos.service.User;
 import gr.manousos.service.UserSrv;
 import gr.manousos.service.UserSrvImplService;
 */
import gr.manousos.model.Contact;
import gr.manousos.model.Taxpayer;
import gr.manousos.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@ManagedBean
@SessionScoped
public class RegisterBean extends LoginBean {

	// private static final Log log = LogFactory.getLog(RegisterBean.class);

	private String repeatPassword;
	private String afm;
	private String lastName;
	private String firstName;
	private String fatherName;
	private String email;
	private String phone;
	private String cell;
	private String fax;
	private String error;

	// private String

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void validatePassword(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		// Get the first password from the attribute.
		UIInput passwordComponent = (UIInput) component.getAttributes().get(
				"password");

		// Get the value from the UIInput component.
		String password = (String) passwordComponent.getValue();

		// Get the value entered in the second string from the component
		// parameter passed to the method.
		String confirm = (String) value;

		// Compare both fields.
		if (!password.equals(confirm)) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"O Κωδικός δεν είναι ίδιος!!!", null));
		}
	}

	public String submit() {
		ClientConfig conf = new DefaultClientConfig();
		Client client = Client.create(conf);
		/*
		 * UserSrvImplService service = new UserSrvImplService(); UserSrv client
		 * = service.getUserSrvImplPort();
		 */
		User u = new User();
		u.setUserName(super.getUserName());
		u.setPassword(super.getPassword());
		u.setActive(0);
		u.setOtp("");

		Contact con = new Contact();
		con.setPhone(this.phone);
		con.setFax(this.fax);
		con.setCell(this.cell);
		con.setEmail(this.email);

		Taxpayer taxPayer = new Taxpayer();
		taxPayer.setAfm(this.afm);
		taxPayer.setContact(con);
		taxPayer.setFatherName(this.fatherName);
		taxPayer.setFname(this.firstName);
		taxPayer.setLname(this.lastName);
		taxPayer.setUser(u);
		
		try {
			Taxpayer newUser = null;
			WebResource r = client
					.resource("http://localhost:8098/TaxisNet/rest/");

			newUser = r.path("UserService/Register").accept("application/json")
					.type("application/json").post(Taxpayer.class, taxPayer);
			if (newUser.getId() > 0)
				return ("SuccessPage");
		} catch (Exception ex) {
			System.err.println(ex);
		}
		/*
		 * try { Taxpayer newUser = client.register(taxPayer); if
		 * (newUser.getId() > 0) return ("SuccessPage"); } catch (Exception ex)
		 * { this.setError(ex.toString()); //
		 * System.err.println("submit Register Error= " + ex.toString()); }
		 */
		return ("ErrorPage");
	}
}
