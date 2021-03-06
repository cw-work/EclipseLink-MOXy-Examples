/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */


package example.json.demo3.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

import example.json.demo3.jaxb.FlightType;
import example.json.demo3.jaxb.Flights;

/**
 * @author Jakub Podlesak
 */
@Singleton
@Path(value = "/flights")
public class FlightList {

    private Flights myFlights;

    /**
     * This class is annotated with @Singleton meaning that only
     * one instance of this class will be instantated per web
     * application. 
     * <p>
     * The flight lists will be constructed just once
     * when the first request to the flight list resource occurs.
     */
    public FlightList() {
        myFlights = new Flights();
        FlightType flight123 = new FlightType();
        flight123.setCompany("Czech Airlines");
        flight123.setNumber(123);
        flight123.setFlightId("OK123");
        flight123.setAircraft("B737");
        FlightType flight124 = new FlightType();
        flight124.setCompany("Czech Airlines");
        flight124.setNumber(124);
        flight124.setFlightId("OK124");
        flight124.setAircraft("AB115");
        myFlights.getFlight().add(flight123);
        myFlights.getFlight().add(flight124);
    }

    @GET
    @Produces({"application/json", "application/xml"})
    public synchronized Flights getFlightList() {
        return myFlights;
    }

    @PUT
    @Consumes({"application/json", "application/xml"})
    public synchronized void putFlightList(Flights flights) {
        myFlights = flights;
    }
}
