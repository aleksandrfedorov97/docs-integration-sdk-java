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

package com.onlyoffice.manager.document;

import com.onlyoffice.model.common.Format;
import com.onlyoffice.model.documenteditor.config.document.DocumentType;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public interface DocumentManager {

    List<Format> getFormats();

    String getDocumentKey(String fileId, boolean embedded);

    String getDocumentName(String fileId);

    String getExtension(String fileName);

    String getBaseName(String fileName);

    DocumentType getDocumentType(String fileName);

    boolean isEditable(String fileName);

    boolean isViewable(String fileName);

    boolean isFillable(String fileName);

    boolean hasAction(String fileName, String action);

    InputStream getNewBlankFile(String extension, Locale locale);

    String getDefaultExtension(DocumentType documentType);

    String getDefaultConvertExtension(String fileName);

    List<String> getConvertExtensionList(String fileName);

    Map<String, Boolean> getLossyEditableMap();

    List<String> getInsertImageExtensions();

    List<String> getCompareFileExtensions();

    List<String> getMailMergeExtensions();

    long getMaxFileSize();

    long getMaxConversionFileSize();
}
