/*
 * Copyright 2017 Hippo Seven
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
 */

package com.hippo.androidchinesestring;

/*
 * Created by Hippo on 2/9/2017.
 */

public class AndroidChineseStringExtension {

  public static final String CONFIG_CN_HK = "s2hk.json";
  public static final String CONFIG_CN_TW = "s2twp.json";

  private String url = "http://opencc.byvoid.com/convert";
  private String textName = "text";
  private String configName = "config";
  private String cnStringsXmlPath = "src/main/res/values-zh-rCN/strings.xml";
  private String hkStringsXmlPath = "src/main/res/values-zh-rHK/strings.xml";
  private String twStringsXmlPath = "src/main/res/values-zh-rTW/strings.xml";

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTextName() {
    return textName;
  }

  public void setTextName(String textName) {
    this.textName = textName;
  }

  public String getConfigName() {
    return configName;
  }

  public void setConfigName(String configName) {
    this.configName = configName;
  }

  public String getCnStringsXmlPath() {
    return cnStringsXmlPath;
  }

  public void setCnStringsXmlPath(String cnStringsXmlPath) {
    this.cnStringsXmlPath = cnStringsXmlPath;
  }

  public String getHkStringsXmlPath() {
    return hkStringsXmlPath;
  }

  public void setHkStringsXmlPath(String hkStringsXmlPath) {
    this.hkStringsXmlPath = hkStringsXmlPath;
  }

  public String getTwStringsXmlPath() {
    return twStringsXmlPath;
  }

  public void setTwStringsXmlPath(String twStringsXmlPath) {
    this.twStringsXmlPath = twStringsXmlPath;
  }
}
