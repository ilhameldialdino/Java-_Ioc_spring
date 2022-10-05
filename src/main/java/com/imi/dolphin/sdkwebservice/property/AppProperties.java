package com.imi.dolphin.sdkwebservice.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppProperties {

	@Value("${sdk.connectTimeout}")
	String sdkConnectTimeout;
	
	@Value("${sdk.readTimeout}")
	String sdkReadTimeout;
	
	/**
	 * @return the sdkConnectTimeout
	 */
	public String getSdkConnectTimeout() {
		return sdkConnectTimeout;
	}

	/**
	 * @param sdkConnectTimeout the sdkConnectTimeout to set
	 */
	public void setSdkConnectTimeout(String sdkConnectTimeout) {
		this.sdkConnectTimeout = sdkConnectTimeout;
	}

	/**
	 * @return the sdkReadTimeout
	 */
	public String getSdkReadTimeout() {
		return sdkReadTimeout;
	}

	/**
	 * @param sdkReadTimeout the sdkReadTimeout to set
	 */
	public void setSdkReadTimeout(String sdkReadTimeout) {
		this.sdkReadTimeout = sdkReadTimeout;
	}

	

}
