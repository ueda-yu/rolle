/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */

package org.apache.roller.weblogger.business;

import java.util.Map;
import org.apache.roller.weblogger.WebloggerException;
import org.apache.roller.weblogger.pojos.RuntimeConfigProperty;


/**
 * Manages global properties for Roller.
 */
public interface PropertiesManager {
    
    /**
     * Initialize the properties manager.
     *
     * @throws InitializationException If there is a problem during initialization.
     */
    void initialize() throws InitializationException;
    
    
    /**
     * Release all resources associated with Roller session.
     */
    void release();
    
    
    /**
     * Save a single property
     */
    void saveProperty(RuntimeConfigProperty property) throws WebloggerException;
    
    
    /**
     * Save a list of properties
     */
    void saveProperties(Map<String, RuntimeConfigProperty> properties) throws WebloggerException;
    
    
    /**
     * Retrieve a single property by name
     */
    RuntimeConfigProperty getProperty(String name) throws WebloggerException;
    
    
    /**
     * Retrieve a list of all properties
     */
    Map<String, RuntimeConfigProperty> getProperties() throws WebloggerException;
    
}
