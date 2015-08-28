package com.ebiz.mmt.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class DomainUtils {
	public static void copyProperties(Object dest, Object orig) {
		if (null != orig) {
			try {
				BeanUtils.copyProperties(dest, orig);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}
}
