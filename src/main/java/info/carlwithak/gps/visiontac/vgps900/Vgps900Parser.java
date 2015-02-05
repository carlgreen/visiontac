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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class Vgps900Parser {

    private final SimpleDateFormat utcDateFormat;

    Vgps900Parser() {
        utcDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        utcDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public Vgps900Data parse(final String line) throws InvalidDataException {
        final String[] fields = line.split("\0*,");

        final Vgps900Data.Builder builder = new Vgps900Data.Builder()
                .index(Long.parseLong(fields[0]))
                .tag(fields[1].charAt(0))
                .timestamp(parseTimestamp(fields[2], fields[3]))
                .latitude(parseLatitude(fields[4]))
                .longitude(parseLongitude(fields[5]))
                .height(Integer.parseInt(fields[6]))
                .speed(Integer.parseInt(fields[7]))
                .heading(Integer.parseInt(fields[8]));

        if (fields.length == 15) {
            builder.fixMode(fields[9])
                    .valid(fields[10])
                    .pdop(Double.parseDouble(fields[11]))
                    .hdop(Double.parseDouble(fields[12]))
                    .vdop(Double.parseDouble(fields[13]))
                    .vox(fields[14].trim());
        } else {
            builder.vox(fields[9].trim());
        }

        return builder.build();
    }

    Date parseTimestamp(final String dateString, final String timeString) throws InvalidDataException {
        final String datetimeString = dateString + timeString;
        try {
            return utcDateFormat.parse(datetimeString);
        } catch (final ParseException e) {
            throw new InvalidDataException(e, datetimeString);
        }
    }

    double parseLatitude(final String latitudeString) throws InvalidDataException {
        return parseLatLngString(latitudeString, 'N', 'S');
    }

    double parseLongitude(final String longitudeString) throws InvalidDataException {
        return parseLatLngString(longitudeString, 'E', 'W');
    }

    private double parseLatLngString(final String str, final char positiveSign, final char negativeSign) throws InvalidDataException {
        final double unsigned = Double.parseDouble(str.substring(0, str.length() - 1));
        final char signChar = str.charAt(str.length() - 1);
        if (signChar != positiveSign && signChar != negativeSign) {
            throw new InvalidDataException("Invalid latitude or longitude string: " + str, str);
        }
        return signChar == positiveSign ? unsigned : unsigned * -1;
    }

    public static List<Vgps900Data> parse(final InputStream is) throws IOException, InvalidDataException {
        final Vgps900Parser parser = new Vgps900Parser();
        final BufferedReader rdr = new BufferedReader(new InputStreamReader(is));
        final ArrayList<Vgps900Data> path = new ArrayList<>();

        // ignore header
        rdr.readLine();

        String line;
        while ((line = rdr.readLine()) != null) {
            path.add(parser.parse(line));
        }

        return path;
    }
}
