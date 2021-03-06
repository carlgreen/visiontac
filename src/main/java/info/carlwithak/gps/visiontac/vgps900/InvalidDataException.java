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

public class InvalidDataException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String data;

    public InvalidDataException(final String message, final String data) {
        super(message);
        this.data = data;
    }

    public InvalidDataException(final Throwable cause, final String data) {
        super(cause);
        this.data = data;
    }

    /**
     * @return the data that caused the exception.
     */
    public String getData() {
        return data;
    }
}
