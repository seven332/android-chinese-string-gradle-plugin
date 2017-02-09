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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

public class AndroidChineseStringConverterTest {

  @Test
  public void testConvert() throws IOException {
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply("android-chinese-string");

    AndroidChineseStringExtension extension = new AndroidChineseStringExtension();

    File cnStringsXmlFile = new File(project.getProjectDir(), extension.getCnStringsXmlPath());
    File hkStringsXmlFile = new File(project.getProjectDir(), extension.getHkStringsXmlPath());
    File twStringsXmlFile = new File(project.getProjectDir(), extension.getTwStringsXmlPath());

    assertTrue(cnStringsXmlFile.getParentFile().isDirectory()
        || cnStringsXmlFile.getParentFile().mkdirs());

    BufferedSink sink = Okio.buffer(Okio.sink(cnStringsXmlFile));
    sink.writeUtf8("文件夹");
    sink.close();

    new AndroidChineseStringConverter(project, extension).convert();

    BufferedSource sourceHK = Okio.buffer(Okio.source(hkStringsXmlFile));
    assertEquals("文件夾", sourceHK.readUtf8());
    sourceHK.close();

    BufferedSource sourceTW = Okio.buffer(Okio.source(twStringsXmlFile));
    assertEquals("資料夾", sourceTW.readUtf8());
    sourceTW.close();
  }
}
