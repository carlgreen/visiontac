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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Carl Green
 */
public final class Vgps900Parser {

    private final SimpleDateFormat utcDateFormat;

    public Vgps900Parser() {
        utcDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        utcDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public Vgps900Data parse(final String line) throws InvalidDataException {
        final Vgps900Data data = new Vgps900Data();
        final String[] fields = line.split("\0*,");

        data.setIndex(Long.parseLong(fields[0]));
        data.setTag(fields[1].charAt(0));
        data.setTimestamp(parseTimestamp(fields[2], fields[3]));
        double latitude = Double.parseDouble(fields[4].substring(0, fields[4].length() - 1));
        final char latitudeSign = fields[4].charAt(fields[4].length() - 1);
        if (latitudeSign == 'S') {
            latitude *= -1;
        }
        data.setLatitude(latitude);
        double longitude = Double.parseDouble(fields[5].substring(0, fields[5].length() - 1));
        final char longitudeSign = fields[5].charAt(fields[5].length() - 1);
        if (longitudeSign == 'S') {
            longitude *= -1;
        }
        data.setLongitude(longitude);
        data.setHeight(Integer.parseInt(fields[6]));
        data.setSpeed(Integer.parseInt(fields[7]));
        data.setHeading(Integer.parseInt(fields[8]));
        data.setFixMode(fields[9]);
        data.setValid(fields[10]);
        data.setPdop(Double.parseDouble(fields[11]));
        data.setHdop(Double.parseDouble(fields[12]));
        data.setVdop(Double.parseDouble(fields[13]));
        data.setVox(fields[14].trim());

        return data;
    }

    Date parseTimestamp(final String dateString, final String timeString) throws InvalidDataException {
        final String datetimeString = dateString + timeString;
        try {
            return utcDateFormat.parse(datetimeString);
        } catch (final ParseException e) {
            throw new InvalidDataException(e, datetimeString);
        }
    }
}
