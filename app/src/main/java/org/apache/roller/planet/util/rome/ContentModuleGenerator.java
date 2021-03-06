/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.roller.planet.util.rome;

import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

public class ContentModuleGenerator implements ModuleGenerator {
    
    private static final Namespace CONTENT_NS = Namespace.getNamespace(ContentModule.URI);

    private static final Set<Namespace> NAMESPACES = Set.of(CONTENT_NS);

    @Override
    public String getNamespaceUri() {
        return ContentModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        ContentModule fm = (ContentModule)module;
        if (fm.getEncoded() != null) {
            element.addContent(generateSimpleElement("encoding", fm.getEncoded()));
        }
    }

    protected Element generateSimpleElement(String name, String value)  {
        Element element = new Element(name, CONTENT_NS);
        element.addContent(value);
        return element;
    }

}
