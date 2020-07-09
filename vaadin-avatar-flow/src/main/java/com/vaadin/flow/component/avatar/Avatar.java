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

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Server-side component for the <code>vaadin-avatar</code> element.
 *
 * @author Vaadin Ltd
 */
@Tag("vaadin-avatar")
@JsModule("@vaadin/vaadin-avatar/src/vaadin-avatar.js")
@NpmPackage(value = "@vaadin/vaadin-avatar", version = "1.0.0-alpha2")
public class Avatar extends Component
    implements HasStyle, HasSize, HasTheme {

    /**
     * Creates a new empty avatar.
     * <p>
     * The avatar displays the user icon in the avatar and "Anonymous"
     * in the tooltip unless overridden by setting other properties.
     */
    public Avatar() {
    }

    /**
     * Creates a new avatar with the provided name.
     *
     * @param name
     *            the name for the avatar
     * @see Avatar#setName(String)
     */
    public Avatar(String name) {
        setName(name);
    }

    /**
     * Creates a new avatar with the provided name and url.
     *
     * @param name
     *            the name for the avatar
     * @param url
     *            the image url
     * @see Avatar#setName(String)
     * @see Avatar#setImage(String)
     */
    public Avatar(String name, String url) {
        setName(name);
        setImage(url);
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
     * <p>
     * The name is displayed in a tooltip on hover.
     * <p>
     * Automatically deduced abbreviation is displayed in the avatar if no
     * abbreviation or image is set.
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
    public String getAbbreviation() {
        return getElement().getProperty("abbr");
    }

    /**
     * Sets the abbreviation for the avatar.
     * <p>
     * The abbreviation will be displayed in the avatar if no image has
     * been set.
     *
     * @param abbr
     *            the abbreviation
     */
    public void setAbbreviation(String abbr) {
        getElement().setProperty("abbr", abbr);
    }

    /**
     * Gets the image url that was set for the avatar.
     *
     * @return the image url
     */
    public String getImage() {
        return getElement().getProperty("img");
    }

    /**
     * Sets the image url for the avatar.
     * <p>
     * The image will be displayed in the avatar even if abbreviation or
     * name is set.
     *
     * @param url
     *            the image url
     */
    public void setImage(String url) {
        getElement().setProperty("img", url);
    }

    /**
     * Gets the color index for the avatar.
     *
     * @return the color index
     */
    public Integer getColorIndex() {
        String colorIndex = getElement().getProperty("colorIndex");
        if (colorIndex != null && !colorIndex.isEmpty()) {
            return Integer.parseInt(colorIndex);
        }

        return null;
    }

    /**
     * Sets the color index for the avatar.
     * <p>
     * The color index defines which color will be used for the border
     * of the avatar.
     *
     * @param colorIndex
     *            the color index
     */
    public void setColorIndex(Integer colorIndex) {
        getElement().setProperty("colorIndex", colorIndex);
    }

    /**
     * Adds theme variants to the avatar group component.
     *
     * @param variants
     *            theme variants to add
     */
    public void addThemeVariants(AvatarVariant... variants) {
        getThemeNames()
                .addAll(Stream.of(variants)
                        .map(AvatarVariant::getVariantName)
                        .collect(Collectors.toList()));
    }

    /**
     * Removes theme variants from the avatar group component.
     *
     * @param variants
     *            theme variants to remove
     */
    public void removeThemeVariants(AvatarVariant... variants) {
        getThemeNames().removeAll(
                Stream.of(variants).map(AvatarVariant::getVariantName)
                        .collect(Collectors.toList()));
    }

}
