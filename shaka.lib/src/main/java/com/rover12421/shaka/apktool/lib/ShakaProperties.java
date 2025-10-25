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
package com.rover12421.shaka.apktool.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ShakaProperties {
    public static String get(String key) {
        return get().getProperty(key);
    }

    public static Properties get() {
        if (sProps == null) {
            loadProps();
        }
        return sProps;
    }

    private static void loadProps() {
        InputStream in = ShakaProperties.class.getResourceAsStream("/properties/shaka.properties");
        sProps = new Properties();
        try {
            sProps.load(in);
            in.close();
        } catch (IOException ex) {
            LOGGER.warning("Can't load properties.");
        }

    }

    private static Properties sProps;

    private static final Logger LOGGER = Logger
            .getLogger(ShakaProperties.class.getName());

    public static boolean isDebug() {
        String str = get("debug");
        if (str != null && str.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getVersion() {
        return get("version");
    }
}