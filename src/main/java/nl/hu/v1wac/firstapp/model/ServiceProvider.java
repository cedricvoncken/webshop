package nl.hu.v1wac.firstapp.model;

import nl.hu.v1wac.firstapp.model.WorldService;

public class ServiceProvider {
	private static WorldService WorldService = new WorldService();
	
	public static WorldService getWorldService() {
		return WorldService;
	}
}
