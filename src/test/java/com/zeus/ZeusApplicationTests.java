package com.zeus;

import com.zeus.client.ZeusClient;
import com.zeus.provider.ZeusApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZeusApplicationTests {

	@Test
	public void contextLoads()throws Exception {
		ZeusApi zeusApi = ZeusClient.getRemoteProxy(ZeusApi.class, new InetSocketAddress(InetAddress.getLocalHost(), 9999));

		String s = zeusApi.zeus();
		System.out.println(s);
	}

}
