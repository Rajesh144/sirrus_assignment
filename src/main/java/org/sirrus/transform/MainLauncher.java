package org.sirrus.transform;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.sirrus.json.Geo;
import org.sirrus.json.JsonUtils;
import org.sirrus.json.Location;
import org.sirrus.json.Tmc;
import org.sirrus.xml.Incident;
import org.sirrus.xml.Incident.Ti.Ev;

public class MainLauncher {

	private static final String GEO = "geo";
	private static final String TMC = "tmc";
	private static final String TRAFFIC_INCIDENT_TYPE = "TrafficIncident";

	public static void main(String[] args) {
		try {

			File file = new File("./sirrus_input.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Incident.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Incident incident = (Incident) jaxbUnmarshaller.unmarshal(file);

			List<Incident.Ti.Ev> evs = incident.getTi().getEv();
//			<ev>
//			<id>23317403</id>
//			<ec>702</ec>
//			<se>3</se>
//			<loc type='geo'>
//			<geo lon='-74.00625' lat='40.717075'/>
//			<addr>Worth St</addr>
//			</loc>
//			<valid start='2019-02-10T05:00:00Z'
//			end='2019-04-13T03:58:59Z'/>
//			<text lang='e'>In Manhattan, major road construction on Worth
//			St between W Broadway and Church St.</text>
//			<delay>8</delay>
//			</ev>

//			{
//				" _id ": "224155332" ,
//				" description ": "In Greenview No. 16, animals on roadway on AB-40 EB
//				etween Forestry Trunk Rd and Pierre Greys Lake Rd." ,
//				" geo ": {
//				" type ": "Point" ,
//				" coordinates ": [
//				-118.67265 ,
//				53.924755
//				]
//				} ,
//				" roadName ": "AB-40" ,
//				" eventCode ": 922 ,
//				" severity ": 2 ,
//				" validStart ": "2018-10-26T13:39:58.000Z" ,
//				" validEnd ": "2019-07-20T00:51:19.000Z" ,
//				" type ": "TrafficIncident" ,
//				" lastUpdated ": "2019-07-20T00:23:12.748Z"
//			},

			List<Location> locations = new ArrayList<Location>();
			for (Ev ev : evs) {
				Location location = new Location();
				location.set_id(String.valueOf(ev.getId()));
				location.setDescription(ev.getText().getValue());

//				eventCode ec
				location.setEventCode((int) ev.getEc());

//				severity se
				location.setSeverity((int) ev.getSe());

//				validStart validStart				
				location.setValidStart(ev.getValid().getStart().toString());

//				validEnd validEnd
				location.setValidEnd(ev.getValid().getEnd().toString());

//				type Always “TrafficIncident”
				location.setType(TRAFFIC_INCIDENT_TYPE);

//				lastUpdated The time the output file was generated formatted as ISO 8601
				location.setLastUpdated(new Timestamp(System.currentTimeMillis()).toString());
				if(ev.getLoc().getType().equals(GEO)) {
					Geo geo = new Geo();
					Float[] coordinates = new Float[2];
					coordinates[0] = ev.getLoc().getGeo().getLon();
					coordinates[1] = ev.getLoc().getGeo().getLat();
					geo.setCoordinates(coordinates);
					geo.setType(ev.getLoc().getType());
					location.setGeo(geo);

				} else if(ev.getLoc().getType().equals(TMC)) {

					Tmc tmc = new Tmc();
					tmc.setTable(Integer.valueOf(ev.getLoc().getStart().getExtent()));
					tmc.setId(Integer.valueOf(ev.getLoc().getStart().getId()));
					tmc.setDirection(String.valueOf((ev.getLoc().getStart().getDir())));
					location.setTmc(tmc);
				}
				location.setRoadName(ev.getLoc().getAddr());
				locations.add(location);
			}

			System.out.println(JsonUtils.writeValueAsString(locations));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
