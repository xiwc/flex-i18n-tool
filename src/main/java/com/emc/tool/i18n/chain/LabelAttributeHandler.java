package com.emc.tool.i18n.chain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;

public class LabelAttributeHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "label";

	@SuppressWarnings("rawtypes")
	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("LabelAttributeHandler.invoke()");

		Document doc = request.getDocument();

		List selectNodes2 = doc.selectNodes("//@" + ATTRIBUTE_NAME);

		// need total size.
		System.out.println(String.format("Size:%s", selectNodes2.size()));

		for (Object object : selectNodes2) {

			Attribute attribute = (Attribute) object;

			String pName = attribute.getParent().getName();

			if (pName == "Object") {
				continue;
			}

			String attrVal = attribute.getValue();

			if (attrVal.startsWith("{") && attrVal.endsWith("}")) {
				continue;
			}

			String id = attrVal.trim().replace("*", "must").replaceAll("[\\W&&[^\\s]]", "").replaceAll("\\s+", ".")
					.toLowerCase();

			String key = StringUtils
					.join(new String[] { request.getNamespace(), pName.toLowerCase(), ATTRIBUTE_NAME.toLowerCase(), id },
							".");

			if (key.endsWith(".")) {
				key = key + "n" + new Date().getTime();
			}

			// need property.
			System.out.println(String.format("%s=%s", key, attrVal));

			if (response.getProps().containsKey(key)) {
				System.err.println(String.format("Existing Key:%s", key));

				if (!response.getProps().get(key).equals(attrVal)) {
					key = key + ".n" + new Date().getTime();
					response.getProps().put(key, attrVal);
					// need property.
					System.out.println(String.format("%s=%s", key, attrVal));
				}
			} else {
				response.getProps().put(key, attrVal);
				// need property.
				System.out.println(String.format("%s=%s", key, attrVal));
			}

			attribute.setValue(StringUtils.replace("{$('key')}", "key", key));
		}

		return true;
	}

}
