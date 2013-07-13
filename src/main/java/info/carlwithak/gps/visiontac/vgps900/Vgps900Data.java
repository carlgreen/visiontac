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

import java.util.Date;

/**
 *
 * @author Carl Green
 */
public class Vgps900Data {
    private long index;
    private char tag;
    private Date timestamp;
    private double latitude;
    private double longitude;
    /**
     * meters above sea level.
     */
    private int height;
    /**
     * meters per second.
     */
    private int speed;
    /**
     * degrees.
     */
    private int heading;
    private String fixMode;
    private String valid;
    private double pdop;
    private double hdop;
    private double vdop;
    private String vox;

    public long getIndex() {
        return index;
    }

    void setIndex(final long index) {
        this.index = index;
    }

    public char getTag() {
        return tag;
    }

    void setTag(final char tag) {
        this.tag = tag;
    }

    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public int getHeight() {
        return height;
    }

    void setHeight(final int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    void setSpeed(final int speed) {
        this.speed = speed;
    }

    public int getHeading() {
        return heading;
    }

    void setHeading(final int heading) {
        this.heading = heading;
    }

    public String getFixMode() {
        return fixMode;
    }

    void setFixMode(final String fixMode) {
        this.fixMode = fixMode;
    }

    public String getValid() {
        return valid;
    }

    void setValid(final String valid) {
        this.valid = valid;
    }

    public double getPdop() {
        return pdop;
    }

    void setPdop(final double pdop) {
        this.pdop = pdop;
    }

    public double getHdop() {
        return hdop;
    }

    void setHdop(final double hdop) {
        this.hdop = hdop;
    }

    public double getVdop() {
        return vdop;
    }

    void setVdop(final double vdop) {
        this.vdop = vdop;
    }

    public String getVox() {
        return vox;
    }

    void setVox(final String vox) {
        this.vox = vox;
    }
}
