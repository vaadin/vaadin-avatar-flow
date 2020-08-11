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
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.internal.NodeOwner;
import com.vaadin.flow.internal.StateTree;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.StreamResourceRegistry;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Server-side component for the <code>vaadin-avatar</code> element.
 *
 * @author Vaadin Ltd
 */
@Tag("vaadin-avatar")
@JsModule("@vaadin/vaadin-avatar/src/vaadin-avatar.js")
@NpmPackage(value = "@vaadin/vaadin-avatar", version = "1.0.0-alpha3")
public class Avatar extends Component
    implements HasStyle, HasSize, HasTheme {

    private StreamRegistration resourceRegistration;

    private AbstractStreamResource imageResource;

    private Registration pendingRegistration;

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
     * Gets the image that was set for the avatar.
     *
     * @return the image resource value or {@code null} if the resource has not
     * been set
     */
    public AbstractStreamResource getImageResource() {
        return imageResource;
    }

    /**
     * Sets the image url for the avatar.
     * <p>
     * The image will be displayed in the avatar even if abbreviation or
     * name is set.
     * <p>
     * Setting the image with this method resets the image resource provided
     * with {@link Avatar#setImageResource(AbstractStreamResource)}
     *
     * @see Avatar#setImageResource(AbstractStreamResource)
     * @param url
     *            the image url
     */
    public void setImage(String url) {
        getElement().setProperty("img", url);

        unsetResource();
    }

    /**
     * Sets the image for the avatar.
     * <p>
     * Setting the image as a resource with this method resets the image URL
     * that was set with {@link Avatar#setImage(String)}
     *
     * @see Avatar#setImage(String)
     * @param resource
     *            the resource value or {@code null} to remove the resource
     */
    public void setImageResource(AbstractStreamResource resource) {
        imageResource = resource;
        if (resource == null) {
            unsetResource();
            return;
        }

        // The following is the copy of functionality from the ElementAttributeMap
        doSetResource(resource);
        if (getElement().getNode().isAttached()) {
            registerResource(resource);
        } else {
            deferRegistration(resource);
        }
    }

    private void doSetResource(AbstractStreamResource resource) {
        final URI targetUri;
        if (VaadinSession.getCurrent() != null) {
            final StreamResourceRegistry resourceRegistry = VaadinSession
                    .getCurrent().getResourceRegistry();
            targetUri = resourceRegistry.getTargetURI(resource);
        } else {
            targetUri = StreamResourceRegistry.getURI(resource);
        }
        getElement().setProperty("img", targetUri.toASCIIString());
    }

    private void unregisterResource() {
        StreamRegistration registration = resourceRegistration;
        Registration handle = pendingRegistration;
        if (handle != null) {
            handle.remove();
        }
        if (registration != null) {
            registration.unregister();
        }
        getElement().removeProperty("img");
    }

    private void deferRegistration(AbstractStreamResource resource) {
        assert pendingRegistration == null;
        Registration handle = getElement().getNode()
                // Do not convert to lambda
                .addAttachListener(new Command() {
                    @Override
                    public void execute() {
                        doSetResource(resource);
                        registerResource(resource);
                    }
                });
        pendingRegistration = handle;
    }

    private void registerResource(AbstractStreamResource resource) {
        assert resourceRegistration == null;
        StreamRegistration registration = getSession().getResourceRegistry()
                .registerResource(resource);
        resourceRegistration = registration;
        Registration handle = pendingRegistration;
        if (handle != null) {
            handle.remove();
        }
        pendingRegistration = getElement().getNode().addDetachListener(
                // Do not convert to lambda
                new Command() {
                    @Override
                    public void execute() {
                        Avatar.this.unsetResource();
                    }
                });
    }

    private void unsetResource() {
        imageResource = null;
        StreamRegistration registration = resourceRegistration;
        Optional<AbstractStreamResource> resource = Optional.empty();
        if (registration != null) {
            resource = Optional.ofNullable(registration.getResource());
        }
        unregisterResource();
        resource.ifPresent(this::deferRegistration);
    }

    private VaadinSession getSession() {
        NodeOwner owner = getElement().getNode().getOwner();
        assert owner instanceof StateTree;
        return ((StateTree) owner).getUI().getSession();
    }

    /**
     * Gets the color index for the avatar.
     *
     * @return the color index or {@code null} if the index has not been set
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
     * of the avatar. Color index N applies CSS variable
     * {@code --vaadin-user-color-N} to the border.
     *
     * @param colorIndex
     *            the color index or {@code null} to remove the index
     */
    public void setColorIndex(Integer colorIndex) {
        getElement().setProperty("colorIndex", colorIndex);
    }

    /**
     * Adds theme variants to the avatar component.
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
     * Removes theme variants from the avatar component.
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
