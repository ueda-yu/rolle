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

package org.apache.roller.weblogger.ui.rendering.pagers;

import java.util.Map;
import org.apache.roller.weblogger.business.URLStrategy;
import org.apache.roller.weblogger.util.URLUtilities;


/**
 * Abstract base for simple pagers.
 */
public abstract class AbstractPager<T> implements Pager<T> {
    
    final URLStrategy urlStrategy;
    private String url = null;
    private int page = 0;
    
    
    public AbstractPager(URLStrategy strat, String baseUrl, int pageNum) {
        
        this.urlStrategy = strat;
        this.url = baseUrl;
        if(pageNum > 0) {
            this.page = pageNum;
        }
    }
    
    
    @Override
    public String getHomeLink() {
        return url;
    }
    
    
    @Override
    public String getHomeName() {
        return "Home";
    }
    
    
    @Override
    public String getNextLink() {
        if(hasMoreItems()) {
            int nextPage = page + 1;
            return createURL(url, Map.of("page", ""+nextPage));
        }
        return null;
    }
    
    
    @Override
    public String getNextName() {
        if(hasMoreItems()) {
            return "Next";
        }
        return null;
    }
    
    
    @Override
    public String getPrevLink() {
        if (page > 0) {
            int prevPage = page - 1;
            return createURL(url, Map.of("page", ""+prevPage));
        }
        return null;
    }
    
    
    @Override
    public String getPrevName() {
        if (page > 0) {
            return "Previous";
        }
        return null;
    }
    
    
    public boolean hasMoreItems() {
        return false;
    }
    
    
    protected String createURL(String url, Map<String, String> params) {
        return url + URLUtilities.getQueryString(params);
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
}
