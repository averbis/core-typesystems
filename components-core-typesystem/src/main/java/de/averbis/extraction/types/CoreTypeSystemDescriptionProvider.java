/*
 * Copyright 2022 Averbis GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.averbis.extraction.types;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.spi.TypeSystemDescriptionProvider;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class CoreTypeSystemDescriptionProvider implements TypeSystemDescriptionProvider {

	@Override
	public List<TypeSystemDescription> listTypeSystemDescriptions() {

		String[] typeSystemDescriptionFiles = {
				"/de/averbis/textanalysis/typesystems/AverbisInternalTypeSystem.xml",
				"/de/averbis/textanalysis/typesystems/AverbisTypeSystem.xml" };

		List<TypeSystemDescription> typeSystemDescriptions = new ArrayList<>();
		for (String typeSystem : typeSystemDescriptionFiles) {
			URL resource = getClass().getResource(typeSystem);
			if (resource == null) {
				UIMAFramework.getLogger().error(
						"Unable to locate type system description as a resource [{}]", typeSystem);
				continue;
			}

			ResourceManager resMgr = new ResourceManager_impl(getClass().getClassLoader());

			try {
				TypeSystemDescription tsd = UIMAFramework.getXMLParser()
						.parseTypeSystemDescription(new XMLInputSource(resource));
				tsd.resolveImports(resMgr);
				typeSystemDescriptions.add(tsd);
			} catch (InvalidXMLException | IOException e) {
				UIMAFramework.getLogger().error(
						"Error loading type system description [{}] from [{}]", typeSystem,
						resource, e);
			}

			resMgr.destroy();
		}

		return typeSystemDescriptions;
	}
}
