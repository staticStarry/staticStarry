/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rover12421.shaka.lib.multiLanguage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by rover12421 on 2/9/15.
 */
public class MultiLanguageSupport {
    private Locale locale = Locale.getDefault();
    private Properties properties = new Properties();

    private static MultiLanguageSupport multiLanguageSupport = new MultiLanguageSupport();

    private MultiLanguageSupport() {
        loadLang();
    }

    private void loadLang() {
        String langFile = getLangFile();
        InputStream is = getClass().getClassLoader().getResourceAsStream(langFile);
        if (is != null) {
            try {
                properties.clear();
                properties.load(new InputStreamReader(is, "UTF-8"));
            } catch (IOException e) {
//            e.printStackTrace();
            }
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public static MultiLanguageSupport getInstance() {
        return multiLanguageSupport;
    }

    public void setLang(Locale locale) {
        if (locale != null && !this.locale.toString().equals(locale)) {
            this.locale = locale;
            loadLang();
        }
    }

    private String getLangFile() {
        return getLangFile(locale.toString());
    }

    private String getLangFile(String lang) {
        return "lang/" + lang + ".lng";
    }

    public String get(int id, String def) {
        return properties.getProperty(Integer.toString(id), def);
    }

    public String get(String key, String def) {
        return properties.getProperty(key, def);
    }
}
