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

package org.jibx.config.osgi.wrapped.org.apache.cxf.jibx;

import javax.xml.stream.XMLStreamWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.StAXWriter;

public class JibxDataWriter extends org.apache.cxf.jibx.JibxDataWriter {

    public void write(Object obj, XMLStreamWriter output) {
        try {
            IBindingFactory factory = BindingDirectory.getFactory(obj.getClass());
            IMarshallingContext ctx = getMarshallingContext(obj);
            StAXWriter writer = new StAXWriter(factory.getNamespaces(), output);
            ctx.setXmlWriter(writer);
            ((IMarshallable)obj).marshal(ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private IMarshallingContext getMarshallingContext(Object object) throws JiBXException {
        IBindingFactory factory = BindingDirectory.getFactory(object.getClass());
        return factory.createMarshallingContext();
    }
}
