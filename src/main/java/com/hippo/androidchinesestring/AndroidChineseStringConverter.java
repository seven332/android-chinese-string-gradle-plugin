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

import java.io.File;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.gradle.api.Project;

public class AndroidChineseStringConverter {

  private Project project;
  private AndroidChineseStringExtension extension;
  private OkHttpClient client;

  public AndroidChineseStringConverter(Project project, AndroidChineseStringExtension extension) {
    this.project = project;
    this.extension = extension;
    this.client = new OkHttpClient.Builder().build();
  }

  public void convert() throws IOException {
    File cnStringsXmlFile = new File(project.getProjectDir(), extension.getCnStringsXmlPath());
    File hkStringsXmlFile = new File(project.getProjectDir(), extension.getHkStringsXmlPath());
    File twStringsXmlFile = new File(project.getProjectDir(), extension.getTwStringsXmlPath());

    ensureDir(cnStringsXmlFile.getParentFile());
    ensureDir(hkStringsXmlFile.getParentFile());
    ensureDir(twStringsXmlFile.getParentFile());

    String cnStringsXmlString = read(cnStringsXmlFile);

    convertBetweenChinese(
        cnStringsXmlString,
        AndroidChineseStringExtension.CONFIG_CN_HK,
        hkStringsXmlFile,
        extension
    );
    convertBetweenChinese(
        cnStringsXmlString,
        AndroidChineseStringExtension.CONFIG_CN_TW,
        twStringsXmlFile,
        extension
    );
  }

  private void convertBetweenChinese(
      String text,
      String config,
      File file,
      AndroidChineseStringExtension extension
  ) throws IOException {
    RequestBody body = new FormBody.Builder()
        .add(extension.getTextName(), text)
        .add(extension.getConfigName(), config)
        .build();
    Request request = new Request.Builder().post(body).url(extension.getUrl()).build();
    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new IOException("Bad status code: " + response.code());
    }
    try (Source source = response.body().source(); BufferedSink sink = Okio.buffer(Okio.sink(file))) {
      sink.writeAll(source);
    }
  }

  private static void ensureDir(File file) throws IOException {
    if (!file.isDirectory() && !file.mkdirs()) {
      throw new IOException("Can't create dir: " + file);
    }
  }

  private static String read(File file) throws IOException {
    BufferedSource source = Okio.buffer(Okio.source(file));
    String text = source.readUtf8();
    source.close();
    return text;
  }
}
