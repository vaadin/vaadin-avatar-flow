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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import elemental.json.Json;
import elemental.json.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Server-side component for the <code>vaadin-avatar-group</code> element.
 *
 * @author Vaadin Ltd
 */
@Tag("vaadin-avatar-group")
@JsModule("@vaadin/vaadin-avatar/src/vaadin-avatar-group.js")
@NpmPackage(value = "@vaadin/vaadin-avatar", version = "1.0.0-alpha2")
public class AvatarGroup extends Component
    implements HasStyle, HasSize {

    private List<AvatarGroupItem> items;

    /**
     * Creates an empty avatar group component.
     */
    public AvatarGroup() {
    }

    /**
     * Creates an avatar group with the provided items to be displayed as
     * avatars.
     */
    public AvatarGroup(Collection<AvatarGroupItem> items) {
        setItems(items);
    }

    /**
     * Creates an avatar group with the provided items to be displayed as
     * avatars.
     */
    public AvatarGroup(AvatarGroupItem... items) {
        setItems(items);
    }

    /**
     * Item to be set as an avatar for the avatar group.
     *
     * @author Vaadin Ltd
     */
    public static class AvatarGroupItem {
        private String name;
        private String abbr;
        private String img;

        /**
         * Creates a new empty avatar.
         * <p>
         * The avatar displays the user icon in the avatar and "Anonymous"
         * in the tooltip unless overridden by setting other properties.
         */
        public AvatarGroupItem() {
        }

        /**
         * Creates a new avatar with the provided name.
         *
         * @param name
         *            the name for the avatar
         * @see AvatarGroupItem#setName(String)
         */
        public AvatarGroupItem(String name) {
            setName(name);
        }

        /**
         * Creates a new avatar with the provided name and url.
         *
         * @param name
         *            the name for the avatar
         * @param url
         *            the image url
         * @see AvatarGroupItem#setName(String)
         * @see AvatarGroupItem#setImage(String)
         */
        public AvatarGroupItem(String name, String url) {
            setName(name);
            setImage(url);
        }

        /**
         * Gets the name that was set for the avatar.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name for the avatar.
         * <p>
         * Name is displayed in a tooltip on hover
         * <p>
         * Automatically deduced abbreviation is displayed in the avatar if no
         * abbr or img is set
         *
         * @param name
         *            the name for the avatar
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets the abbreviation that was set for the avatar.
         *
         * @return the abbreviation
         */
        public String getAbbreviation() {
            return abbr;
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
            this.abbr = abbr;
        }

        /**
         * Gets the image url that was set for the avatar.
         *
         * @return the image url
         */
        public String getImage() {
            return img;
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
            this.img = url;
        }

        @Override
        public String toString() {
            return '{'
                + "name:" + name + ";"
                + "abbr:" + abbr + ";"
                + "img:" + img + ";"
                + '}';
        }
    }

    /**
     * Sets the items that will be displayed as avatars.
     *
     * @param items
     *            the image url
     */
    public void setItems(Collection<AvatarGroupItem> items) {
        this.items = new ArrayList<>(items);

        getElement().setProperty("items", items.toString());
    }

    /**
     * Sets the items that will be displayed as avatars.
     *
     * @param items
     *            the items
     */
    public void setItems(AvatarGroupItem... items) {
        this.items = new ArrayList<>(Arrays.asList(items));

        getElement().setProperty("items", items.toString());
    }

    /**
     * Gets the items that were set for the avatar group.
     *
     * @return list of items
     */
    public List<AvatarGroupItem> getItems() {
        return items;
    }

    /**
     * Sets the the maximum number of avatars to display.
     * <p>
     * The remaining avatars will be shown in the dropdown
     *
     * @param max
     *            the max number of avatars
     */
    public void setMax(Integer max) {
        getElement().setProperty("max", max);
    }

    /**
     * Gets the maximum number of avatars to display.
     *
     * @return the max number of avatars
     * @see AvatarGroup#setMax(Integer)
     */
    public Integer getMax() {
        String max = getElement().getProperty("max");
        if (max != null && !max.isEmpty()) {
            return Integer.parseInt(max);
        }

        return null;
    }

}
