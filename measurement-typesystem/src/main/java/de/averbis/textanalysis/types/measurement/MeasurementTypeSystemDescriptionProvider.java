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
package de.averbis.textanalysis.types.measurement;

import static org.apache.uima.util.TypeSystemUtil.loadTypeSystemDescriptionsFromClasspath;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.spi.JCasClassProvider;
import org.apache.uima.spi.TypeSystemDescriptionProvider;

public class MeasurementTypeSystemDescriptionProvider
		implements TypeSystemDescriptionProvider, JCasClassProvider {

	private List<TypeSystemDescription> typeSystemDescriptions;
	private List<Class<? extends TOP>> jCasClasses;


	@Override
	public synchronized List<TypeSystemDescription> listTypeSystemDescriptions() {

		if (typeSystemDescriptions == null) {
			typeSystemDescriptions = loadTypeSystemDescriptionsFromClasspath(getClass(), //
					"MeasurementTypeSystem.xml");
		}
		return typeSystemDescriptions;
	}


	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<Class<? extends TOP>> listJCasClasses() {

		if (jCasClasses == null) {
			List<Class<? extends TOP>> classes = new ArrayList<>();
			ClassLoader cl = getClass().getClassLoader();

			for (TypeSystemDescription tsd : listTypeSystemDescriptions()) {
				for (TypeDescription td : tsd.getTypes()) {
					try {
						classes.add((Class<? extends TOP>) cl.loadClass(td.getName()));
					} catch (ClassNotFoundException e) {
						// This is acceptable - there may not be a JCas class
					}
				}
			}
			jCasClasses = classes;
		}

		return jCasClasses;
	}
}
