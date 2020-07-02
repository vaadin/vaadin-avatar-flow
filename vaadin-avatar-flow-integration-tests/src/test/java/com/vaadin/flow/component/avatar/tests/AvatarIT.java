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

import com.vaadin.flow.component.avatar.demo.AvatarView;
import com.vaadin.flow.demo.ComponentDemoTest;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.parallel.Browser;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Integration tests for the {@link AvatarView}.
 *
 * @author Vaadin Ltd.
 */
@RunLocally(Browser.CHROME)
public class AvatarIT extends ComponentDemoTest {

    @Test
    public void propertiesAreSet() {
        WebElement toggleImg = layout.findElement(By.id("toggle-img"));
        WebElement toggleAbbr = layout.findElement(By.id("toggle-abbr"));
        WebElement toggleName = layout.findElement(By.id("toggle-name"));

        WebElement imgBlock = layout.findElement(By.id("data-block-img"));
        WebElement abbrBlock = layout.findElement(By.id("data-block-abbr"));
        WebElement nameBlock = layout.findElement(By.id("data-block-name"));

        toggleImg.click();
        Assert.assertEquals("https://vaadin.com/", imgBlock.getText());

        toggleAbbr.click();
        Assert.assertEquals("BB", abbrBlock.getText());

        toggleName.click();
        Assert.assertEquals("Foo Bar", nameBlock.getText());
    }

    @Test
    public void propertiesAreUnset() {
        WebElement toggleImg = layout.findElement(By.id("toggle-img"));
        WebElement toggleAbbr = layout.findElement(By.id("toggle-abbr"));
        WebElement toggleName = layout.findElement(By.id("toggle-name"));

        WebElement imgBlock = layout.findElement(By.id("data-block-img"));
        WebElement abbrBlock = layout.findElement(By.id("data-block-abbr"));
        WebElement nameBlock = layout.findElement(By.id("data-block-name"));

        toggleImg.click();
        toggleAbbr.click();
        toggleName.click();

        toggleImg.click();
        Assert.assertNull(imgBlock.getText());

        toggleAbbr.click();
        Assert.assertNull(abbrBlock.getText());

        toggleName.click();
        Assert.assertNull(nameBlock.getText());
    }

    @Override
    protected String getTestPath() {
        return "/vaadin-avatar";
    }
}
