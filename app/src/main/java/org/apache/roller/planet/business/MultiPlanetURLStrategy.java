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

package org.apache.roller.planet.business;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.roller.weblogger.config.WebloggerRuntimeConfig;

/**
 *
 */
public class MultiPlanetURLStrategy implements PlanetURLStrategy {
    
    
    /**
     * Get root url for a given weblog.  Optionally for a certain locale.
     */
    @Override
    public String getPlanetURL(String planet) {
        
        if(planet == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(WebloggerRuntimeConfig.getProperty("site.absoluteurl"));
        url.append('/').append(planet).append('/');
        
        return url.toString();
    }
    
    
    /**
     * Get url for a single weblog entry on a given weblog.
     */
    @Override
    public String getPlanetGroupURL(String planet, String group, int pageNum) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        url.append(getPlanetURL(planet));
        url.append("group/").append(group).append('/');
        
        if(pageNum > 0) {
            url.append("?page=");
            url.append(pageNum);
        }
        
        return url.toString();
    }
    
    
    /**
     * Get url for a feed on a given weblog.
     */
    @Override
    public String getPlanetGroupFeedURL(String planet, String group, String format) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(getPlanetGroupURL(planet, group, -1));
        url.append("feed/").append(format);
        
        return url.toString();
    }
    
    
    /**
     * Get url for opml file on a given planet group.
     */
    @Override
    public String getPlanetGroupOpmlURL(String planet, String group) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(getPlanetGroupURL(planet, group, -1));
        url.append("opml");
        
        return url.toString();
    }
    
    
    /**
     * Compose a map of key=value params into a query string.
     */
    @Override
    public String getQueryString(Map<String, String> params) {
        
        if(params == null) {
            return null;
        }
        
        StringBuilder queryString = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (queryString.length() == 0) {
                queryString.append('?');
            } else {
                queryString.append('&');
            }

            queryString.append(entry.getKey());
            queryString.append('=');
            queryString.append(entry.getValue());
        }

        return queryString.toString();
    }
    
    
    /**
     * URL encode a string using UTF-8.
     */
    @Override
    public String encode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }
    
    
    /**
     * URL decode a string using UTF-8.
     */
    @Override
    public String decode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }
    
}
