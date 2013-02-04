package gr.manousos.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ActivateBean extends RegisterBean {
	public ActivateBean(){
		super();
	}
	
	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}	
	
}
