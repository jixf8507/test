package com.qx.common.highcharts;

import java.math.BigDecimal;

public class TimeSerieBO extends SerieBO<BigDecimal> {
	private long pointInterval = 24 * 3600 * 1000;
	private long pointStart;

	public TimeSerieBO() {
		super();
	}

	public TimeSerieBO(String name) {
		super(name);
	}

	public long getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(long pointInterval) {
		this.pointInterval = pointInterval;
	}

	public long getPointStart() {
		return pointStart;
	}

	public void setPointStart(long pointStart) {
		this.pointStart = pointStart;
	}
}
