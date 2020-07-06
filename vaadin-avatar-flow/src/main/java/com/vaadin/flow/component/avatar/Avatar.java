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
     * Constructs a new avatar with the provided name.
     *
     * @param name
     *            the name for the avatar
     */
    public Avatar(String name) {
        setName(name);
    }

    /**
     * Constructs a new avatar with the provided name and abbreviation.
     * <p>
     * Abbreviation has higher priority than name and will be displayed in case
     * both are set. Name will be used for the title in that case.
     *
     * @param name
     *            the name for the avatar
     * @param abbr
     *            the abbreviation for the avatar
     */
    public Avatar(String name, String abbr) {
        setName(name);
        setAbbr(abbr);
    }

    /**
     * Constructs a new avatar with the provided image link.
     * <p>
     * Image has higher priority than abbreviation and name, so will be
     * displayed if all are set.
     *
     * @param name
     *            the name for the avatar
     * @param abbr
     *            the abbreviation for the avatar
     * @param imgLink
     *            the image link for the avatar
     */
    public Avatar(String name, String abbr, String imgLink) {
        setName(name);
        setAbbr(abbr);
        setImgLink(imgLink);
    }

    /**
     * Gets the name that was set for the avatar.
     *
     * @return the name
     */
    public String getName() {
        return getElement().getProperty("name");
    }

    /**
     * Sets the name for the avatar.
     *
     * @param name
     *            the name for the avatar
     */
    public void setName(String name) {
        getElement().setProperty("name", name);
    }

    /**
     * Gets the abbreviation that was set for the avatar.
     *
     * @return the abbreviation
     */
    @Synchronize(property = "abbr", value = "abbr-changed")
    public String getAbbr() {
        return getElement().getProperty("abbr");
    }

    /**
     * Sets the abbreviation for the avatar.
     * <p>
     * Abbreviation has higher priority than name and will be displayed in case
     * both are set.
     *
     * @param abbr
     *            the abbreviation
     */
    public void setAbbr(String abbr) {
        getElement().setProperty("abbr", abbr);
    }

    /**
     * Gets the image link that was set for the avatar.
     *
     * @return the image link
     */
    public String getImgLink() {
        return getElement().getProperty("img");
    }

    /**
     * Sets the image link for the avatar.
     * <p>
     * Image has higher priority than abbreviation and name, so will be
     * displayed if all are set.
     *
     * @param imgLink
     *            the image link
     */
    public void setImgLink(String imgLink) {
        getElement().setProperty("img", imgLink);
    }

}
