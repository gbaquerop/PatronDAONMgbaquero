package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ HotelDAOJdbcTest.class, PersonaDAOJdbcTest.class, ReservaDAOJdbcTest.class })
public class AllTests {

}
