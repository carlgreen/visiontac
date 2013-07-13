/*
 * Copyright (C) 2013 Carl Green
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.carlwithak.gps.visiontac.vgps900;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Carl Green
 */
public class Vgps900ParserTest {

    private final Vgps900Parser parser = new Vgps900Parser();

    @Test
    public void testParseAdvancedLine() throws ParseException {
        final String input = "1\0\0\0\0\0,T,111213,185059,36.874506S,174.779188E,152\0\0,79\0\0,120,3D,SPS ,2.1\0\0,1.9\0\0,1.0\0\0,\0\0\0\0\0\0\0\0\0";

        final Vgps900Data result = parser.parse(input);
        assertThat(result.getIndex(), is(1L));
        assertThat(result.getTag(), is('T'));
        final Calendar date = new GregorianCalendar();
        date.set(Calendar.MILLISECOND, 0);
        date.setTimeZone(TimeZone.getTimeZone("UTC"));
        date.set(111 + 1900, 11, 13, 18, 50, 59);
        assertThat(result.getTimestamp(), is(date.getTime()));
        assertThat(result.getLatitude(), is(-36.874506));
        assertThat(result.getLongitude(), is(174.779188));
        assertThat(result.getHeight(), is(152));
        assertThat(result.getSpeed(), is(79));
        assertThat(result.getHeading(), is(120));
        assertThat(result.getFixMode(), is("3D"));
        assertThat(result.getValid(), is("SPS "));
        assertThat(result.getPdop(), is(2.1));
        assertThat(result.getHdop(), is(1.9));
        assertThat(result.getVdop(), is(1.0));
        assertThat(result.getVox(), is(""));
    }
}
