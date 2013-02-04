package gr.manousos.test;

import gr.manousos.bean.E2Bean;
import gr.manousos.bean.E2Bean.LeasesProperties;
import gr.manousos.bean.E2Bean.OtherEstates;
import gr.manousos.bean.E2Bean.PartialEstates;
import gr.manousos.model.E2;
import gr.manousos.model.E2Id;
import gr.manousos.model.E2coOwner;
import gr.manousos.model.E2estate;
import gr.manousos.model.E2otherEstate;
import gr.manousos.model.Taxpayer;

import java.net.URI;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestService {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * UserSrvImplService userService = new UserSrvImplService(); UserSrv
		 * userClient = userService.getUserSrvImplPort(); Taxpayer taxpayer =
		 * userClient.getTaxPayerById(9);
		 */
		ClientConfig conf = new DefaultClientConfig();
		Client client = Client.create(conf);

		Taxpayer taxpayer = null;
		try {
			WebResource restSrv = client.resource(new URI(
					"http://localhost:8098/TaxisNet/rest/"));
			taxpayer = (Taxpayer) restSrv.path("UserService/TaxPayer/9")
					.accept(MediaType.APPLICATION_JSON).get(Taxpayer.class);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println(ex.getStackTrace());
		}
		if (taxpayer != null)
			System.out.println(taxpayer.getAfm());

		// E2 primary KEY
		E2Id key = new E2Id();
		key.setTaxierId(9);
		key.setYear(Calendar.getInstance().get(Calendar.YEAR));

		// master table !
		E2 e2 = new E2();
		e2.setId(key);
		e2.setIsComplete(0);

		E2estate e2estateObj = new E2estate();
		e2estateObj.setArea(10);
		e2estateObj.setFrom(2);
		e2estateObj.setLocation("location test");
		e2estateObj.setMonthlyRental(100);
		e2estateObj.setPosition("position from test");
		e2estateObj.setRersentCoOwner(50);
		e2estateObj.setTenantAfm("0000000000");
		e2estateObj.setTenantFullName("tenantFullName from test");
		e2estateObj.setTo(10);
		e2estateObj.setUsage("usage from test");

		E2coOwner e2coOwners = new E2coOwner();
		e2coOwners.setAddress("address from test");
		e2coOwners.setAfm("0000000");
		e2coOwners.setFullName("fullName from test");
		e2coOwners.setPercent(50);
		e2coOwners.setRent(new Float(100f));
		

		E2otherEstate otherEst = new E2otherEstate();
		otherEst.setArea(111);
		otherEst.setLocation("o location from test");
		otherEst.setPosition("o position from test");
		otherEst.setTitle("o title from test");
		otherEst.setUsage("o usage from test");

		Set<E2estate> listOfE2estates = new HashSet<E2estate>();
		Set<E2coOwner> listOfE2coOwner = new HashSet<E2coOwner>();
		Set<E2otherEstate> listOfOtherEstates = new HashSet<E2otherEstate>();

		listOfOtherEstates.add(otherEst);
		listOfE2coOwner.add(e2coOwners);
		listOfE2estates.add(e2estateObj);

		e2.setE2otherEstates(listOfOtherEstates);
		e2estateObj.setE2coOwners(listOfE2coOwner);
		e2.setE2estates(listOfE2estates);

		String result = "";
		try {

			WebResource r = client
					.resource("http://localhost:8098/TaxisNet/rest/");

			result = r.path("DocumentService/submitE2")
					.accept("application/json").type("application/json")
					.post(String.class, e2);
			// if (newUser.getId() > 0)
			// return ("SuccessPage");
		} catch (Exception ex) {
			System.err.println(ex);
			System.err.println(ex.getCause());
		}
		System.out.println(result);
	}
}
