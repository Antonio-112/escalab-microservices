package com.anto.microcarrito.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoundRobinCustomizedRule extends AbstractLoadBalancerRule {

	private AtomicInteger nextServerCyclicCounter;

	private static Logger log = LoggerFactory.getLogger(RoundRobinCustomizedRule.class);

	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			log.warn("no load balancer");
			return null;
		}

		Server server = null;
		int count = 0;
		while (server == null && count++ < 5) {
			List<Server> activeServers = lb.getReachableServers();
			List<Server> allServers = lb.getAllServers();

			int upCount = activeServers.size();
			int serverCount = allServers.size();

			if(serverCount == 1) {
				log.info("Only 1 server available");
				return allServers.get(0);
			}
			
			if ((upCount == 0) || (serverCount == 0)) {
				log.warn("No up servers available from load balancer: " + lb);
				return null;
			}

			int nextServerIndex = incrementAndGetModulo(serverCount);
			server = allServers.get(nextServerIndex);

			if (server == null) {
				Thread.yield();
				continue;
			}

			if (server.isAlive() && (server.isReadyToServe())) {
				return (server);
			}
			server = null;
		}

		if (count >= 5) {
			log.warn("No available alive servers after 5 tries from load balancer: " + lb);
		}
		return server;
	}

	private int incrementAndGetModulo(int modulo) {
		for (;;) {
			int current = nextServerCyclicCounter.get();
			int next = (current + 1) % modulo;
			if (nextServerCyclicCounter.compareAndSet(current, next))
				return next;
		}
	}

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
	}
}
