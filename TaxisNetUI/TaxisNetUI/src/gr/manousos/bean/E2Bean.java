package gr.manousos.bean;

import gr.manousos.model.Taxpayer;
import gr.manousos.model.E2otherEstate;
import gr.manousos.model.E2coOwner;
import gr.manousos.model.E2estate;
import gr.manousos.model.E2;
import gr.manousos.model.E2Id;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@ManagedBean
@SessionScoped
public class E2Bean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ArrayList<PartialEstates> partialEstateLst = new ArrayList<E2Bean.PartialEstates>();
	private static ArrayList<OtherEstates> otherEstateLst = new ArrayList<E2Bean.OtherEstates>();
	private static ArrayList<LeasesProperties> rnEstateList = new ArrayList<E2Bean.LeasesProperties>();

	private boolean dtPartialEstLstVisible = false;// partialEstateLst.size() >
													// 0 ? true : false;
	private boolean dtOtherEstLstVisible = false;
	private boolean dtEstLstVisible = false;
	private boolean pnPartialEstates = true;// partialEstateLst.size() > 0 ?
											// true : false;
	private int taxPayerID;
	private String error;
	private String location;
	private String point;
	private String usage;
	private float area;
	private float rent;
	private String coFullName;
	private String coAFM;
	private String coAddress;
	private float cOwnPesr;

	private String location2;
	private String point2;
	private String usage2;
	private float area2;
	private String titleProp;

	private String rentierFullName;
	private String rentierAFM;
	private String rentierFatherName;
	private String rnLocation;
	private String rnPoint;
	private String rnUsage;
	private float rnArea;
	private String rnFullName;
	private String rnAFM;
	private int fromMonth;
	private int toMonth;
	private int totalMonth;
	private float rnRent;
	private float rnPesr;
	private float revFreeBuilding;
	private float revFreeOffice;
	private float revFreeLand;
	private float revPropHotel;
	private float revPropOffice;

	/**
	 * 
	 */
	public E2Bean() {
		Taxpayer taxpayer = null;
		try {

			ClientConfig conf = new DefaultClientConfig();
			Client client = Client.create(conf);
			WebResource restSrv = client.resource(new URI(
					"http://localhost:8098/TaxisNet/rest/"));
			taxpayer = (Taxpayer) restSrv.path("UserService/TaxPayer/9")
					.accept(MediaType.APPLICATION_JSON).get(Taxpayer.class);

			this.rentierAFM = taxpayer.getAfm();
			this.setRentierFatherName(taxpayer.getFatherName());
			this.setRentierFullName(taxpayer.getFname() + " "
					+ taxpayer.getLname());
			this.setTaxPayerID(taxpayer.getId());
		} catch (Exception ex) {
			this.error = "Exeption: " + ex.toString() + "<br /> Stack Trace "
					+ ex.getStackTrace() + "<br /> Caouse " + ex.getCause();
		}

	}

	public ArrayList<PartialEstates> getPartialEstateLst() {
		return partialEstateLst;
	}

	public void setPartialEstateLst(ArrayList<PartialEstates> partialEstateLst) {
		E2Bean.partialEstateLst = partialEstateLst;
	}

	public ArrayList<OtherEstates> getOtherEstateLst() {
		return otherEstateLst;
	}

	public void setOtherEstateLst(ArrayList<OtherEstates> otherEstateLst) {
		E2Bean.otherEstateLst = otherEstateLst;
	}

	public ArrayList<LeasesProperties> getRnEstateList() {
		return rnEstateList;
	}

	public void setRnEstateList(ArrayList<LeasesProperties> rnEstateList) {
		E2Bean.rnEstateList = rnEstateList;
	}

	public boolean isDtPartialEstLstVisible() {
		return dtPartialEstLstVisible;
	}

	public void setDtPartialEstLstVisible(boolean dtPartialEstLstVisible) {
		this.dtPartialEstLstVisible = dtPartialEstLstVisible;
	}

	public boolean isDtOtherEstLstVisible() {
		return dtOtherEstLstVisible;
	}

	public void setDtOtherEstLstVisible(boolean dtOtherEstLstVisible) {
		this.dtOtherEstLstVisible = dtOtherEstLstVisible;
	}

	public boolean isDtEstLstVisible() {
		return dtEstLstVisible;
	}

	public void setDtEstLstVisible(boolean dtEstLstVisible) {
		this.dtEstLstVisible = dtEstLstVisible;
	}

	public boolean isPnPartialEstates() {
		return pnPartialEstates;
	}

	public void setPnPartialEstates(boolean pnPartialEstates) {
		this.pnPartialEstates = pnPartialEstates;
	}

	public int getTaxPayerID() {
		return taxPayerID;
	}

	public void setTaxPayerID(int taxPayerID) {
		this.taxPayerID = taxPayerID;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getRent() {
		return rent;
	}

	public void setRent(float rent) {
		this.rent = rent;
	}

	public String getCoFullName() {
		return coFullName;
	}

	public void setCoFullName(String coFullName) {
		this.coFullName = coFullName;
	}

	public String getCoAFM() {
		return coAFM;
	}

	public void setCoAFM(String coAFM) {
		this.coAFM = coAFM;
	}

	public String getCoAddress() {
		return coAddress;
	}

	public void setCoAddress(String coAddress) {
		this.coAddress = coAddress;
	}

	public float getcOwnPesr() {
		return cOwnPesr;
	}

	public void setcOwnPesr(float cOwnPesr) {
		this.cOwnPesr = cOwnPesr;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getPoint2() {
		return point2;
	}

	public void setPoint2(String point2) {
		this.point2 = point2;
	}

	public String getUsage2() {
		return usage2;
	}

	public void setUsage2(String usage2) {
		this.usage2 = usage2;
	}

	public float getArea2() {
		return area2;
	}

	public void setArea2(float area2) {
		this.area2 = area2;
	}

	public String getTitleProp() {
		return titleProp;
	}

	public void setTitleProp(String titleProp) {
		this.titleProp = titleProp;
	}

	public String getRentierFullName() {
		return rentierFullName;
	}

	public void setRentierFullName(String rentierFullName) {
		this.rentierFullName = rentierFullName;
	}

	public String getRentierAFM() {
		return rentierAFM;
	}

	public void setRentierAFM(String rentierAFM) {
		this.rentierAFM = rentierAFM;
	}

	public String getRentierFatherName() {
		return rentierFatherName;
	}

	public void setRentierFatherName(String rentierFatherName) {
		this.rentierFatherName = rentierFatherName;
	}

	public String getRnLocation() {
		return rnLocation;
	}

	public void setRnLocation(String rnLocation) {
		this.rnLocation = rnLocation;
	}

	public String getRnPoint() {
		return rnPoint;
	}

	public void setRnPoint(String rnPoint) {
		this.rnPoint = rnPoint;
	}

	public String getRnUsage() {
		return rnUsage;
	}

	public void setRnUsage(String rnUsage) {
		this.rnUsage = rnUsage;
	}

	public float getRnArea() {
		return rnArea;
	}

	public void setRnArea(float rnArea) {
		this.rnArea = rnArea;
	}

	public String getRnFullName() {
		return rnFullName;
	}

	public void setRnFullName(String rnFullName) {
		this.rnFullName = rnFullName;
	}

	public String getRnAFM() {
		return rnAFM;
	}

	public void setRnAFM(String rnAFM) {
		this.rnAFM = rnAFM;
	}

	public int getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(int fromMonth) {
		this.fromMonth = fromMonth;
	}

	public int getToMonth() {
		return toMonth;
	}

	public void setToMonth(int toMonth) {
		this.toMonth = toMonth;
	}

	public int getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public float getRnRent() {
		return rnRent;
	}

	public void setRnRent(float rnRent) {
		this.rnRent = rnRent;
	}

	public float getRnPesr() {
		return rnPesr;
	}

	public void setRnPesr(float rnPesr) {
		this.rnPesr = rnPesr;
	}

	public float getRevFreeBuilding() {
		return revFreeBuilding;
	}

	public void setRevFreeBuilding(float revFreeBuilding) {
		this.revFreeBuilding = revFreeBuilding;
	}

	public float getRevFreeOffice() {
		return revFreeOffice;
	}

	public void setRevFreeOffice(float revFreeOffice) {
		this.revFreeOffice = revFreeOffice;
	}

	public float getRevFreeLand() {
		return revFreeLand;
	}

	public void setRevFreeLand(float revFreeLand) {
		this.revFreeLand = revFreeLand;
	}

	public float getRevPropHotel() {
		return revPropHotel;
	}

	public void setRevPropHotel(float revPropHotel) {
		this.revPropHotel = revPropHotel;
	}

	public float getRevPropOffice() {
		return revPropOffice;
	}

	public void setRevPropOffice(float revPropOffice) {
		this.revPropOffice = revPropOffice;
	}

	/*
	 * propList estateList rnEstateList LeasesProperties
	 */
	public String saveE2() {
		// TODO: Is Valid Form ?
		// 1 estate with coOwn ->1,,* PartialEstates

		// TODO: Get Taxpayer for session...

		Set<E2estate> listOfE2estates = new HashSet<E2estate>();
		Set<E2coOwner> listOfE2coOwner = new HashSet<E2coOwner>();
		Set<E2otherEstate> listOfOtherEstates = new HashSet<E2otherEstate>();

		// E2 primary KEY
		E2Id key = new E2Id();
		key.setTaxierId(this.getTaxPayerID());
		key.setYear(Calendar.getInstance().get(Calendar.YEAR));

		// master table !
		E2 e2 = new E2();
		e2.setId(key);
		e2.setIsComplete(0);
		int virtualEstateID = 0;
		// E2Estate db table
		E2estate estate = null;
		for (LeasesProperties item : E2Bean.rnEstateList) {
			estate = new E2estate();
			estate.setArea(item.getArea());
			estate.setLocation(item.getLocation());
			estate.setMonthlyRental(item.getRent());
			estate.setPosition(item.getPoint());
			estate.setRersentCoOwner(item.getRnPesr());
			estate.setRevenueFreeHome(item.getRevFreeBuilding());
			estate.setRevenueFreeOffice(item.getRevFreeOffice());
			estate.setRevenuePrivateHotel(item.getRevPropHotel());
			estate.setRevenuePrivateOffice(item.getRevPropOffice());
			estate.setTenantAfm(item.getRnAFM());
			estate.setTenantFullName(item.getRnFullName());
			estate.setUsage(item.getUsage());
			estate.setFrom(item.getFromMonth());
			estate.setTo(item.getToMonth());
			// TODO: care it...maybe it is a bug !!
			E2coOwner coOwner = null;
			virtualEstateID++;
			if (estate.getRersentCoOwner() != 100) {
				for (PartialEstates pEstate : E2Bean.partialEstateLst) {
					coOwner = new E2coOwner();
					if (pEstate.location.trim().equals(
							estate.getLocation().trim())
							&& pEstate.point.trim().equals(
									estate.getPosition().trim())
							&& pEstate.usage.trim().equals(
									estate.getUsage().trim())
							&& pEstate.area == estate.getArea()) {

						// Transient fields
						coOwner.setLocation(pEstate.location.trim());
						coOwner.setPosition(pEstate.point.trim());
						coOwner.setUsage(pEstate.usage.trim());
						coOwner.setArea(pEstate.area);
						// END Transient fields
						coOwner.setAddress(pEstate.getCoAddress());
						coOwner.setAfm(pEstate.getCoAFM());
						coOwner.setFullName(pEstate.getCoFullName());
						coOwner.setPercent(pEstate.getcOwnPers());
						coOwner.setRent(pEstate.getCoRent());
						// coOwner.setE2estate(estate);
						listOfE2coOwner.add(coOwner);

						// System.err.println(partialEstateLst.remove(pEstate));
					}
				}
			}// estate.setE2coOwners(listOfE2coOwner);
			listOfE2estates.add(estate);
		}
		// E2 ee = new E2(key, null, 0, e2otherEstates, e2estates)
		E2otherEstate oEstate = null;
		for (OtherEstates otherEstItem : E2Bean.otherEstateLst) {
			oEstate = new E2otherEstate();
			oEstate.setArea(otherEstItem.getArea());
			oEstate.setLocation(otherEstItem.getLocation());
			oEstate.setPosition(otherEstItem.getPoint());
			oEstate.setTitle(otherEstItem.getTitleProp());
			oEstate.setUsage(otherEstItem.getUsage());

			listOfOtherEstates.add(oEstate);
		}

		e2.setE2otherEstates(listOfOtherEstates);
		if (estate != null)
			estate.setE2coOwners(listOfE2coOwner);
		e2.setE2estates(listOfE2estates);

		String result = "";

		ClientConfig conf = new DefaultClientConfig();
		Client client = Client.create(conf);

		try {
			WebResource r = client
					.resource("http://localhost:8098/TaxisNet/rest/");

			result = r.path("DocumentService/submitE2")
					.accept("application/json").type("application/json")
					.post(String.class, e2);

			this.error = result;
			if (result == "E2 Saved !!")
				return ("SuccessPage");
		} catch (Exception ex) {
			this.error = ex.toString();
			return "";
		}

		return "";
	}

	public String addPartialEstateAction() {
		this.dtPartialEstLstVisible = true;
		partialEstateLst.add(new PartialEstates(this.location, this.point,
				this.usage, this.area, this.coFullName, this.coAFM,
				this.coAddress, this.cOwnPesr, this.rent));

		return "";
	}

	public String addOtherEstatesAction() {

		this.dtOtherEstLstVisible = true;

		otherEstateLst.add(new OtherEstates(this.location2, this.point2,
				this.usage2, this.area2, this.titleProp));

		return "";
	}

	public String addEstatesRevenue() {

		this.dtEstLstVisible = true;
		rnEstateList.add(new LeasesProperties(this.rnLocation, this.rnPoint,
				this.rnUsage, this.rnArea, this.rnFullName, this.rnAFM,
				this.fromMonth, this.toMonth, this.totalMonth, this.rnRent,
				this.rnPesr, this.revFreeBuilding, this.revFreeOffice,
				this.revFreeLand, this.revPropHotel, this.revPropOffice));

		// if (this.rnPesr > 0) {
		// this.dtPartialEstLstVisible = true;
		// this.location = this.rnLocation;
		// this.point = this.rnPoint;
		// this.usage = this.rnUsage;
		// this.area = this.rnArea;

		/*
		 * partialEstateLst.add(new PartialEstates(this.location, this.point,
		 * this.usage, this.area, this.coFullName, this.coAddress, this.coAFM,
		 * this.cOwnPesr, this.rent));
		 */
		// }
		return "";
	}

	public String deletePartialEstateAction(PartialEstates objList) {
		partialEstateLst.remove(objList);
		return "";
	}

	public String deleteOtherEstates(OtherEstates objList) {
		otherEstateLst.remove(objList);
		return null;
	}

	public String deleteEstatesRevenue(LeasesProperties objList) {
		rnEstateList.remove(objList);
		return null;
	}

	public String saveAction() {

		return "";
	}

	public static class OtherEstates {
		private String location;
		private String point;
		private String usage;
		private float area;
		private String titleProp;

		public OtherEstates(String location, String point, String usage,
				float area, String title) {
			this.location = location;
			this.point = point;
			this.usage = usage;
			this.area = area;
			this.titleProp = title;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public String getUsage() {
			return usage;
		}

		public void setUsage(String usage) {
			this.usage = usage;
		}

		public float getArea() {
			return area;
		}

		public void setArea(float area) {
			this.area = area;
		}

		public String getTitleProp() {
			return titleProp;
		}

		public void setTitleProp(String titleProp) {
			this.titleProp = titleProp;
		}

	}

	public static class PartialEstates {
		private String location;
		private String point;
		private String usage;
		private float area;
		private String coFullName;
		private String coAFM;
		private String coAddress;
		private float cOwnPers;
		private float coRent;
		private int vitualEstateID;

		public PartialEstates(String location, String point, String usage,
				float area, String coFullName, String coAFM, String coAddress,
				float coPercent, float coRent) {
			this.location = location;
			this.point = point;
			this.usage = usage;
			this.area = area;
			this.coFullName = coFullName;
			this.coAFM = coAFM;
			this.coAddress = coAddress;
			this.cOwnPers = coPercent;
			this.coRent = coRent;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public String getUsage() {
			return usage;
		}

		public void setUsage(String usage) {
			this.usage = usage;
		}

		public float getArea() {
			return area;
		}

		public void setArea(float area) {
			this.area = area;
		}

		public String getCoFullName() {
			return coFullName;
		}

		public void setCoFullName(String coFullName) {
			this.coFullName = coFullName;
		}

		public String getCoAFM() {
			return coAFM;
		}

		public void setCoAFM(String coAFM) {
			this.coAFM = coAFM;
		}

		public String getCoAddress() {
			return coAddress;
		}

		public void setCoAddress(String coAddress) {
			this.coAddress = coAddress;
		}

		public float getcOwnPers() {
			return cOwnPers;
		}

		public void setcOwnPers(float cOwnPers) {
			this.cOwnPers = cOwnPers;
		}

		public float getCoRent() {
			return coRent;
		}

		public void setCoRent(float coRent) {
			this.coRent = coRent;
		}

	}

	public static class LeasesProperties {
		private String location;
		private String point;
		private String usage;
		private float area;
		private float rent;
		private int fromMonth;
		private int toMonth;
		private int totalMonth;
		private float rnPesr;
		private float revFreeBuilding;
		private float revFreeOffice;
		private float revFreeLand;
		private float revPropHotel;
		private float revPropOffice;
		private String rnFullName;
		private String rnAFM;

		public LeasesProperties(String location, String point, String usage,
				float area, String rnFullName, String rnAFM, int fromMonth,
				int toMonth, int totalMonth, float rent, float coPerecent,
				float revFreeBuilding, float revFreeOffice, float revFreeLand,
				float revPropHotel, float revPropOffice) {
			this.area = area;
			this.location = location;
			this.point = point;
			this.usage = usage;
			this.rnAFM = rnAFM;
			this.rnFullName = rnFullName;
			this.rent = rent;
			this.fromMonth = fromMonth;
			this.toMonth = toMonth;
			this.totalMonth = toMonth - fromMonth + 1;
			this.rnPesr = coPerecent;
			this.revFreeBuilding = revFreeBuilding;
			this.revFreeLand = revFreeLand;
			this.revFreeOffice = revFreeOffice;
			this.revPropHotel = revPropHotel;
			this.revPropOffice = revPropOffice;
		}

		public String getRnFullName() {
			return rnFullName;
		}

		public void setRnFullName(String rnFullName) {
			this.rnFullName = rnFullName;
		}

		public String getRnAFM() {
			return rnAFM;
		}

		public void setRnAFM(String rnAFM) {
			this.rnAFM = rnAFM;
		}

		public int getFromMonth() {
			return fromMonth;
		}

		public void setFromMonth(int fromMonth) {
			this.fromMonth = fromMonth;
		}

		public int getToMonth() {
			return toMonth;
		}

		public void setToMonth(int toMonth) {
			this.toMonth = toMonth;
		}

		public int getTotalMonth() {
			return totalMonth;
		}

		public void setTotalMonth(int totalMonth) {
			this.totalMonth = totalMonth;
		}

		public float getRnPesr() {
			return rnPesr;
		}

		public void setRnPesr(float rnPesr) {
			this.rnPesr = rnPesr;
		}

		public float getRevFreeBuilding() {
			return revFreeBuilding;
		}

		public void setRevFreeBuilding(float revFreeBuilding) {
			this.revFreeBuilding = revFreeBuilding;
		}

		public float getRevFreeOffice() {
			return revFreeOffice;
		}

		public void setRevFreeOffice(float revFreeOffice) {
			this.revFreeOffice = revFreeOffice;
		}

		public float getRevFreeLand() {
			return revFreeLand;
		}

		public void setRevFreeLand(float revFreeLand) {
			this.revFreeLand = revFreeLand;
		}

		public float getRevPropHotel() {
			return revPropHotel;
		}

		public void setRevPropHotel(float revPropHotel) {
			this.revPropHotel = revPropHotel;
		}

		public float getRevPropOffice() {
			return revPropOffice;
		}

		public void setRevPropOffice(float revPropOffice) {
			this.revPropOffice = revPropOffice;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public String getUsage() {
			return usage;
		}

		public void setUsage(String usage) {
			this.usage = usage;
		}

		public float getArea() {
			return area;
		}

		public void setArea(float area) {
			this.area = area;
		}

		public float getRent() {
			return rent;
		}

		public void setRent(float rent) {
			this.rent = rent;
		}

	}
}
