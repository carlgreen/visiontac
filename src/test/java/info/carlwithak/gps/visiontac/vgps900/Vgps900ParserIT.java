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

import java.io.InputStream;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Vgps900ParserIT {

    @Test
    public void testParseStandardFile() throws Exception {
        final InputStream is = ClassLoader.getSystemResourceAsStream("09051201.CSV");
        final List<Vgps900Data> result = Vgps900Parser.parse(is);
        assertThat(result.size(), is(3));
    }

    @Test
    public void testParseProfessionalFile() throws Exception {
        final InputStream is = ClassLoader.getSystemResourceAsStream("11121300.CSV");
        final List<Vgps900Data> result = Vgps900Parser.parse(is);
        assertThat(result.size(), is(3));
    }
}
