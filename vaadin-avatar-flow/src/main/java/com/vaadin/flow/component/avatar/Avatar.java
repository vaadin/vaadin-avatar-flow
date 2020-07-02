/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.flow.component.avatar;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("vaadin-avatar")
@JsModule("@vaadin/vaadin-avatar/src/vaadin-avatar.js")
@NpmPackage(value = "@vaadin/vaadin-avatar", version = "1.0.0-alpha2")
public class Avatar extends Component
    implements HasStyle, HasTheme {

    /**
     * Creates an empty avatar component.
     */
    public Avatar() {
    }

    /**
     * Constructs a new object with the ... .
     *
     * @param imgLink
     *            the ...
     */
    public Avatar(String imgLink) {
        setImgLink(imgLink);
    }

    /**
     * Constructs a new object with the ... .
     *
     * @param imgLink
     *            the ...
     */
    public Avatar(String imgLink, String abbr) {
        setImgLink(imgLink);
        setAbbr(abbr);
    }

    /**
     * Constructs a new object with the ... .
     *
     * @param imgLink
     *            the ...
     */
    public Avatar(String imgLink, String abbr, String name) {
        setImgLink(imgLink);
        setAbbr(abbr);
        setName(name);
    }

    /**
     * Gets the ...
     *
     * @return the ...
     */
    @Synchronize(property = "name", value = "name-changed")
    public String getName() {
        return getElement().getProperty("name");
    }

    /**
     * Sets the ...
     *
     * @param name
     *            the ...
     */
    public void setName(String name) {
        getElement().setProperty("name", name);
    }

    /**
     * Gets the ...
     *
     * @return the ...
     */
    @Synchronize(property = "abbr", value = "abbr-changed")
    public String getAbbr() {
        return getElement().getProperty("abbr");
    }

    /**
     * Sets the ...
     *
     * @param abbr
     *            the ...
     */
    public void setAbbr(String abbr) {
        getElement().setProperty("abbr", abbr);
    }

    /**
     * Gets the ...
     *
     * @return the ...
     */
    @Synchronize(property = "img", value = "img-changed")
    public String getImgLink() {
        return getElement().getProperty("img");
    }

    /**
     * Sets the ...
     *
     * @param imgLink
     *            the ...
     */
    public void setImgLink(String imgLink) {
        getElement().setProperty("img", imgLink);
    }

}
