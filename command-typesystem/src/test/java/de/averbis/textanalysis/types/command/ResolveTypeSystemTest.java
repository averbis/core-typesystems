/*
 * Copyright 2025 Averbis GmbH
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
package de.averbis.textanalysis.types.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Test;

class ResolveTypeSystemTest {

	@Test
	void thatTypeSystemCanBeAutoDetectedAndResolved() throws Exception {

		var expectedTsd = UIMAFramework.getXMLParser().parseTypeSystemDescription(
				new XMLInputSource(getClass().getResource("CommandTypeSystem.xml")));

		var detectedTsd = TypeSystemDescriptionFactory.createTypeSystemDescription();

		assertThat(detectedTsd.getTypes()) //
				.extracting(TypeDescription::getName).containsAll(
						Stream.of(expectedTsd.getTypes()).map(TypeDescription::getName).toList());

		assertThat(detectedTsd.getTypes()).hasSize(50);
	}
}
