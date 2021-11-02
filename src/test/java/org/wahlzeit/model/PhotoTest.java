package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mock;

public class PhotoTest {

    @Test
    public void testReadFromLocation() {
        ResultSet rset = mock(ResultSet.class);
        Photo photo = new Photo();
        photo.setLocation(new Location(new Coordinate(0.0, 0.0, 0.0)));
        try {
            when(rset.getString("owner_email_address")).thenReturn("mockemail@mockemail");
            when(rset.getString("owner_home_page")).thenReturn("http://mockhomepage");
            when(rset.getDouble("x_coordinate")).thenReturn(1.0);
            when(rset.getDouble("y_coordinate")).thenReturn(1.0);
            when(rset.getDouble("z_coordinate")).thenReturn(1.0);
            photo.readFrom(rset);
        }
        catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
        Location expectedLocation = new Location(new Coordinate(1.0, 1.0, 1.0));
        assertEquals(expectedLocation.getCoordinate(), photo.location.getCoordinate());
    }

    @Test
    public void testWriteOnLocation() {
        ResultSet rset = mock(ResultSet.class);
        Photo photo = new Photo();
        photo.setLocation(new Location(new Coordinate(0.0, 0.0, 0.0)));
        
        try {
            URL url = new URL("http://mockURL/");
            photo.ownerHomePage = url;
            photo.writeOn(rset);
            verify(rset, times(1)).updateDouble("x_coordinate", 0.0);
            verify(rset, times(1)).updateDouble("y_coordinate", 0.0);
            verify(rset, times(1)).updateDouble("z_coordinate", 0.0);
        }
        catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
        catch(MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
}
