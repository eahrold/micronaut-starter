/*
 * Copyright 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.api;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.MessageSource;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.naming.Described;
import io.micronaut.core.naming.Named;
import io.micronaut.starter.options.Language;
import io.micronaut.starter.util.NameUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO objects for {@link Language}.
 *
 * @author graemerocher
 * @since 1.0.0
 */
@Schema(name = "LanguageInfo")
@Introspected
public class LanguageDTO extends Linkable implements Named, Described, Selectable<String> {
    static final String MESSAGE_PREFIX = StarterConfiguration.PREFIX + ".language.";
    private final String name;
    private final String extension;
    private final String description;
    private final String value;

    /**
     * @param language The language
     */
    public LanguageDTO(Language language) {
        this.name = language.getName();
        this.extension = language.getExtension();
        this.description = language.getName();
        this.value = language.name();
    }

    /**
     * @param name the name
     * @param extension The extension
     * @param description The description
     */
    @Creator
    @Internal
    LanguageDTO(String name, String extension, String description, String value) {
        this.name = name;
        this.extension = extension;
        this.description = description;
        this.value = value;
    }

    /**
     * i18n constructor.
     * @param language The type
     * @param messageSource The message source
     * @param messageContext The message context
     */
    @Internal
    LanguageDTO(Language language, MessageSource messageSource, MessageSource.MessageContext messageContext) {
        String name = language.getName();
        this.name = name;
        this.extension = language.getExtension();
        this.description = messageSource.getMessage(MESSAGE_PREFIX + name + ".description", messageContext, name);
        this.value = language.name();
    }

    @Schema(description = "The extension of the language")
    public String getExtension() {
        return extension;
    }

    @Override
    @Schema(description = "A description of the language")
    public String getDescription() {
        return description;
    }

    @Override
    @Schema(description = "The name of the language")
    @NonNull
    public String getName() {
        return name;
    }


    @Override
    @Schema(description = "The value of the language for select options")
    @NonNull
    public String getValue() {
        return value;
    }

    @Override
    @Schema(description = "The label of the language for select options")
    public String getLabel() {
        return NameUtils.getNaturalNameOfEnum(name);
    }
}
