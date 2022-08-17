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
package de.averbis.textanalysis.types.evaluation;

import static de.averbis.extraction.types.CoreTypeSystemDescriptionProvider.loadTypeSystemDescriptions;

import java.util.List;

import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.spi.TypeSystemDescriptionProvider;

public class EvaluationTypeSystemDescriptionProvider implements TypeSystemDescriptionProvider {

	@Override
	public List<TypeSystemDescription> listTypeSystemDescriptions() {

		return loadTypeSystemDescriptions(getClass(), "EvaluationTypeSystem.xml");
	}
}
