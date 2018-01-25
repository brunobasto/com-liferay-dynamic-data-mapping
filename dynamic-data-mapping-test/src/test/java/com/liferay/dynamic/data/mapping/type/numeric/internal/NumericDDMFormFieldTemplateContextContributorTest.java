/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.type.numeric.internal;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Pedro Queiroz
 */
public class NumericDDMFormFieldTemplateContextContributorTest
	extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_numericDDMFormFieldTemplateContextContributor =
			new NumericDDMFormFieldTemplateContextContributor();
	}

	@Test
	public void testGetDecimalSeparatorSymbols() {
		DDMFormField ddmFormField = createDDMFormField("double");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setLocale(LocaleUtil.US);

		Map<String, Object> parameters =
			_numericDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		Map<String, String> separatorSymbolsMap =
			(Map<String, String>)parameters.get("symbols");

		Assert.assertEquals(
			".", separatorSymbolsMap.get("decimalSeparatorSymbol"));
		Assert.assertEquals(
			",", separatorSymbolsMap.get("groupSeparatorSymbol"));

		ddmFormFieldRenderingContext.setLocale(LocaleUtil.BRAZIL);

		parameters =
			_numericDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		separatorSymbolsMap = (Map<String, String>)parameters.get("symbols");

		Assert.assertEquals(
			",", separatorSymbolsMap.get("decimalSeparatorSymbol"));
		Assert.assertEquals(
			".", separatorSymbolsMap.get("groupSeparatorSymbol"));
	}

	protected DDMFormField createDDMFormField(String dataType) {
		DDMFormField ddmFormField = DDMFormTestUtil.createNumericDDMFormField(
			"number", false, false, dataType, false);

		return ddmFormField;
	}

	private NumericDDMFormFieldTemplateContextContributor
		_numericDDMFormFieldTemplateContextContributor;

}