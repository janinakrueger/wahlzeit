package org.wahlzeit.model;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class PhotoTest {

    @Test
    public void testWriteOnLocation() throws SQLException, MalformedURLException {

        Location location = Mockito.mock(Location.class); 
        ResultSet rset = Mockito.mock(ResultSet.class);
        Photo photo = new Photo();
        photo.setLocation(location);
        URL url = new URL("http://mockURL/");
        photo.ownerHomePage = url;
        photo.writeOn(rset);

        verify(location, Mockito.times(1)).writeOn(rset);
    }

    @Test
    public void testReadFromLocation() throws SQLException {
        Location location = Mockito.mock(Location.class); 
        ResultSet rset = Mockito.mock(ResultSet.class);
        when(rset.getString("owner_email_address")).thenReturn("mockemail@mockemail");
        when(rset.getString("owner_home_page")).thenReturn("http://mockhomepage");
        Photo photo = new Photo();
        photo.setLocation(location);

        photo.readFrom(rset);

        verify(location, Mockito.times(1)).readFrom(rset);
    }
}
