package registration.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import registration.model.Facility;

public class FacilityDAOTest {

	Facility facility;
	
	
	@Test
	public void test() {
		ArrayList<Facility> facilityList = new ArrayList<Facility>();

		facilityList = FacilityDAO.showFacilitiesCalling("Conference rooms", "2019-10-22 22:00:00");
		System.out.println(facilityList.size());
	}

}
