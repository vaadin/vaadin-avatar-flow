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

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.component.avatar.Avatar;

/**
 * @author Vaadin Ltd.
 */
public class AvatarTest {

    private Avatar avatar = new Avatar();
    private Avatar constructedAvatar;
    String name = "foo bar";
    String abbr = "fb";
    String imgLink = "https://vaadin.com/";

    @Test
    public void shouldCreateEmptyAvatarWithDefaultState() {
        Assert.assertNull("Initial name is null", avatar.getName());
        Assert.assertNull("Initial abbr is null", avatar.getAbbr());
        Assert.assertNull("Initial imgLink is null", avatar.getImgLink());
    }

    @Test
    public void setName_getName() {
        avatar.setName(name);
        Assert.assertEquals(avatar.getName(), name);
    }

    @Test
    public void setAbbr_getAbbr() {
        avatar.setAbbr(abbr);
        Assert.assertEquals(avatar.getAbbr(), abbr);
    }

    @Test
    public void setImgLink_getImgLink() {
        avatar.setImgLink(imgLink);
        Assert.assertEquals(avatar.getImgLink(), imgLink);
    }

    @Test
    public void constructAvatarWithName() {
        constructedAvatar = new Avatar(name);
        Assert.assertEquals(constructedAvatar.getName(), name);
    }

    @Test
    public void constructAvatarWithNameAndAbbr() {
        constructedAvatar = new Avatar(name, abbr);

        Assert.assertEquals(constructedAvatar.getName(), name);
        Assert.assertEquals(constructedAvatar.getAbbr(), abbr);
    }

    @Test
    public void constructAvatarWithNameAndAbbrAndImgLink() {
        constructedAvatar = new Avatar(name, abbr, imgLink);

        Assert.assertEquals(constructedAvatar.getName(), name);
        Assert.assertEquals(constructedAvatar.getAbbr(), abbr);
        Assert.assertEquals(constructedAvatar.getImgLink(), imgLink);
    }

}
