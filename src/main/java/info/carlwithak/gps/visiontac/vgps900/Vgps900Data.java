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
public final class Vgps900Data {
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
    private Double pdop;
    private Double hdop;
    private Double vdop;
    private String vox;

    private Vgps900Data(final Builder builder) {
        this.index = builder.index;
        this.tag = builder.tag;
        this.timestamp = builder.timestamp;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.height = builder.height;
        this.speed = builder.speed;
        this.heading = builder.heading;
        this.fixMode = builder.fixMode;
        this.valid = builder.valid;
        this.pdop = builder.pdop;
        this.hdop = builder.hdop;
        this.vdop = builder.vdop;
        this.vox = builder.vox;
    }

    public long getIndex() {
        return index;
    }

    public char getTag() {
        return tag;
    }

    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeading() {
        return heading;
    }

    public String getFixMode() {
        return fixMode;
    }

    public String getValid() {
        return valid;
    }

    public Double getPdop() {
        return pdop;
    }

    public Double getHdop() {
        return hdop;
    }

    public Double getVdop() {
        return vdop;
    }

    public String getVox() {
        return vox;
    }

    static class Builder {
        private long index;
        private char tag;
        private Date timestamp;
        private double latitude;
        private double longitude;
        private int height;
        private int speed;
        private int heading;
        private String fixMode;
        private String valid;
        private Double pdop;
        private Double hdop;
        private Double vdop;
        private String vox;

        Builder index(final long index) {
            this.index = index;
            return this;
        }

        Builder tag(final char tag) {
            this.tag = tag;
            return this;
        }

        Builder timestamp(final Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        Builder latitude(final double latitude) {
            this.latitude = latitude;
            return this;
        }

        Builder longitude(final double longitude) {
            this.longitude = longitude;
            return this;
        }

        Builder height(final int height) {
            this.height = height;
            return this;
        }

        Builder speed(final int speed) {
            this.speed = speed;
            return this;
        }

        Builder heading(final int heading) {
            this.heading = heading;
            return this;
        }

        Builder fixMode(final String fixMode) {
            this.fixMode = fixMode;
            return this;
        }

        Builder valid(final String valid) {
            this.valid = valid;
            return this;
        }

        Builder pdop(final Double pdop) {
            this.pdop = pdop;
            return this;
        }

        Builder hdop(final Double hdop) {
            this.hdop = hdop;
            return this;
        }

        Builder vdop(final Double vdop) {
            this.vdop = vdop;
            return this;
        }

        Builder vox(final String vox) {
            this.vox = vox;
            return this;
        }

        Vgps900Data build() {
            return new Vgps900Data(this);
        }
    }
}
