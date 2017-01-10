/*
 * RSS Feed Aggregator
 * This is an api for \"RSS Feed Aggregator\".  [View Subject](https://intra.epitech.eu/module/2016/M-EAP-650/PAR-9-1/acti-235029/project/file/RSS-feed-aggregator.pdf) or [Messenger group](https://www.messenger.com/t/552069568251252)  A successfull login or signup generate a key usable to authenticate request  This key is owned by one account, a request providing an apiKey should get result for the user owning this key. 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
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


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Feed
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-10T14:57:23.575Z")
public class Feed {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("link")
  private String link = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("language")
  private String language = null;

  public Feed id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Feed url(String url) {
    this.url = url;
    return this;
  }

   /**
   * url to download feed
   * @return url
  **/
  @ApiModelProperty(example = "null", required = true, value = "url to download feed")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Feed title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Feed link(String link) {
    this.link = link;
    return this;
  }

   /**
   * url of blog corresponding to feed
   * @return link
  **/
  @ApiModelProperty(example = "null", value = "url of blog corresponding to feed")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Feed description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Feed language(String language) {
    this.language = language;
    return this;
  }

   /**
   * ISO_639-1 language code
   * @return language
  **/
  @ApiModelProperty(example = "null", value = "ISO_639-1 language code")
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Feed feed = (Feed) o;
    return Objects.equals(this.id, feed.id) &&
        Objects.equals(this.url, feed.url) &&
        Objects.equals(this.title, feed.title) &&
        Objects.equals(this.link, feed.link) &&
        Objects.equals(this.description, feed.description) &&
        Objects.equals(this.language, feed.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, title, link, description, language);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Feed {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

