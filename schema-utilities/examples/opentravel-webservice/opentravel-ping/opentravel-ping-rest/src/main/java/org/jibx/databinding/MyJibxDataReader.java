/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jibx.databinding;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.jibx.JibxDataReader;
import org.apache.cxf.jibx.JibxNullBindingFactory;
import org.apache.cxf.jibx.JibxSimpleTypes;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.StAXReaderWrapper;
import org.jibx.runtime.impl.UnmarshallingContext;


public class MyJibxDataReader extends JibxDataReader {

    public Object read(QName elementQName, XMLStreamReader input, Class type) {
        try {
        	UnmarshallingContext ctx = getUnmarshallingContext(input, type);
            return ctx.unmarshalElement(type);
        } catch (JiBXException e) {
            throw new RuntimeException(e);
        }
    }
    private static UnmarshallingContext getUnmarshallingContext(XMLStreamReader reader, Class jtype)
            throws JiBXException {
            IBindingFactory factory;
            if (JibxSimpleTypes.isSimpleType(jtype)) {
                factory = JibxNullBindingFactory.getFactory();
            } else {
                factory = BindingDirectory.getFactory(jtype);
            }
            UnmarshallingContext ctx = (UnmarshallingContext)factory.createUnmarshallingContext();
            StAXReaderWrapper wrapper = new StAXReaderWrapper(reader, "Data-element", true);
            ctx.setDocument(wrapper);
            ctx.toTag();
            return ctx;
        }

}
