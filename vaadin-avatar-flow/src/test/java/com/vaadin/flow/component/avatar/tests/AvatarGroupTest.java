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

package com.vaadin.flow.component.avatar.tests;

import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.avatar.AvatarGroup.AvatarGroupItem;
import com.vaadin.flow.component.avatar.AvatarGroupVariant;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author Vaadin Ltd.
 */
public class AvatarGroupTest {

    private AvatarGroup avatarGroup = new AvatarGroup();
    private AvatarGroupItem avatarGroupItem = new AvatarGroupItem();
    private AvatarGroupItem avatarGroupItem2 = new AvatarGroupItem("Foo Bar");

    private String name = "foo bar";
    private String abbr = "fb";
    private String imgUrl = "https://vaadin.com/";

    private List<AvatarGroupItem> items = new ArrayList<>();


    @Test
    public void shouldHaveSomeTests() {
        assertTrue(true);
    }

    @Test
    public void setName_getName() {
        avatarGroupItem.setName(name);
        Assert.assertEquals(avatarGroupItem.getName(), name);
    }

    @Test
    public void setAbbr_getAbbr() {
        avatarGroupItem.setAbbreviation(abbr);
        Assert.assertEquals(avatarGroupItem.getAbbreviation(), abbr);
    }

    @Test
    public void setImgUrl_getImgUrl() {
        avatarGroupItem.setImage(imgUrl);
        Assert.assertEquals(avatarGroupItem.getImage(), imgUrl);
    }

    @Test
    public void setCreatedItems_getCreatedItems() {
        items.add(avatarGroupItem);
        items.add(avatarGroupItem2);
        avatarGroup.setItems(items);

        Assert.assertEquals(items, avatarGroup.getItems());
    }

    @Test
    public void createWithItems_getCreatedItems() {
        items.add(avatarGroupItem);
        items.add(avatarGroupItem2);
        AvatarGroup createdAvatarGroup = new AvatarGroup(items);

        Assert.assertEquals(items, createdAvatarGroup.getItems());
    }

    @Test
    public void createWithVarargsItems_getCreatedItems() {
        items.add(avatarGroupItem);
        items.add(avatarGroupItem2);
        AvatarGroup createdAvatarGroup = new AvatarGroup(avatarGroupItem, avatarGroupItem2);

        Assert.assertEquals(items,
                createdAvatarGroup.getItems());
    }

    @Test
    public void setMax_getMax() {
        avatarGroup.setMax(3);

        Assert.assertEquals(new Integer(3),
                avatarGroup.getMax());
    }

    @Test
    public void getMax_returnsNull() {
        Assert.assertNull(avatarGroup.getMax());
    }

    @Test
    public void addThemeVariant_themeAttributeContainsThemeVariant() {
        avatarGroup.addThemeVariants(AvatarGroupVariant.LUMO_LARGE);

        Set<String> themeNames = avatarGroup.getThemeNames();
        Assert.assertTrue(themeNames
                .contains(AvatarGroupVariant.LUMO_LARGE.getVariantName()));
    }


}
