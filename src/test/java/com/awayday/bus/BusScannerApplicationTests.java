package com.awayday.bus;

import com.awayday.bus.bus.model.BusApiModel;
import com.awayday.bus.bus.service.BusService;
import com.awayday.bus.station.model.StationApiModel;
import com.awayday.bus.station.service.StationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusScannerApplicationTests {

	@Autowired
	private StationService stationService;

	@Autowired
	private BusService busService;

	@Test
	public void testStation() {
		Flux<StationApiModel> mono2 = stationService.requestLocationStation(36.3, 127.3);
		List<StationApiModel> list = mono2.collectList().block();
		System.out.println(list);

		Assert.assertEquals(4, list.size());
	}

	@Test
	public void testBusOfNode() {
		Flux<BusApiModel> mono2 = busService.requestArriveBus("25", "DJB8001793ND");
		List<BusApiModel> list = mono2.collectList().block();
		System.out.println(list);

		Assert.assertEquals(1, list.size());
	}

}
