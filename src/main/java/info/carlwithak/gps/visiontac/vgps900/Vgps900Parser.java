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
        data.setLatitude(parseLatitude(fields[4]));
        data.setLongitude(parseLongitude(fields[5]));
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

    double parseLatitude(final String latitudeString) {
        final double unsignedLatitude = Double.parseDouble(latitudeString.substring(0, latitudeString.length() - 1));
        final char latitudeSign = latitudeString.charAt(latitudeString.length() - 1);
        return latitudeSign == 'N' ? unsignedLatitude : unsignedLatitude * -1;
    }

    double parseLongitude(final String longitudeString) {
        final double unsignedLongitude = Double.parseDouble(longitudeString.substring(0, longitudeString.length() - 1));
        final char longitudeSign = longitudeString.charAt(longitudeString.length() - 1);
        return longitudeSign == 'E' ? unsignedLongitude : unsignedLongitude * -1;
    }
}
