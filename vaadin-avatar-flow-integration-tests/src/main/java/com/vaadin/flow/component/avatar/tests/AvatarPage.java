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

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.router.Route;

@Route("avatar")
public class AvatarPage extends Div {

    public AvatarPage() {
        Avatar avatar = new Avatar();

        NativeButton toggleImgLink = new NativeButton("Toggle img", e -> {
            if (avatar.getImgLink() == null || avatar.getImgLink().isEmpty()) {
                avatar.setImgLink("https://vaadin.com/");
            } else {
                avatar.setImgLink(null);
            }
        });
        toggleImgLink.setId("toggle-img");

        NativeButton toggleAbbr = new NativeButton("Toggle abbr", e -> {
            if (avatar.getAbbr() == null || avatar.getAbbr().isEmpty()) {
                avatar.setAbbr("BB");
            } else {
                avatar.setAbbr(null);
            }
        });
        toggleAbbr.setId("toggle-abbr");

        NativeButton toggleName = new NativeButton("Toggle name", e -> {
            if (avatar.getName() == null || avatar.getName().isEmpty()) {
                avatar.setName("Foo Bar");
            } else {
                avatar.setName(null);
            }
        });
        toggleName.setId("toggle-name");

        Div dataImg = new Div();
        dataImg.setId("data-block-img");

        Div dataAbbr = new Div();
        dataAbbr.setId("data-block-abbr");

        Div dataName = new Div();
        dataName.setId("data-block-name");

        NativeButton getPropertyValues = new NativeButton("Get properties", e -> {
            dataImg.setText(avatar.getElement().getProperty("img"));
            dataAbbr.setText(avatar.getElement().getProperty("abbr"));
            dataName.setText(avatar.getElement().getProperty("name"));
        });

        add(avatar, toggleImgLink, toggleAbbr, toggleName, dataImg, dataAbbr, dataName, getPropertyValues);
    }
}
