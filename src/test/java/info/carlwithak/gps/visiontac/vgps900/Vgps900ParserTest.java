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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class Vgps900ParserTest {

    private final Vgps900Parser parser = new Vgps900Parser();

    @Test
    public void parseTimestampShouldReturnValidDate() throws InvalidDataException {
        assertThat(parser.parseTimestamp("111213", "185059"), is(getUtcDate(111 + 1900, 11, 13, 18, 50, 59)));
    }

    @Test
    public void parseNorthernLatitudeShouldReturnSignedValue() throws InvalidDataException {
        assertThat(parser.parseLatitude("36.874506N"), is(36.874506));
    }

    @Test
    public void parseSouthernLatitudeShouldReturnSignedValue() throws InvalidDataException {
        assertThat(parser.parseLatitude("36.874506S"), is(-36.874506));
    }

    @Test(expected = InvalidDataException.class)
    public void parseInvalidLatitudeShouldFail() throws InvalidDataException {
        parser.parseLatitude("36.874506X");
    }

    @Test
    public void parseEasternLongitudeShouldReturnSignedValue() throws InvalidDataException {
        assertThat(parser.parseLongitude("174.779188E"), is(174.779188));
    }

    @Test
    public void parseWesternLongitudeShouldReturnSignedValue() throws InvalidDataException {
        assertThat(parser.parseLongitude("174.779188W"), is(-174.779188));
    }

    @Test
    public void parseStandardLineShouldReturnPopulatedData() throws Exception {
        final String input = "23\0\0\0\0,T,090512,041041,41.302453S,174.778450E,2\0\0,3\0\0\0,0\0\0,\0\0\0\0\0\0\0\0\0";

        final Vgps900Data result = parser.parse(input);
        assertThat(result.getIndex(), is(23L));
        assertThat(result.getTag(), is('T'));
        assertThat(result.getTimestamp(), is(getUtcDate(109 + 1900, 4, 12, 4, 10, 41)));
        assertThat(result.getLatitude(), is(-41.302453));
        assertThat(result.getLongitude(), is(174.778450));
        assertThat(result.getHeight(), is(2));
        assertThat(result.getSpeed(), is(3));
        assertThat(result.getHeading(), is(0));
        assertThat(result.getFixMode(), is(nullValue()));
        assertThat(result.getValid(), is(nullValue()));
        assertThat(result.getPdop(), is(nullValue()));
        assertThat(result.getHdop(), is(nullValue()));
        assertThat(result.getVdop(), is(nullValue()));
        assertThat(result.getVox(), is(""));
    }

    @Test
    public void parseAdvancedLineShouldReturnPopulatedData() throws InvalidDataException {
        final String input = "1\0\0\0\0\0,T,111213,185059,36.874506S,174.779188E,152\0\0,79\0\0,120,3D,SPS ,2.1\0\0,1.9\0\0,1.0\0\0,\0\0\0\0\0\0\0\0\0";

        final Vgps900Data result = parser.parse(input);
        assertThat(result.getIndex(), is(1L));
        assertThat(result.getTag(), is('T'));
        assertThat(result.getTimestamp(), is(getUtcDate(111 + 1900, 11, 13, 18, 50, 59)));
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

    private static Date getUtcDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }
}
