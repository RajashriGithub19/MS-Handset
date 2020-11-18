package com.mobile.microservice.domain;

public class Hardware {
	private String audioJack;

	private String gps;

	private String battery;

	public void setAudioJack(String audioJack) {
		this.audioJack = audioJack;
	}

	public String getAudioJack() {
		return this.audioJack;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getGps() {
		return this.gps;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getBattery() {
		return this.battery;
	}

	@Override
	public String toString() {
		return "Hardware [audioJack=" + audioJack + ", gps=" + gps + ", battery=" + battery + "]";
	}

}
