/**
 *
 * (c) Copyright Ascensio System SIA 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.onlyoffice.model.convertservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlyoffice.model.common.RequestEntity;
import com.onlyoffice.model.convertservice.convertrequest.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertRequest implements RequestEntity {

    private Boolean async;

    private Integer codePage;

    private Integer delimiter;

    private String filetype;

    private String key;

    private String outputtype;

    private String password;

    private String region;

    private Thumbnail thumbnail;

    private String title;

    private String token;

    private String url;

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(final String tn) {
        this.token = tn;
    }
}